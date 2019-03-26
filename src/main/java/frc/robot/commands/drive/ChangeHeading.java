/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.NavX;
/**
 * Command that turns the robot to a certain heading.
 */
public class ChangeHeading extends PIDCommand {
 
  private PIDController pidController = getPIDController();
  private AHRS ahrs = NavX.getInstance().getNavX();
  private int countOnTarget;
  
  /**
   * Uses Drive subsystem. Sets up pidController.
   * @param target desired heading for robot, based on position set when robot was enabled (0)
   * @param speed the limiting speed of the robot while turning
   */
    public ChangeHeading(double target, double speed) {
      super(.067, .02, .2);
      requires(Drive.getInstance());
      requires(NavX.getInstance());
      NavX.getInstance().getNavX().reset();;
      countOnTarget = 0;
      setSetpoint(target);
      setInputRange(-180.0, 180.0);
      pidController.setContinuous();
      pidController.setOutputRange(-speed, speed);
      pidController.setPercentTolerance(.5);
    }

    /**
     * Returns the input for the PID controller. Called by that controller.
     */
    protected double returnPIDInput() {
      return ahrs.getYaw();
    }
    
    /**
     * Takes value given by PID controller to then turn to desired heading. Called by the
     * PID controller.
     */
    protected void usePIDOutput(double output) {
      Drive.getInstance().tankDrive(output,-output); // TODO: Are the signs still correct?
    }
    
    /**
     * Calls pidController's {@link edu.wpi.first.wpilibj.PIDController#onTarget() onTarget() method}
     * @return Boolean representing whether the robot is facing the correct heading or not
     */
    protected boolean isFinished() {
      if (pidController.onTarget()) {
        if (countOnTarget >= 3) {
          NavX.getInstance().getNavX().zeroYaw();
          return true;
        }
        countOnTarget++;

      } else {
        countOnTarget = 0;
      }
      return false;
    }
}
