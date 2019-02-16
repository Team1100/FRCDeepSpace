/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class NavX extends Subsystem {
  
  private AHRS ahrs;
  public static NavX navx;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private NavX() { 
    ahrs = new AHRS(RobotMap.D_NAVX);
  }

  public static NavX getInstance(){
    if (navx == null){
      navx = new NavX();
    }
    return navx;
  }

  public AHRS getNavX() {
    return ahrs;
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
