/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.input.XboxController;
import frc.robot.input.XboxController.*;
import frc.robot.subsystems.BallIntake;

/**
 * This command allows for operator control of the intake
 * It allows the operator to use the left joystick (scheduled to change) to control the speed
 * and direction of the intake.
 */
public class DefaultIntake extends Command {
  double speed;
  XboxAxis rightJoystickY = XboxAxis.kYRight;
  public DefaultIntake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(BallIntake.getInstance());

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    BallIntake.getInstance().setIntakeSpeed(0);
    speed = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    speed = 0;
    if(Math.abs(OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYRight)) > .05) {
      speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYRight);
    }
    else if(Math.abs(OI.getInstance().getXboxClimb().getAxis(XboxController.XboxAxis.kYRight)) > .05) {
      speed = OI.getInstance().getXboxClimb().getAxis(XboxController.XboxAxis.kYRight);
    }

    BallIntake.getInstance().setIntakeSpeed(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
