/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.intake.DefaultIntake;

/**
 * Add your docs here.
 */
public class BallIntake extends Subsystem {

  public static BallIntake intake;
  //private VictorSPX rollers;
  private BeamBreak bb;
  VictorSPX axis_movement_right;
  VictorSPX axis_movement_left;
  DigitalInput topSwitch;
  AnalogInput pot;
  boolean canGoUp, canGoDown = false;
  private double motorPower;

  public static BallIntake ballintake;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private BallIntake() {
  //  rollers = new VictorSPX(RobotMap.B_ROLLERS);
    axis_movement_left = new VictorSPX(RobotMap.B_AXIS_MOVEMENT_LEFT);
    axis_movement_right = new VictorSPX(RobotMap.B_AXIS_MOVEMENT_RIGHT);
    //topSwitch = new DigitalInput(RobotMap.B_TOP_SWITCH);
    motorPower = 0;
    bb = BeamBreak.getInstance();
    topSwitch = new DigitalInput(RobotMap.B_TOP_SWITCH);
    //pot = new AnalogInput(RobotMap.B_POT);
  }

  public static BallIntake getInstance(){
    if (intake == null){
      intake = new BallIntake();
    }
    return intake;
  }

  public void intakeUp(double intakeSpeed){
    //intakeSpeed = Math.abs(intakeSpeed); // enforce positive
    axis_movement_left.set(ControlMode.PercentOutput, intakeSpeed);
    axis_movement_right.set(ControlMode.PercentOutput, -intakeSpeed);
  }

  public void intakeDown(double intakeSpeed) {
    //intakeSpeed = -Math.abs(intakeSpeed); // enforce negative
    axis_movement_left.set(ControlMode.PercentOutput, intakeSpeed);
    axis_movement_right.set(ControlMode.PercentOutput, -intakeSpeed);
  }

  public void setIntakeSpeed(double intakeSpeed) {
    axis_movement_left.set(ControlMode.PercentOutput, intakeSpeed);
    axis_movement_right.set(ControlMode.PercentOutput, -intakeSpeed);
  }

 // public void spinRollers(double rollerSpeed) {
   // rollers.set(ControlMode.PercentOutput, rollerSpeed);
  //}


  public boolean isUp() {
    return topSwitch.get();
  }



  public void incrementIntakePower(double incrementPower) {
    motorPower += incrementPower;
    this.intakeDown(motorPower + incrementPower);
  }

  public void setIntakePower(double power) {
    motorPower = power;
    this.intakeDown(motorPower);
  }

  public double getVoltage() {
    return pot.getAverageVoltage();
  }

  //public void rollersIn(double rollersSpeed){
   // spinRollers(-rollersSpeed);
  //}

 // public void rollersOff(){
   // spinRollers(0);
  //}

  //public void spitBall(double rollersSpeed) {
    //spinRollers(rollersSpeed);
  //}

 // public void spinOut(double rollersSpeed) {
   // rollersSpeed = -Math.abs(rollersSpeed);
   // spinRollers(rollersSpeed);
  //}

  public boolean ballIsIn(){
    return bb.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultIntake());
  }
}
