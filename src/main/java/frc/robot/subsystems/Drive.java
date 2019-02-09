/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.drive.DefaultDrive;
import com.kauailabs.navx.frc.AHRS;


/**
 * The Drive subsystem: Sets up the infrastructure for the drivetrain and its hardware.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Drive drive;
  public static DifferentialDrive drivetrain;
  private VictorSP frontLeft, frontRight, backLeft, backRight;
  private SpeedControllerGroup left, right;
  private AHRS ahrs;

  /**
   * Contructor that sets up speed controllers and the drive train.
   */
  private Drive() {
    //TODO:Update with names and proper ports
    frontLeft = new VictorSP(RobotMap.D_FRONT_LEFT);
    frontRight = new VictorSP(RobotMap.D_FRONT_RIGHT);
    backLeft = new VictorSP(RobotMap.D_BACK_LEFT);
    backRight = new VictorSP(RobotMap.D_BACK_RIGHT);

    left = new SpeedControllerGroup(frontLeft, backLeft);
    right = new SpeedControllerGroup(frontRight, backRight);

    ahrs = new AHRS(RobotMap.D_NAVX);
    drivetrain = new DifferentialDrive(left, right);

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

 /**
   * Used in UserDrive command to take user input and drive with it.
   * @param leftSpeed Plug in left joystick value.
   * @param rightSpeed Plug in right joystuck value.
   */
  public void tankDrive(double leftSpeed, double rightSpeed){
    drivetrain.tankDrive(leftSpeed, rightSpeed);
  }

  /**
   * ONLY USE WITH DRIVE STRAIGHT
   * <p>
   * This makes robot drive at a given speed and heading, so it is easy to
   * write a PID loop that uses this.
   * @param speed speed of robot
   * @param angle angle at which it turns while driving
   */
  public void arcadeDrive(double speed, double angle) {
    drivetrain.arcadeDrive(speed, angle);
  }

  public AHRS getNavX() {
    return ahrs;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultDrive());
  }

}