package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.OI;
import frc.robot.input.AttackThree.AttackThreeAxis;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Vision;

/**
 * Moves the robot until it is centered on the vision target
 */
public class DriveWhileCentered extends PIDCommand {
  private PIDController pidController = getPIDController();
  private int count;
  double left, right;
  private boolean isAimed = false;
  AttackThreeAxis yAxis = AttackThreeAxis.kY;


  /**
   * Sets up PID Controller
   * @param tolerance Percent tolerance of PID loop
   */
  public DriveWhileCentered(double tolerance) {
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
   * Finishes when robot is secured on target
   */
  protected boolean isFinished() {
    if(isTimedOut()) {
      return true;
    }
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
    return Vision.getInstance().getCX();
  }

  /**
   * drives robot based on the output of the PID controller and driver input
   * @param output the output of the PID controller
   */
  @Override
  protected void usePIDOutput(double output) {
    left  = OI.getInstance().getLeftStick().getAxis(yAxis);
    right = OI.getInstance().getRightStick().getAxis(yAxis);
    double leftOutput = output + left;
    double rightOutput = -output + right;
    if (leftOutput > 1 || leftOutput < -1 || rightOutput > 1 || rightOutput < -1){
      if (Math.abs(leftOutput) >= Math.abs(rightOutput)){
        rightOutput /= leftOutput;
        leftOutput = 1;
      } else if (Math.abs(leftOutput) < Math.abs(rightOutput)){
        leftOutput /= rightOutput;
        rightOutput = 1;
      }
    }
    Drive.getInstance().tankDrive(leftOutput, rightOutput);
  }
}
