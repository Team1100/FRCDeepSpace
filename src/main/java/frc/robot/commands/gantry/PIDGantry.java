/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.gantry;

import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Gantry;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * PIDElevator uses the Elevator subsystem and is based on the 
 * PIDCommand class. An encoder is used to sense the 
 * height. 
 */
public class PIDGantry extends PIDCommand {
  private PIDController pidController = getPIDController();

  Gantry gantry;
  int countOnTarget = 0;

  /**
   * Sets up PID controller, setpoint, and PID values
   * @param position the desired position of the gantry, as a percent (0.0 to 1.0)
   */
  public PIDGantry(double position) {
    super(2.0,0.4,2.0);
    requires(Gantry.getInstance());
    gantry = Gantry.getInstance();
    setInputRange(0 ,1); 
    setSetpoint(position);
    pidController.setOutputRange(-1, 1);
    pidController.setPercentTolerance(0.4);
  }

  /**
   * Sets speed of elevator to 0
   */
  protected void initialize() {
    gantry.driveGantryMotor(0);
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
    gantry.driveGantryMotor(0);
  }

  /**
   * Sets speed of elevator to 0
   */
  protected void interrupted() {
    gantry.driveGantryMotor(0);
  }


  /**
   * Puts height of elevator to SmartDashboard
   * @return height of robot/2, therefore the height as a percent
   */
  @Override
  protected double returnPIDInput() {
      return gantry.getEncoder().getDistance();
  }

  /**
   * Sets speed of elevator to output of PID loop
   */
  @Override
  protected void usePIDOutput(double output) {
      gantry.driveGantryMotor(-output);
  }
}