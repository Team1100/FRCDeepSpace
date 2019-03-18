/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class DrivingDeltas extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private double forwardPower;
  private double steeringPower;

  public DrivingDeltas(){
    forwardPower = 0;
    steeringPower = 0;
  }

  public DrivingDeltas(double forwardPower, double steeringPower){
    this.forwardPower = forwardPower;
    this.steeringPower = steeringPower;
  }

  public double getForwardPower(){
    return this.forwardPower;
  }

  public void setForwardPower(double forwardPower) {
    this.forwardPower = forwardPower;
  }

  public double getSteeringPower(){
    return this.steeringPower;
  }

  public void setSteeringPower(double steeringPower) {
    this.steeringPower = steeringPower;
  }
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
