/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command closes the claw by triggering the pneumatic solenoid valve controlling the claw
 */
public class SixPistonUp extends Command {
  
  Climber climber;

  public SixPistonUp() {
    requires(Climber.getInstance());
    climber = Climber.getInstance();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    climber.liftSix();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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