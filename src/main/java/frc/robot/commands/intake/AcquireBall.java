/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.BeamBreak;

/**
 * This command ends when the ball is detected in the intake
 */
public class AcquireBall extends Command {
  private BeamBreak bb;
  public AcquireBall() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(BeamBreak.getInstance());
    bb = BeamBreak.getInstance();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(10);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (!bb.get() || isTimedOut());
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