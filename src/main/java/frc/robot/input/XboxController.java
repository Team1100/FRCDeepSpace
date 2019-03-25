package frc.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is for an XboxController. All information about any given button
 * or axis can be accessed through the functions written here.
 */
public class XboxController extends Joystick {
	
	/*                                                                      *
	 * ---------------------NOTE TO FUTURE TEAM MEMBERS---------------------*
	 *                                                                      *
	 * This was an extremely annoying class to write.                       *
	 * Do yourself a favor and copy this file into the newest robot project.*
	 *                                                                      *
	 * - Grant Perkins, 2018                                                *
	 *                                                                      *
	 */
	private JoystickButton buttonA;
	private JoystickButton buttonB;
	private JoystickButton buttonX;
	private JoystickButton buttonY;
	private JoystickButton buttonLeftBumper;
	private JoystickButton buttonRightBumper;
	private JoystickButton buttonBack;
	private JoystickButton buttonStart;
	private JoystickButton buttonLeftStick;
	private JoystickButton buttonRightStick;
	private DirectionalPad dpad;
	private double deadband;	

	/**
	 * This enumeration is used for the 6 axes: the x and y of the two joysticks, as
	 * well as the two triggers.
	 *
	 */
	public enum XboxAxis {
		/**
		 * Left stick, x axis
		 */
		kXLeft(0),
		/**
		 * Left stick, y axis
		 */
		kYLeft(1),
		/**
		 * Left trigger
		 */
		kLeftTrigger(2),
		/**
		 * Right trigger
		 */
		kRightTrigger(3),
		/**
		 * Right stick, x axis
		 */
		kXRight(4),
		/**
		 * Right stick, y axis
		 */
		kYRight(5);

		public final int key;

		/**
		 * This is the constructor of the enumeration. The keys provided to the
		 * constructor are used to access the value of each axis in getAxis().
		 * 
		 * @param key
		 *            the magical number assigned by the Driver Station
		 */
		private XboxAxis(int key) {
			this.key = key;
		}
	}
	
	/**
     * Relation of direction and number for .getPOV() used
     * in the DirectionalPad class.
     */
    private static enum Direction {
        kUp(0),
        kUpRight(45),
        kRight(90),
        kDownRight(135),
        kDown(180),
        kDownLeft(225),
        kLeft(270),
        kUpLeft(315);

        private int value;

        /**
         * Constructor
         * @param value
         */
        Direction(int value) {
            this.value = value;
        }
    }

	/**
	 * Initializes a XBOX Controller on a specific channel, mapping the buttons. The
	 * JoyStick will never return a value in between +/- the deadband value.
	 * 
	 * @param channel the channel the Controller is plugged into
	 * @param deadband the value of the deadband, from 0 to 1
	 */
	public XboxController(int channel, double deadband) {
		super(channel);

		buttonA = new JoystickButton(this, 1);
		buttonB = new JoystickButton(this, 2);
		buttonX = new JoystickButton(this, 3);
		buttonY = new JoystickButton(this, 4);
		buttonLeftBumper = new JoystickButton(this, 5);
		buttonRightBumper = new JoystickButton(this, 6);
		buttonBack = new JoystickButton(this, 7);
		buttonStart = new JoystickButton(this, 8);
		buttonLeftStick = new JoystickButton(this, 9);
		buttonRightStick = new JoystickButton(this, 10);
		dpad = new DirectionalPad(this);

		this.deadband = deadband;
	}

	/**
	 * Gets the A Button from the Controller
	 * 
	 * @return the A Button
	 */
	public JoystickButton getButtonA() {
		return buttonA;
	}

	/**
	 * Gets the B Button from the Controller
	 * 
	 * @return the B Button
	 */
	public JoystickButton getButtonB() {
		return buttonB;
	}

	/**
	 * Gets the X Button from the Controller
	 * 
	 * @return the X Button
	 */
	public JoystickButton getButtonX() {
		return buttonX;
	}

	/**
	 * Gets the Y Button from the Controller
	 * 
	 * @return the Y Button
	 */
	public JoystickButton getButtonY() {
		return buttonY;
	}

	/**
	 * Gets the Left Bumper from the Controller
	 * 
	 * @return the Left Bumper
	 */
	public JoystickButton getButtonLeftBumper() {
		return buttonLeftBumper;
	}

