/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stilts;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Stilts;

/**
 * Used to test both stilts. Has no use anymore?
 */
public class TestingStilts extends Command {

  private Stilts stilts;
  private double speed;

  public TestingStilts(double power) {
    requires(Stilts.getInstance());
    stilts = Stilts.getInstance();
    speed = power;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    stilts.setSpeed(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    stilts.setSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    stilts.setSpeed(0);
  }
}
