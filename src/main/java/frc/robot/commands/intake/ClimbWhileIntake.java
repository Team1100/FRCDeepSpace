/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.Rollers;

/**
 * This command is used to lower the intake while also running the rollers inward.
 * This command is used in the climbing sequence when climbing the HAB level 3.
 */
public class ClimbWhileIntake extends Command {
  double rSpeed, iSpeed;
  BallIntake intake;
  Rollers rollers;

  public ClimbWhileIntake(double rollerSpeed, double intakeSpeed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(BallIntake.getInstance());
    requires(Rollers.getInstance());

    intake = BallIntake.getInstance();
    rollers = Rollers.getInstance();

    rSpeed = rollerSpeed;
    iSpeed = intakeSpeed;
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    rollers.rollersIn(rSpeed);
    intake.intakeDown(iSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    rollers.rollersIn(0);
    intake.intakeDown(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    rollers.rollersIn(0);
    intake.intakeDown(0);
  }
}
