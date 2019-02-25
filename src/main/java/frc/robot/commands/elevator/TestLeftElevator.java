package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.input.XboxController;
import frc.robot.subsystems.Elevator;

/**
 * Used in the testing part of the program to drive the left side of the elevator.
 */
public class TestLeftElevator extends Command {
  double speed;

  public TestLeftElevator() {
    requires(Elevator.getInstance());
  }

  /**
   * Not Used
   */
  protected void initialize() {
  }

  /**
   * Uses left stick of xbox controller to move left side of elevator up and down
   */
  protected void execute() {
    speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYLeft);
    Elevator.getInstance().extendLeft(speed);
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
   * Stops elevator when command gets interrupted.
   */
  protected void interrupted() {
    Elevator.getInstance().extendLeft(0);
  }

}