	/**
	 * Gets the Right Bumper from the Controller
	 * 
	 * @return the Right Bumper
	 */
	public JoystickButton getButtonRightBumper() {
		return buttonRightBumper;
	}

	/**
	 * Gets the Back Button from the Controller
	 * 
	 * @return the Back Button
	 */
	public JoystickButton getButtonBack() {
		return buttonBack;
	}

	/**
	 * Gets the Start Button from the Controller
	 * 
	 * @return the Start Button
	 */
	public JoystickButton getButtonStart() {
		return buttonStart;
	}

	/**
	 * Gets the Left Stick Button from the Controller
	 * 
	 * @return Left Stick Button
	 */
	public JoystickButton getButtonLeftStick() {
		return buttonLeftStick;
	}

	/**
	 * Gets the Right Stick Button from the Controller
	 * 
	 * @return Right Stick Button
	 */
	public JoystickButton getButtonRightStick() {
		return buttonRightStick;
	}

	/**
	 * Gets position of specified axis, accounting for the deadband
	 *
	 * @param axis
	 *            the XboxAxis (XboxController.XboxAxis.k___) to retrieve
	 * @return the value of the axis, with the deadband factored in
	 */
	public double getAxis(XboxAxis axis) {
		double val = getRawAxis(axis.key);
		if (Math.abs(val) <= deadband) {
			val = 0.0;
		}
		return val;
	}
	
	/**
	 * Gets the dpad
	 * @return the dpad
	 */
	public DirectionalPad getDPad() {
		return dpad;
	}
	
	/**
	 * This class is for the DPad on the xbox controller.
	 *
	 */
	public static class DirectionalPad extends Button {
        
        private final Joystick parent;
        
        final Button up;
        final Button upRight;
        final Button right;
        final Button downRight;
        final Button down;
        final Button downLeft;
        final Button left;
        final Button upLeft;
        
        /**
         * Initializes buttons
         * @param parent 
         */
        DirectionalPad(Joystick parent) {
            this.parent	= parent;
            this.up = new DPadButton(this, Direction.kUp);
            this.upRight = new DPadButton(this, Direction.kUpRight);
            this.right = new DPadButton(this, Direction.kRight);
            this.downRight = new DPadButton(this, Direction.kDownRight);
            this.down = new DPadButton(this, Direction.kDown);
            this.downLeft = new DPadButton(this, Direction.kDownLeft);
            this.left = new DPadButton(this, Direction.kLeft);
            this.upLeft = new DPadButton(this, Direction.kUpLeft);
        }
        
        /**
         * Gets the angle of the POV
         * @return the angle of the POV
         */
        public int getPOV() {
        	return parent.getPOV();
        }
        
        /**
         * True if dpad is at an angle
         */
		@Override
		public boolean get() {
			return getPOV() != -1;
		}
		
		/**
		 * Gets the up direction
		 * @return up button
		 */
		public Button getUp() {
			return up;
		}
		
		/**
		 * Gets the upper right direction
		 * @return upper right button
		 */
		public Button getUpRight() {
			return upRight;
		}
		
		/**
		 * Gets the right direction
		 * @return right button
		 */
		public Button getRight() {
			return right;
		}
		
		/**
		 * Gets the down right direction
		 * @return down right button
		 */
		public Button getDownRight() {
			return downRight;
		}
		
		/**
		 * Gets the down direction
		 * @return down button
		 */
		public Button getDown() {
			return down;
		}
		
		/**
		 * Gets the down left direction
		 * @return down left button
		 */
		public Button getDownLeft() {
			return downLeft;
		}
		
		/**
		 * Gets the left direction
		 * @return left button
		 */
		public Button getLeft() {
			return left;
		}
		
		/**
		 * Gets the up left direction
		 * @return left button
		 */
		public Button getUpLeft() {
			return upLeft;
		}
		
		/**
         * This class is used to represent each of the 8 values a
         * dpad has as a button.
         */
        public static class DPadButton extends Button {
            private final Direction direction;
            private final DirectionalPad parent;
            
            /**
             * Constructor
             * @param parent
             * @param dPad
             */
            DPadButton(DirectionalPad parent, Direction dPadDirection) {
                this.direction  = dPadDirection;
                this.parent = parent;
            }
            
            /**
             * True if dpad direction is this button
             */
            @Override
            public boolean get() {
                return parent.getPOV() == direction.value;
            }
        }
	}
}