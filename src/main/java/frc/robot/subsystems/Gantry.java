/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Sets up the gantry subsystem. This subsytem controls the gantry that allows
 * the claw to translate left and right on the elevator for easier vision tageting.
 */
public class Gantry extends Subsystem {

  public static Gantry gantry;
  private VictorSPX gantryMotor;
  private Encoder encoder;
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
    gantryMotor = new VictorSPX(RobotMap.G_GANTRY);
    encoder = new Encoder(RobotMap.G_ENCODER_CW, RobotMap.G_ENCODER_CCW);
  }
  
  /**
   * Method for actually driving the Gantry left or right
   * @param speed Speed to drive the gantry at. Value from -1 to 1.
   */
  public void translateGantry(double speed){
    gantryMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * Method for referencing the Gantry encoder outside of the class
   * @return The instance of the gantry encoder for use outside of the class
   */
  public Encoder getEncoder(){
    return encoder;
  }

  /**
   * Unused
   */
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
