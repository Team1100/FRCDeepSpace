/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotMap;

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
  double PULSE_PER_FOOT = 4458.75;
  boolean canGoUp = true;
  boolean canGoDown = true;
  double bottom;
  double top;


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
    encoder.setDistancePerPulse(1/PULSE_PER_FOOT);
    bottom = 0;
    top = 7;

  }

  /**
   * Used to extend the Elevator at the input speed
   * @param speed Speed of the elevator
   */

  public void extend(double speed){
    canGoDown = true;
    canGoUp = true;

    if (isAtBottom()) {
      canGoDown = false;
      bottom = encoder.getDistance();
    } else if (isAtTop()) {
      canGoUp = false;
      top = encoder.getDistance();
    }
    
    if (!canGoDown && speed < 0) {
      speed = 0;
    } else if (!canGoUp && speed > 0) {
      speed = 0;
    }
    left.set(ControlMode.PercentOutput, -speed);
    right.set(ControlMode.PercentOutput, -speed);
  }

  public void extendLeft(double speed){
    left.set(ControlMode.PercentOutput, -speed);
  }

  public void extendRight(double speed){
    right.set(ControlMode.PercentOutput, -speed);
  }
  /**
   * Checks if the elevator is at level one
   */
  public boolean isAtBottom(){
    return bottomSwitch.get();
  }

  public Encoder getEncoder(){
    return encoder;
  }


  /**
   * Checks if the elevator is at level two
   */
  public boolean isAtTop(){
    return topSwitch.get();
  }

  /**
   * Gets the highest height possible
   * @return Highest height of elevator
   */
  public double getTop() {
    return top;
  }

  /**
   * Gets the lowest height possible
   * @return Lowest height of elevator
   */
  public double getBottom(){
    return bottom;
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
    //setDefaultCommand(new DefaultElevator());
  }
}
