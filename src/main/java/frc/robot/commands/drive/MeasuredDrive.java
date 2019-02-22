/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drive;
/**
 * This command drives the robot based off of a input distance in feet.
 */
public class MeasuredDrive extends Command {
  double rightDistance, leftDistance;
  double dist, pow;
  boolean completed;
  //TODO: Measure the PULSES_PER_FOOT value.
  double PULSES_PER_FOOT = 4090;
  public MeasuredDrive(double distanceInFeet, double power) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Drive.getInstance());
    dist = distanceInFeet * PULSES_PER_FOOT;
    pow = power;
    completed = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Drive.getInstance().getRightEncoder().reset();
    Drive.getInstance().getLeftEncoder().reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    rightDistance = Drive.getInstance().getRightEncoder().getDistance();
    leftDistance = Drive.getInstance().getLeftEncoder().getDistance();
    if(rightDistance >= dist && leftDistance >= dist){
      completed = true;
    }
    if(!completed){
      Drive.getInstance().tankDrive(pow, pow);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return completed;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Drive.getInstance().tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
