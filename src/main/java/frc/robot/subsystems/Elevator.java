/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.DefaultElevator;

/**
 * The Drive subsystem: Sets up the infrastructure for the drivetrain and its hardware.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Elevator elevator;
  private WPI_TalonSRX left, right;

  /**
   * Contructor that sets up speed controllers.
   */
  private Elevator() {
      //TODO:Update with names and proper ports
      left = new WPI_TalonSRX(RobotMap.E_LEFT);
      right = new WPI_TalonSRX(RobotMap.E_RIGHT);
  }

  /**
   * Used to extend the Elevator at the input speed
   * @param speed Speed of the elevator
   */

  public void extend(double speed){
    //TODO:Implement safeties using limits
    left.set(speed);
    right.set(speed);
}

  /**
   * Used outside of the Elevator subsystem to return an instance of Elevator subsystem.
   * @return Returns instance of Elevator subsystem formed from constructor.
   */
  public static Elevator getInstance(){
    if (elevator == null){
      elevator = new Elevator();
    }
    return elevator;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultElevator());
  }

}