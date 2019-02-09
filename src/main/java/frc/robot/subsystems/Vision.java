/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;

/**
 * The Vision subsystem: Sets up the infrastructure for the drivetrain and its hardware.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Vision vision;
  public static NetworkTable nt;

  /**
   * Used outside of the Vision subsystem to return an instance of Vision subsystem.
   * @return Returns instance of Vision subsystem formed from constructor.
   */
  public static Vision getInstance(){
    if (vision == null){
      vision = new Vision();
    }
    return vision;
  }
  
  public double getCX() {
    return nt.getEntry("centerx").getDouble(-1);
  }

  public double getCY() {
    return nt.getEntry("centery").getDouble(-1);
  }

  public double getArea() {
    return nt.getEntry("Area").getDouble(-1);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }

}