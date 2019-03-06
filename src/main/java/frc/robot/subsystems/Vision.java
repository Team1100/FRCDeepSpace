/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The Vision subsystem: Sets up the infrastructure for the drivetrain and its hardware.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Vision vision;
  public static NetworkTable nt;
  private static boolean canAim = false;
  private static boolean isAimed = false;
  public static boolean finishedAligning;
  /**
   * Used outside of the Vision subsystem to return an instance of Vision subsystem.
   * @return Returns instance of Vision subsystem formed from constructor.
   */

   private Vision(){
     nt = NetworkTableInstance.getDefault().getTable("Pi");
     finishedAligning = true;
   }
  public static Vision getInstance(){
    if (vision == null){
      vision = new Vision();
    }
    return vision;
  }
  
  public double getCX() {
    return nt.getEntry("centerx").getDouble(-1);
  }

  public void setisAimed(boolean set) {
    if(set == true) {
      isAimed = true;
    } else {
      isAimed = false;
    }
  }

  public boolean getisAimed(){
    return isAimed;
  }

  public boolean getcanAim(){
    if (Vision.getInstance().getCX() != -1) {
      canAim = true;
    }
    return canAim;
  }

  public double getCY() {
    return nt.getEntry("centery").getDouble(-1);
  }

  public double getArea() {
    return nt.getEntry("Area").getDouble(-1);
  }

  public void setFinishedAligning(boolean bool){
    finishedAligning= bool;
  }

  public boolean returnFinishedAligning(){
    return finishedAligning;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }

}
