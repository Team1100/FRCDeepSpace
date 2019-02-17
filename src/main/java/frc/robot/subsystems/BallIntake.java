/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.commands.intake.DefaultIntake;

/**
 * Add your docs here.
 */
public class BallIntake extends Subsystem {

  public static BallIntake intake;
  private WPI_TalonSRX rollers, axis_movement;
  AnalogInput bottomSwitch, topSwitch;
  boolean canGoUp, canGoDown = false;
  private double motorPower;

  public static BallIntake ballintake;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private BallIntake() {
    rollers = new WPI_TalonSRX(RobotMap.B_ROLLERS);
    axis_movement = new WPI_TalonSRX(RobotMap.B_AXIS_MOVEMENT);
    bottomSwitch = new AnalogInput(RobotMap.B_BOTTOM_SWITCH);
    topSwitch = new AnalogInput(RobotMap.B_TOP_SWITCH);
    motorPower = 0;
  }

  public static BallIntake getInstance(){
    if (intake == null){
      intake = new BallIntake();
    }
    return intake;
  }

  public void intakeUp(double intakeSpeed){
    intakeSpeed = Math.abs(intakeSpeed); // enforce positive
    axis_movement.set(intakeSpeed);
    rollers.set(0);
  }

  public void intakeDown(double intakeSpeed) {
    intakeSpeed = -Math.abs(intakeSpeed); // enforce negative
  	axis_movement.set(intakeSpeed);
  }

  public void setIntakeSpeed(double intakeSpeed) {
    axis_movement.set(intakeSpeed);
  }

  public void spinRollers(double rollerSpeed) {
    rollers.set(rollerSpeed);
  }

  public boolean isUp() {
    return Math.round(topSwitch.getAverageValue()) == 1;
  }

  public boolean isDown() {
    return Math.round(bottomSwitch.getAverageValue()) == 1;
  }

  public void incrementIntakePower(double incrementPower) {
    motorPower += incrementPower;
    this.intakeDown(motorPower + incrementPower);
  }

  public void setIntakePower(double power) {
    motorPower = power;
    this.intakeDown(motorPower);
  }

  public void rollersIn(double rollersSpeed){
    spinRollers(rollersSpeed);
  }

  public void rollersOff(){
    spinRollers(0);
  }

  public void spitBall(double rollersSpeed) {
    spinRollers(rollersSpeed);
  }

  public void spinOut(double rollersSpeed) {
    rollersSpeed = -Math.abs(rollersSpeed);
    spinRollers(rollersSpeed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultIntake());
  }
}
