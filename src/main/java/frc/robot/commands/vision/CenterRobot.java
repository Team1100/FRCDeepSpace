package frc.robot.commands.vision;

import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.Timer;

/**
 * Moves the claw along the gantry until the claw is centered on the vision target
 */
public class CenterRobot extends PIDCommand {
	private PIDController pidController = getPIDController();
  private int count;
  private boolean isAimed = false;
  Timer t;
	
	/**
	* Sets up PID Controller
	* @param tolerance Percent tolerance of PID loop
	*/
  public CenterRobot(double tolerance) {
      //TODO: Tune these PID values
      super(.05, .05, 0);
      t = new Timer();
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
    	t.start();
    }


    /**
     * Finishes when claw is secured on target
     */
    protected boolean isFinished() {
      if(t.get() > 2.5) {
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
    Drive.getInstance().tankDrive(output, -output);
	}
}