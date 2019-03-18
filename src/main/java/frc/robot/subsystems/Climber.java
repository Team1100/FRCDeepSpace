/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  public static Climber climber;
  DoubleSolenoid pistonSix;
  DoubleSolenoid pistonEight;

  Solenoid pistonSix2;
  Solenoid pistonEight2;
  private Boolean isUp6 = true;
  private Boolean isUp8 = true;


  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static Climber getInstance(){
    if (climber == null){
      climber = new Climber();
    }
    return climber;
  }

  private Climber() {
    //Need to fill in parameters w/ robot map eventually
    pistonSix = new DoubleSolenoid(RobotMap.L_LIFTER_CAN, RobotMap.L_LIFT_UP_6, RobotMap.L_LIFT_DOWN_6);	
    pistonEight = new DoubleSolenoid(RobotMap.L_LIFTER_CAN, RobotMap.L_LIFT_UP_8, RobotMap.L_LIFT_DOWN_8);

    //pistonSix2 = new Solenoid(RobotMap.L_LIFTER_CAN2, RobotMap.L_LIFT_6);	
    //pistonEight2 = new Solenoid(RobotMap.L_LIFTER_CAN2, RobotMap.L_LIFT_8);
  }
  
  public void liftSix() {
    pistonSix.set(DoubleSolenoid.Value.kForward);
    //pistonSix2.set(true);
    isUp6 = true;
  }

  public void extendSix() {
    pistonSix.set(DoubleSolenoid.Value.kReverse);
    //pistonSix2.set(false);
    isUp6 = false;
  }

  public void liftBoth() {
    pistonSix.set(DoubleSolenoid.Value.kForward);
    pistonEight.set(DoubleSolenoid.Value.kForward);
    //pistonSix2.set(true);
    //pistonEight2.set(true);
    isUp6 = true;
    isUp8 = true;
  }

  public void extendBoth() {
    pistonSix.set(DoubleSolenoid.Value.kReverse);
    pistonEight.set(DoubleSolenoid.Value.kReverse);
    //pistonSix2.set(false);
    //pistonEight2.set(false);

    isUp6 = false;
    isUp8 = false;
  }

  public boolean isUp6 () {
    return isUp6();
  }

  public boolean isUp8() {
    return isUp8();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
