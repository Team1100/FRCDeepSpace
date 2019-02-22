/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stilts;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.input.XboxController;
import frc.robot.input.XboxController.*;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.Stilts;

/**
 * Used for testing climbing and determining optimal stiltSpeed to intakeSpeed ratio.
 * Currently takes in input from left xbox joystick.
 */
public class JoystickClimb extends Command {
  double intakeSpeed, stiltSpeed, speed;
  XboxAxis rightJoystickY = XboxAxis.kYRight;

  public JoystickClimb() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(BallIntake.getInstance());
    requires(Stilts.getInstance());

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    BallIntake.getInstance().setIntakeSpeed(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYLeft);
    //speed = OI.getInstance().getXbox().getAxis(rightJoystickY);
    stiltSpeed = speed;
    intakeSpeed = stiltSpeed / 4;
    Stilts.getInstance().setSpeed(stiltSpeed);
    BallIntake.getInstance().intakeDown(intakeSpeed);
    //BallIntake.getInstance().spinRollers(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
