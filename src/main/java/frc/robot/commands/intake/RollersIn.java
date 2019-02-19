/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;


import frc.robot.subsystems.Rollers;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.Command;

public class RollersIn extends Command {

  Rollers rollers;

  public RollersIn() {
    requires(Rollers.getInstance());
    rollers = Rollers.getInstance();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    rollers.rollersIn(.5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Rollers.getInstance().ballIsIn();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    rollers.rollersIn(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    rollers.rollersIn(0);
  }
}
