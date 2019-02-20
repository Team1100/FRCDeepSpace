package frc.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class AttackThree extends Joystick {

	public static boolean leftIsLeft = true;

	public static boolean isInTesting = false;

	/*
	 * Total amount of buttons on the Attack Three
	 */
	private final short totalButtons = 11;
	
	/*
	 * Array of every button on the Attack Three
	 */
	private JoystickButton buttons[];
	
	/*
	 * Amount of deadband
	 */
	private double deadband;

	/**
	 * Initializes a Joystick on a specific channel, mapping the buttons. The
	 * Joystick will never return a value in between +/- the deadband value.
	 * 
	 * @param channel the channel the Joystick is plugged into
	 * @param deadband the value of the deadband, from 0 to 1
	 */
	public AttackThree(int channel, double deadband) {
		super(channel);

		buttons = new JoystickButton[totalButtons];

		// Maps each button key to a location in the buttons array
		for (int i = 0; i < totalButtons; i++) {
			buttons[i] = new JoystickButton(this, i + 1);
		}

		this.deadband = deadband;
	}

	/**
	 * Gets the specified button on this controller
	 *
	 * @param number the number of the button on the Joystick
	 * @return the Button corresponding the the number, starting at 1
	 */
	public JoystickButton getButton(int number) {
		return buttons[number - 1];
	}

	public enum AttackThreeAxis {
		
		/**
		 * x axis
		 */
		kX(0),
		/**
		 * y axis
		 */
		kY(1),
		/**
		 * z axis. THIS DOESN'T EXIST BUT DRIVERSTATION SAYS IT DOES
		 */
		kZ(2);

		public final int key;

		/**
		 * This is the constructor of the enumeration. The keys provided to the
		 * constructor are used to access the value of each axis in getAxis().
		 * 
		 * @param key the magical number assigned by the Driver Station
		 */
		private AttackThreeAxis(int key) {
			this.key = key;
		}
	}

	/**
	 * Gets position of a specific axis, accounting for the deadband
	 *
	 * @param axis the AxisType to retrieve
	 * @return the value of the axis, with the deadband factored in
	 */
	public double getAxis(AttackThreeAxis axis) {
		double val = getRawAxis(axis.key);
		if (Math.abs(val) <= deadband) {
			val = 0.0;
		}
		return val;
	}
}