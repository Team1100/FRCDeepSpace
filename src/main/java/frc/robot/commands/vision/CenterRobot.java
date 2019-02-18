package frc.robot.commands.vision;

import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Moves the robot until it is centered on the vision target
 */
public class CenterRobot extends PIDCommand {
	private PIDController pidController = getPIDController();
  private int count;
  private boolean isAimed = false;
	
	/**
	* Sets up PID Controller
	* @param tolerance Percent tolerance of PID loop
	*/
  public CenterRobot(double tolerance) {
      //TODO: Tune these PID values
      super(.05, .05, 0);
      requires(Drive.getInstance()); 
      //Max displacement from center of image (cx)
      setInputRange(0, 640);
      pidController.setOutputRange(-0.5, 0.5);
      pidController.setPercentTolerance(tolerance);
      //Ideally want center of target to be aligned with center of camera
      setSetpoint(0);
    }
    
    protected void initialize() {
      count = 0;
      setTimeout(2.5);
    }


    /**
     * Finishes when robot is locked on target
     */
    protected boolean isFinished() {
      if(Vision.getInstance().getCX() == -1){
        return true;
      }
      if(isTimedOut()) {
        return true;
      }
      if (pidController.onTarget()) {
        if (count >= 3) {
          return true;
        }
       count++;     
      } 
      else {
       count = 0;
      }
      return false;
    }
    
    /**
     * Returns the displacement from camera center to target center
     */
	@Override
	protected double returnPIDInput() {
      return Vision.getInstance().getCX();
  }
	
	/**
	 * drives robot based on the output of the PID controller
	 * @param output the output of the PID controller
	 */
	@Override
	protected void usePIDOutput(double output) {
    Drive.getInstance().tankDrive(output, -output);
	}
}