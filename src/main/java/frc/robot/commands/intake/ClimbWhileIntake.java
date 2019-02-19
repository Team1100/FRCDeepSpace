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
  VictorSPX axis_movement_left, axis_movement_right, rollers;
  double rSpeed, iSpeed;

  public ClimbWhileIntake(double rollerSpeed, double intakeSpeed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(BallIntake.getInstance());
    axis_movement_left = new VictorSPX(RobotMap.B_AXIS_MOVEMENT_LEFT);
    axis_movement_right = new VictorSPX(RobotMap.B_AXIS_MOVEMENT_RIGHT);
    rollers = new VictorSPX(RobotMap.B_ROLLERS);

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
    rollers.set(ControlMode.PercentOutput, rSpeed);
    axis_movement_left.set(ControlMode.PercentOutput, iSpeed);
    axis_movement_right.set(ControlMode.PercentOutput, iSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    rollers.set(ControlMode.PercentOutput, 0);
    axis_movement_left.set(ControlMode.PercentOutput, 0);
    axis_movement_right.set(ControlMode.PercentOutput, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    rollers.set(ControlMode.PercentOutput, 0);
    axis_movement_left.set(ControlMode.PercentOutput, 0);
    axis_movement_right.set(ControlMode.PercentOutput, 0);
  }
}
