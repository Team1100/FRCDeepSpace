/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stilts;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Stilts;

public class BalanceStilts extends PIDCommand {
  private PIDController pidController = getPIDController();
  private int countOnTarget;
  private AHRS ahrs;
  private Stilts stilts;

  	/**
	  * Uses Stilts subsystem. Sets up pidController.
	  * @param speed the limiting speed of the robot while balancing
	  */
  public BalanceStilts(double speed) {
    super(.067, .02, .1); // TODO: Tune PID inputs
    requires(Stilts.getInstance());
    requires(NavX.getInstance());
    ahrs = NavX.getInstance().getNavX();
    stilts = Stilts.getInstance();
    countOnTarget = 0;
    setSetpoint(0); // Balance is at 0 pitch
    setInputRange(-180.0, 180.0);
    pidController.setContinuous();
    pidController.setOutputRange(0, Math.abs(speed));
    pidController.setPercentTolerance(1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (pidController.onTarget()) {
      if (countOnTarget >= 3) {
        return true;
      }
      countOnTarget++;
    } else {
      countOnTarget = 0;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    stilts.leftExtend(0);
    stilts.rightExtend(0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    stilts.leftExtend(0);
    stilts.rightExtend(0);
  }

  @Override
  protected double returnPIDInput() {
    return ahrs.getPitch();
  }

  @Override
  protected void usePIDOutput(double output) {
    stilts.leftExtend(0);
    stilts.rightExtend(0);
  }
}
