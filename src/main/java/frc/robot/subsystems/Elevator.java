/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.DefaultElevator;

/**
 * The Drive subsystem: Sets up the infrastructure for the drivetrain and its hardware.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Elevator elevator;
  private VictorSP left, right;
  DigitalInput switchOne, switchTwo, switchThree, bottomSwitch;
  Encoder elevatorEncoder;
  double PULSE_PER_FOOT = 4458.75;
  boolean canGoUp = true;
  boolean canGoDown = true;


  /**
   * Contructor that sets up speed controllers.
   */
  private Elevator() {
      //TODO:Update with names and proper ports
      left = new VictorSP(RobotMap.E_LEFT);
      right = new VictorSP(RobotMap.E_RIGHT);
      bottomSwitch = new DigitalInput(RobotMap.E_BOTTOM);
      switchOne = new DigitalInput(RobotMap.E_LEVEL_ONE_SWITCH);
      elevatorEncoder = new Encoder(RobotMap.E_ENCODER_A, RobotMap.E_ENCODER_B);
      elevatorEncoder.setDistancePerPulse(1/PULSE_PER_FOOT);
      //switchTwo = new DigitalInput(RobotMap.E_LEVEL_TWO_SWITCH);
      //switchThree = new DigitalInput(RobotMap.E_LEVEL_THREE_SWITCH);

  }

  /**
   * Used to extend the Elevator at the input speed
   * @param speed Speed of the elevator
   */

  public void extend(double speed){
    if (isAtBottom()) {
      canGoDown = false;
    } else if (isAtLevelOne()) {
      canGoUp = false;
    }
    
    if (!canGoDown && speed < 0) {
      speed = 0;
    } else if (!canGoUp && speed > 0) {
      speed = 0;
    }

    left.set(speed);
    right.set(speed);
}
/**
 * Checks if the elevator is at level one
 */

  public boolean isAtLevelOne(){
    return switchOne.get();
  }

/**
 * Checks if the elevator is at level two
 */
  public boolean isAtLevelTwo(){
    return switchTwo.get();
  }
  /**
   * Checks if the elevator is at level three
   */
  public boolean isAtLevelThree(){
    return switchThree.get();
  }

  public boolean isAtBottom(){
    return bottomSwitch.get();
  }

  public Encoder getEncoder(){
    return elevatorEncoder;
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