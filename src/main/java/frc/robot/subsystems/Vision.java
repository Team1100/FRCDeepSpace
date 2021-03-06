/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The Vision subsystem: Sets up the infrastructure for the drivetrain and its hardware.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Vision vision;
  public static NetworkTable nt;
  private boolean tapeSeen = false;
  private NetworkTableEntry tapeDetected, tapeYaw, centerOfTarget, centerY;
  public static boolean finishedAligning;

  private final double KpAIM = 0.005;
  private final double KpDISTANCE = 0.025;
  private final double DEADBAND_DEGREES = 0;
  /**
   * Used outside of the Vision subsystem to return an instance of Vision subsystem.
   * @return Returns instance of Vision subsystem formed from constructor.
   */

   private Vision(){
     nt = NetworkTableInstance.getDefault().getTable("ChickenVision");

     tapeDetected = nt.getEntry("tapeDetected");
     tapeYaw = nt.getEntry("tapeYaw");
     centerOfTarget = nt.getEntry("centerOfTarget");
     centerY = nt.getEntry("centerY");
   }

   public DrivingDeltas calculateDeltas(){
     double steeringAdjust = 0;
     double distanceAdjust = 0;

     if(isTapeSeen()){
       if(Math.abs(getTargetOffset()) > DEADBAND_DEGREES){
         steeringAdjust = KpAIM * getTargetOffset();
       }

       if(Math.abs(getVerticalOffset()) > DEADBAND_DEGREES){
         distanceAdjust = KpDISTANCE * getVerticalOffset();
       }
     }

     return new DrivingDeltas(-distanceAdjust, steeringAdjust);
   }


  public static Vision getInstance(){
    if (vision == null){
      vision = new Vision();
    }
    return vision;
  }

  public double getTapeYaw(){
    return tapeYaw.getDouble(-1);
  }

  
  public boolean isTapeSeen() {
    tapeSeen = tapeDetected.getBoolean(false);
    return tapeSeen;
  }

  public double getTargetOffset(){
    return centerOfTarget.getDouble(-1);
  }

  public double getVerticalOffset(){
    return centerY.getDouble(-1);
  }

  
 
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }

}
