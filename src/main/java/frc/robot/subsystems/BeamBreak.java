/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BeamBreak extends Subsystem {

  private static BeamBreak beamBreak;
  private DigitalInput digitalInput;

  public BeamBreak() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    digitalInput = new DigitalInput(RobotMap.B_BEAM_BREAK);
  }

  public static BeamBreak getInstance(){
    if (beamBreak == null){
      beamBreak = new BeamBreak();
    }
    return beamBreak;
  }

  public DigitalInput getDigitalInput() {
    return digitalInput;
  }

  public boolean get() {
    return digitalInput.get();
  }

  public void initDefaultCommand() {

  }

}
