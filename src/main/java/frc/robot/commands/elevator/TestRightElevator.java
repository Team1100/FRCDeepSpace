package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.input.XboxController;
import frc.robot.subsystems.Elevator;

public class TestRightElevator extends Command {
  double speed;

  public TestRightElevator() {
    requires(Elevator.getInstance());
  }

  /**
   * Not Used
   */
  protected void initialize() {
  }

  /**
   * Uses left stick of xbox controller to move the right side of the elevator up and down
   */
  protected void execute() {
    speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYLeft);
    Elevator.getInstance().extendRight(speed);
  }

  /**
   * Always false because default commands never stop
   */
  protected boolean isFinished() {
    return false;
  }

  /**
   * Not Used
   */
  protected void end() {
  }

  /**
   * Stops the elevator when interrupted.
   */
  protected void interrupted() {
    Elevator.getInstance().extendRight(0);
  }

}
