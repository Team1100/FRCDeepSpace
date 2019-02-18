package frc.robot.commands.vision;

import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Gantry;
import frc.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Moves the claw along the gantry until the claw is centered on the vision target
 */
public class TranslateClawToCenter extends PIDCommand {
	private PIDController pidController = getPIDController();
  private int count;
  private boolean isAimed = false;
  
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
    
    protected void initialize() {
      count = 0;
      setTimeout(2.5);
    }

    /**
     * Finishes when claw is secured on target
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
          isAimed = true;
          Vision.getInstance().setisAimed(isAimed);
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
		if (Vision.getInstance().getCX() == -1) {
			return 0;
    }
    else {
      return Vision.getInstance().getCX();
    }
  }
	
	/**
	 * drives Gantry based on the output of the PID controller
	 * @param output the output of the PID controller
	 */
	@Override
  protected void usePIDOutput(double output) {
    isAimed =  false;
    Vision.getInstance().setisAimed(isAimed);
    Gantry.getInstance().translateGantry(output);
	}
}