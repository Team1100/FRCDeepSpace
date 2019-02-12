package frc.robot.commands.vision;

import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Gantry;
import frc.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Moves the glaw along the gantry until the claw is centered on the vision target
 */
public class TranslateClawToCenter extends PIDCommand {
	private PIDController pidController = getPIDController();
	private int count = 0;
	
	/**
	 * Sets up PID Controller
	 * @param tolerance Percent tolerance of PID loop
	 */
    public TranslateClawToCenter(double tolerance) {
        //TODO: Tune these PID values
    	super(.05, .05, 0);
        requires(Drive.getInstance()); 
        //Max displacement from center of image (cx)
        setInputRange(0, 640);
        pidController.setOutputRange(-1, 1);
        pidController.setPercentTolerance(tolerance);
        //Ideally want center of target to be aligned with center of camera
        setSetpoint(0);
    }
    
    /**
     * Finishes when claw is secured on target
     */
    protected boolean isFinished() {
        if (pidController.onTarget()) {
            if (count >= 3) {
              return true;
            }
            count++;     
          } else {
            count = 0;
          }
          return false;
      }
    
    /**
     * Returns the displacement from camera center to target center
     */
	@Override
	protected double returnPIDInput() {
		//If not detected, end
		if (Vision.getInstance().getCX() == -1) {
			return 0;
        }
        else {
            return Vision.getInstance().getCX();
        }
	}
	
	/**
	 * Tank drives based on the output of the PID controller
	 * @param output the output of the PID controller
	 */
	@Override
	protected void usePIDOutput(double output) {
		Gantry.getInstance().translateGantry(output);
	}
}