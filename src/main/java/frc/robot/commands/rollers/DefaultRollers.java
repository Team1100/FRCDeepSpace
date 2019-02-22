/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.rollers;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.input.XboxController;
import frc.robot.input.XboxController.XboxAxis;
import frc.robot.subsystems.Rollers;

/**
 * Gives the operator control of the rollers.
 * Currently bound to the right joystick on the Xbox Controller
 */
public class DefaultRollers extends Command {
  double speed;
  XboxAxis rightJoystickY = XboxAxis.kYRight;
  public DefaultRollers() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Rollers.getInstance());
    //requires(BallIntake.getInstance());

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Rollers.getInstance().spinRollers(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYRight);


    Rollers.getInstance().rollersIn(speed);
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
