/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ProximitySensor;

/**
 * This command will end when the current state of the proximity sensor returns true
 * The proximity sensor will be used to detect when the ball is in the claw and will close
 * the claw onto the ball, therefore capturing it.
 * Can be used in command groups as a sequential command.
 */
public class ClawAcquireBall extends Command {
  private ProximitySensor ps;
  boolean captured = false;
  public ClawAcquireBall() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(ProximitySensor.getInstance());
    ps = ProximitySensor.getInstance();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    captured = ps.getInstance().isTriggered();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return captured;
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
