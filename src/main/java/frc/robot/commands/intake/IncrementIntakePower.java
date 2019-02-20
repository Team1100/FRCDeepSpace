/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import frc.robot.subsystems.BallIntake;
import edu.wpi.first.wpilibj.command.Command;

public class IncrementIntakePower extends Command {
  
  double incrementPower;
  BallIntake intake;
  boolean executed;

  public IncrementIntakePower(double increment) {
    incrementPower = increment;

    requires(BallIntake.getInstance());
    intake = BallIntake.getInstance();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    intake.incrementIntakePower(incrementPower);
    executed = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    executed = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return executed;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    executed = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    executed = false;
  }
}
