/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.DefaultElevator;

/**
 * The Drive subsystem: Sets up the infrastructure for the drivetrain and its hardware.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Elevator elevator;
  private VictorSPX left, right;
  DigitalInput bottomSwitch, topSwitch;
  Encoder encoder;
  double bottom;
  double top;
  boolean canGoUp = true;
  boolean canGoDown = true;


  /**
   * Contructor that sets up speed controllers.
   */
  private Elevator() {
      //TODO:Update with names and proper ports
      left = new VictorSPX(RobotMap.E_LEFT);
      right = new VictorSPX(RobotMap.E_RIGHT);
      bottomSwitch = new DigitalInput(RobotMap.E_BOTTOM_SWITCH);
      topSwitch = new DigitalInput(RobotMap.E_TOP_SWITCH);
      encoder = new Encoder(RobotMap.E_ENCODER_A, RobotMap.E_ENCODER_B);
      top = 0.6;
		  bottom = 4.2;
  }

  /**
   * Used to extend the Elevator at the input speed
   * @param speed Speed of the elevator
   */
  public void extend(double speed){
    //TODO:Implement safeties using limits
    /*
    if (isAtBottom()) {
      canGoDown = false;
    } else if (isAtTop()) {
      canGoUp = false;
    }
    
    if (speed < 0) {
      canGoDown = true;
    } else if (speed > 0) {
      canGoUp = true;
    }
    
    if (!canGoDown && speed > 0) {
      speed = 0;
    } else if (!canGoUp && speed < 0) {
      speed = 0;
    }
    */
    left.set(ControlMode.PercentOutput, speed);
    right.set(ControlMode.PercentOutput, speed);
  }

/**
 * Checks if the elevator is at level one
 */
  public boolean isAtBottom(){
    return bottomSwitch.get();
  }

/**
 * Checks if the elevator is at level two
 */
public boolean isAtTop(){
  return topSwitch.get();
}

/**
 * Gets the lowest height possible
 * @return Lowest height of elevator
 */
public double getBottom() {
  return bottom;
}

/**
 * Gets the highest height possible
 * @return Highest height of elevator
 */
public double getTop() {
  return top;
}

public Encoder getEncoder(){
  return encoder;
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