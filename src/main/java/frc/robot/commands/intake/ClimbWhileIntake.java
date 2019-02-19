/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.BallIntake;

public class ClimbWhileIntake extends Command {
  double rSpeed, iSpeed;
  BallIntake intake;

  public ClimbWhileIntake(double rollerSpeed, double intakeSpeed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(BallIntake.getInstance());
    intake = BallIntake.getInstance();

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
    intake.rollersIn(rSpeed);
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
    intake.rollersIn(0);
    intake.intakeDown(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    intake.rollersIn(0);
    intake.intakeDown(0);
  }
}
