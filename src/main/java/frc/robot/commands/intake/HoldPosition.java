/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.BallIntake;
import frc.robot.input.XboxController;
import frc.robot.input.XboxController.*;

public class HoldPosition extends PIDCommand {
  private PIDController pidController = getPIDController();
  private BallIntake intake;
  double position;

  public HoldPosition(double position) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super(.067, .02, .1); // TODO: Tune PID inputs
    requires(BallIntake.getInstance());
    intake = BallIntake.getInstance();
    setSetpoint(position); // Balance is at 0 pitch
    setInputRange(1, 3); // Actuall about 1.8 to 2.8
    pidController.setContinuous();
    pidController.setOutputRange(-1, 1);
    pidController.setPercentTolerance(1);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    position = 1.8;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    position = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYRight);

    position = position + 2; // change -1->1 to 1->3
    SmartDashboard.putNumber("Intake position", position);
    setSetpoint(position);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    intake.setIntakeSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    intake.setIntakeSpeed(0);
  }

  @Override
  protected double returnPIDInput() {
    return intake.getVoltage();
  }

  @Override
  protected void usePIDOutput(double output) {
    intake.setIntakeSpeed(output);
  }
}
