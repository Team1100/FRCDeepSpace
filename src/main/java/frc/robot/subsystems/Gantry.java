/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.gantry.DefaultGantry;

/**
 * Sets up the gantry subsystem. This subsytem controls the gantry that allows
 * the claw to translate left and right on the elevator for easier vision tageting.
 */
public class Gantry extends Subsystem {

  public static Gantry gantry;
  public static final double PULSES_PER_UNIT = 2176.25;
  VictorSPX gantryMotor;
  Encoder encoder;
  DigitalInput leftLimit, rightLimit;
  boolean canGoLeft = true;
  boolean canGoRight = true;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static Gantry getInstance(){
    if (gantry == null){
      gantry = new Gantry();
    }
    return gantry;
  }

  /**
   * Sets up motors and encoders that are present on the Gantry
   */
  private Gantry() {
    gantryMotor = new VictorSPX(RobotMap.G_MOTOR);
    encoder = new Encoder(RobotMap.G_ENCODER_A, RobotMap.G_ENCODER_B);
    leftLimit = new DigitalInput(RobotMap.G_LIMIT_L);
    rightLimit = new DigitalInput(RobotMap.G_LIMIT_R);
    encoder.setDistancePerPulse(1/PULSES_PER_UNIT);

  }

   /**
    * @return the encoder
    */
  public Encoder getEncoder() {
    return encoder;
  }

  /**
   * Method for actually driving the Gantry left or right
   * @param speed Speed to drive the gantry at. Value from -1 to 1.
   */
  public void driveGantryMotor(double speed){
    canGoLeft = true;
    canGoRight = true;
    if(isAtLeftLimit()){
      canGoLeft = false;
      encoder.reset();
    }else if(isAtRightLimit()){
      canGoRight = false;
    }

    if(!canGoLeft && speed > 0){
      speed = 0;
    }else if(!canGoRight && speed < 0){
      speed = 0;
    }
    
    gantryMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Checks if the gantry has reached the left side
   */
  public Boolean isAtLeftLimit(){
    return !leftLimit.get();
  }

  /**
   * Checks if the gantry has reached the right side
   */
  public Boolean isAtRightLimit(){
    return !rightLimit.get();
  }

  public double calculateGantryPosition(){
    double encoderPos = 0.5;
    double cx = Vision.getInstance().getCX();
    double percentage = 0.5;

    if(Vision.getInstance().getcanAim()){
      percentage = cx/640;
    }

    encoderPos = percentage;

    return encoderPos;
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultGantry());
  }
}
