/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.intake;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * PIDElevator uses the Elevator subsystem and is based on the 
 * PIDCommand class. An encoder is used to sense the 
 * height. 
 */
public class PIDClimb extends PIDCommand {
  private PIDController pidController = getPIDController();

  double speed;
  BallIntake intake;
  NavX navx;
  int countOnTarget = 0;

  /**
   * Sets up PID controller, setpoint, and PID values
   * @param height the desired height of the elevator, as a percent (0.0 to 1.0). Please stay away from the endpoints
   */
  public PIDClimb(double height) {
    super(2.5,.4,1);
    requires(BallIntake.getInstance());
    requires(NavX.getInstance());
    intake = BallIntake.getInstance();
    navx = NavX.getInstance();

  }

  /**
   * Sets speed of elevator to 0
   */
  protected void initialize() {
    intake.setIntakeSpeed(0);
    setSetpoint(0);
    pidController.setOutputRange(-1, 1);
    pidController.setPercentTolerance(0.2);
  }

  /**
   * Gets state of PID loop
   * @return whether elevator is "close enough" to setpoint
   */
  protected boolean isFinished() {
    if (pidController.onTarget()) {
      if (countOnTarget == 3) {
        return true;
      }
      countOnTarget++;

    } else {
      countOnTarget = 0;
    }
    return false;
  }

  /**
   * Sets speed of elevator to 0
   */
  protected void end() {
    intake.setIntakeSpeed(0);
  }

  /**
   * Sets speed of elevator to 0
   */
  protected void interrupted() {
    intake.setIntakeSpeed(0);
  }


  /**
   * Puts height of elevator to SmartDashboard
   * @return height of robot/2, therefore the height as a percent
   */
  @Override
  protected double returnPIDInput() {
      return navx.getNavX().getPitch();
  }

  /**
   * Sets speed of elevator to output of PID loop
   */
  @Override
  protected void usePIDOutput(double output) {
      intake.setIntakeSpeed(output);
  }
}
