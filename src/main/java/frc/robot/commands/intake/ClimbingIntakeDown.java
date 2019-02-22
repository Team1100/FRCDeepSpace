/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import frc.robot.subsystems.BallIntake;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command is used in the climbing phase of the game to lower the intake onto the HAB
 */
public class ClimbingIntakeDown extends Command {
  
  BallIntake intake;
  final int THREE_SECONDS = 3;
  double speed;

  public ClimbingIntakeDown(double power) {
    requires(BallIntake.getInstance());
    intake = BallIntake.getInstance();
    speed = power;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    intake.intakeDown(speed);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    intake.intakeDown(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    intake.intakeUp(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //intake.intakeUp(0);
  }
}
