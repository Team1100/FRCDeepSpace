/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.gantry;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Gantry;

public class MoveGantryLeft extends Command {
  public MoveGantryLeft() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Gantry.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Gantry.getInstance().translateGantry(0.2);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Gantry.getInstance().isAtLeft();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Gantry.getInstance().translateGantry(0);
    Gantry.getInstance().getEncoder().reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
