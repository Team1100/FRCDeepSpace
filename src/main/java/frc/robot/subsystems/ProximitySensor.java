/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ProximitySensor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  DigitalInput prox;
  public static ProximitySensor proximitySensor;

  private ProximitySensor(){
    prox = new DigitalInput(RobotMap.P_PROX_SENS);
  }

  public Boolean isTriggered(){
    return prox.get();
  }

  public static ProximitySensor getInstance(){
    if(proximitySensor == null){
      proximitySensor = new ProximitySensor();
    }
    return proximitySensor;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
