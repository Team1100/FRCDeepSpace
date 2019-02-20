/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.rollers.*;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.commands.intake.DefaultIntake;

/**
 * Add your docs here.
 */
public class Rollers extends Subsystem {

  public static Rollers rollers;
  private VictorSPX rollersMotors;
  private BeamBreak bb;
  VictorSPX axis_movement_right;
  VictorSPX axis_movement_left;
  //DigitalInput topSwitch;
  boolean canGoUp, canGoDown = false;
  private double motorPower;

  public static BallIntake ballintake;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Rollers() {
    rollersMotors = new VictorSPX(RobotMap.B_ROLLERS);
    //topSwitch = new DigitalInput(RobotMap.B_TOP_SWITCH);
    motorPower = 0;
    bb = BeamBreak.getInstance();
  }

  public static Rollers getInstance(){
    if (rollers == null){
      rollers = new Rollers();
    }
    return rollers;
  }

  public void spinRollers(double rollerSpeed) {
    rollersMotors.set(ControlMode.PercentOutput, rollerSpeed);
  }


  //public boolean isUp() {
    //return topSwitch.get();
  //}



  public void rollersIn(double rollersSpeed){
    spinRollers(-rollersSpeed);
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

  public boolean ballIsIn(){
    return bb.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultRollers());
  }
}
