/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Claw extends Subsystem {

  public static Claw claw;
  DoubleSolenoid clawManipulator;
  DoubleSolenoid clawMovement;
  private Boolean isClosed = true;
  private Boolean isForward = false;


  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static Claw getInstance(){
    if (claw == null){
      claw = new Claw();
    }
    return claw;
  }

  private Claw() {
    //Need to fill in parameters w/ robot map eventually
		clawManipulator = new DoubleSolenoid(RobotMap.C_Claw_CAN, RobotMap.C_Claw_0, RobotMap.C_Claw_1);	
  }
  
  public void openClaw() {
    clawManipulator.set(DoubleSolenoid.Value.kForward);
    isClosed = false;
  }

  public void closeClaw() {
    clawManipulator.set(DoubleSolenoid.Value.kReverse);
    isClosed = true;
  }

  public void pushClawForward() {
    clawMovement.set(DoubleSolenoid.Value.kForward);
    isForward = true;
  }

  public void pullClawBack() {
    clawMovement.set(DoubleSolenoid.Value.kReverse);
    isForward = false;
  }

  public boolean isClosed () {
    return isClosed();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
