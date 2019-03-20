/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.NavX;
import frc.robot.OI;
import frc.robot.input.AttackThree;
import frc.robot.input.AttackThree.AttackThreeAxis;

/**
 * This command will drive the robot in a straight direction aligning itself using the NavX;
 */
public class DriveStraight extends Command {
  Drive drive;
  AHRS navX;

  AttackThree left, right;
  double error;
  double turnPower;
  double power;
  public DriveStraight(double power) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Drive.getInstance());
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drive = Drive.getInstance();
    drive.tankDrive(0, 0);
    navX = NavX.getInstance().getNavX();
    navX.zeroYaw();

    left  = OI.getInstance().getLeftStick();
    right = OI.getInstance().getRightStick();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    error = navX.getYaw();
    turnPower = 0.1 * -error;
    drive.arcadeDrive(this.power, turnPower); 
  }

  /**
   * This command will end when the joysticks are moved;
   */
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return(left.getRawButtonPressed(1));
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drive.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drive.tankDrive(0, 0);
  }
}
