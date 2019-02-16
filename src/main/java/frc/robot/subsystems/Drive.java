/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DefaultDrive;
import edu.wpi.first.wpilibj.Encoder;


/**
 * The Drive subsystem: Sets up the infrastructure for the drivetrain and its hardware.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Drive drive;
  public static DifferentialDrive drivetrain;
  private WPI_TalonSRX frontLeft, frontRight, backLeft, backRight;
  private SpeedControllerGroup left, right;
  Encoder encoderL, encoderR;
  final double PULSE_PER_FOOT = 4090;

  /**
   * Contructor that sets up speed controllers and the drive train.
   */
  private Drive() {
    //TODO:Update with names and proper ports
    frontLeft = new WPI_TalonSRX(RobotMap.D_FRONT_LEFT);
    frontRight = new WPI_TalonSRX(RobotMap.D_FRONT_RIGHT);
    backLeft = new WPI_TalonSRX(RobotMap.D_BACK_LEFT);
    backRight = new WPI_TalonSRX(RobotMap.D_BACK_RIGHT);

    left = new SpeedControllerGroup(frontLeft, backLeft);
    right = new SpeedControllerGroup(frontRight, backRight);

    drivetrain = new DifferentialDrive(left, right);

    encoderL = new Encoder(RobotMap.D_ENCODER_LEFT_A, RobotMap.D_ENCODER_LEFT_B);
    encoderL.setDistancePerPulse(1/PULSE_PER_FOOT);
    encoderR = new Encoder(RobotMap.D_ENCODER_RIGHT_A, RobotMap.D_ENCODER_RIGHT_B);
    encoderR.setDistancePerPulse(1/PULSE_PER_FOOT);
  }

  /**
   * Used in UserDrive command to take user input and drive with it.
   * @param leftSpeed Plug in left joystick value.
   * @param rightSpeed Plug in right joystuck value.
   */
  public void tankDrive(double leftSpeed, double rightSpeed){
    drivetrain.tankDrive(leftSpeed, rightSpeed);
  }

  /**
   * Used outside of the Drive subsystem to return an instance of Drive subsystem.
   * @return Returns instance of Drive subsystem formed from constructor.
   */
  public static Drive getInstance(){
    if (drive == null){
      drive = new Drive();
    }
    return drive;
  }

  public Encoder getRightEncoder() {
    return encoderR;
  }

  public Encoder getLeftEncoder() {
    return encoderL;
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultDrive());
  }

}