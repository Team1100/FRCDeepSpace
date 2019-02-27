/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.gantry.DefaultGantry;

/**
 * Add your docs here.
 */
public class Gantry extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  VictorSP gantryMotor;
  Encoder encoder;
  DigitalInput leftLimit, rightLimit;
  public static Gantry gantry;
  boolean canGoLeft = true;
  boolean canGoRight = true;

  private Gantry(){
    gantryMotor = new VictorSP(RobotMap.G_MOTOR);
    encoder = new Encoder(RobotMap.G_ENCODER_A, RobotMap.G_ENCODER_B);
    leftLimit = new DigitalInput(RobotMap.G_LIMIT_L);
    rightLimit = new DigitalInput(RobotMap.G_LIMIT_R);
  }

   /**
    * @return the encoder
    */
  public Encoder getEncoder() {
    return encoder;
  }

  public void driveGantryMotor(double speed){
    canGoLeft = true;
    canGoRight = true;
    if(isAtLeftLimit()){
      canGoLeft = false;
    }else if(isAtRightLimit()){
      canGoRight = false;
    }

    if(!canGoLeft && speed > 0){
      speed = 0;
    }else if(!canGoRight && speed < 0){
      speed = 0;
    }
    
    gantryMotor.set(speed);
  }

  public Boolean isAtLeftLimit(){
    return !leftLimit.get();
  }

  public Boolean isAtRightLimit(){
    return !rightLimit.get();
  }

  public static Gantry getInstance(){
    if (gantry == null){
      gantry = new Gantry();
    }
    return gantry;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultGantry());
  }
}
