/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class Kicker extends Subsystem {

  public static Kicker kicker;
  DoubleSolenoid cargoKicker;
  private Boolean isForward = false;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static Kicker getInstance(){
    if (kicker == null){
      kicker = new Kicker();
    }
    return kicker;
  }

  private Kicker() {
    //Need to fill in parameters w/ robot map eventually
		cargoKicker = new DoubleSolenoid(RobotMap.K_KICKER_CAN, RobotMap.K_KICK_OUT, RobotMap.K_KICK_IN);	
  }
  
  public void kickCargo() {
    cargoKicker.set(DoubleSolenoid.Value.kForward);
    isForward = true;
  }

  public void retractKicker() {
    cargoKicker.set(DoubleSolenoid.Value.kReverse);
    isForward = false;
  }

  public boolean getIsForward() {
    return isForward;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
