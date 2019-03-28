/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
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
  public WPI_TalonSRX leftMaster, rightMaster, leftSlave, rightSlave;
  private SpeedControllerGroup left, right;
  Encoder encoderL, encoderR;
  final double PULSE_PER_FOOT = 4090;

  /**
   * Contructor that sets up speed controllers and the drive train.
   */
  private Drive() {
    leftMaster = new WPI_TalonSRX(RobotMap.D_FRONT_LEFT);
    rightMaster = new WPI_TalonSRX(RobotMap.D_FRONT_RIGHT);
    leftSlave = new WPI_TalonSRX(RobotMap.D_BACK_LEFT);
    rightSlave = new WPI_TalonSRX(RobotMap.D_BACK_RIGHT);

    leftMaster.configContinuousCurrentLimit(45);
    rightMaster.configContinuousCurrentLimit(45);
    leftSlave.configContinuousCurrentLimit(45);
    rightSlave.configContinuousCurrentLimit(45);

    leftMaster.enableCurrentLimit(true);
    rightMaster.enableCurrentLimit(true);
    leftSlave.enableCurrentLimit(true);
    rightSlave.enableCurrentLimit(true);

    left = new SpeedControllerGroup(leftMaster, leftSlave);
    right = new SpeedControllerGroup(rightMaster, rightSlave);

    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);

    leftSlave.setInverted(InvertType.FollowMaster);
    rightSlave.setInverted(InvertType.FollowMaster);

    leftMaster.configFactoryDefault();
    rightMaster.configFactoryDefault();

    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

    leftMaster.setSensorPhase(true);//TODO: TUNE
    rightMaster.setSensorPhase(true);

    leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
    leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
    rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
    rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

    leftMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
    leftMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
    rightMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
    rightMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);

    leftMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
    leftMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    rightMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
    rightMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    leftMaster.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		leftMaster.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		leftMaster.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		leftMaster.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
    leftMaster.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);

    rightMaster.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		rightMaster.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		rightMaster.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		rightMaster.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
    rightMaster.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);

    leftMaster.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
    leftMaster.configMotionAcceleration(6000, Constants.kTimeoutMs);

    rightMaster.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
    rightMaster.configMotionAcceleration(6000, Constants.kTimeoutMs);

    leftMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    rightMaster.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

    drivetrain = new DifferentialDrive(leftMaster, rightMaster);

    /*
    encoderL = new Encoder(RobotMap.D_ENCODER_LEFT_A, RobotMap.D_ENCODER_LEFT_B);
    encoderL.setDistancePerPulse(1/PULSE_PER_FOOT);
    encoderR = new Encoder(RobotMap.D_ENCODER_RIGHT_A, RobotMap.D_ENCODER_RIGHT_B);
    encoderR.setDistancePerPulse(1/PULSE_PER_FOOT);
    */
  }

  /**
   * Used in UserDrive command to take user input and drive with it.
   * @param leftSpeed Plug in left joystick value.
   * @param rightSpeed Plug in right joystuck value.
   */
  public void tankDrive(double leftSpeed, double rightSpeed){
    drivetrain.tankDrive(leftSpeed, rightSpeed);
  }


  public void setLeftSpeed(double speed) {
    this.left.set(speed);
  }

  public void setRightSpeed(double speed) {
    this.left.set(speed);
  }

  public void arcadeDrive(double staightSpeed, double turnModifer) {
    this.setLeftSpeed(-(staightSpeed - turnModifer));
    this.setRightSpeed(staightSpeed + turnModifer);
  }

  public void visionArcadeDrive(DrivingDeltas drivingDeltas) {
    arcadeDrive(drivingDeltas.getForwardPower(), drivingDeltas.getSteeringPower());
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

  public float getYaw(){
    return NavX.getInstance().getNavX().getYaw();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultDrive());
  }

}