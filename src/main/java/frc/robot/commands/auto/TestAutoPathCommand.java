/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
package frc.robot.commands.auto;

import java.io.File;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drive;

public class TestAutoPathCommand extends Command {

  EncoderFollower leftFollower;
  EncoderFollower rightFollower;

  Drive chassis = Drive.getInstance();

  File leftCSV;
  File rightCSV;

  Trajectory rightTrajectory;
  Trajectory leftTrajectory;

  Notifier notifier;

  double dt;

  double p = 0.8;
  double i = 0.;
  double d = 0;
  double v = 1 / 12;
  double a = 0;
  
  
  public TestAutoPathCommand() {
    requires(Drive.getInstance());

    leftCSV = new File("/home/deploy/" + pathName + ".left");
    rightCSV = new File("/home/deploy/" + pathName + ".right");

    leftTrajectory = Pathfinder.readFromCSV(leftCSV);
    rightTrajectory = Pathfinder.readFromCSV(rightCSV);

    notifier = new Notifier(new RunProfile());
    dt = leftTrajectory.get(0).dt;

    System.out.println("CSV has been locked and loaded");
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    leftFollower = new EncoderFollower(leftTrajectory);
    rightFollower = new EncoderFollower(rightTrajectory);

    leftFollower.reset();
    rightFollower.reset();

    leftFollower.configureEncoder((int)Drive.getEncoderLeft(), 128, 6 / 12);
    rightFollower.configureEncoder((int)Drive.getEncoderRight(), 128, 6 / 12);

    leftFollower.configurePIDVA( p,  i, d, v, a);
    rightFollower.configurePIDVA(p , i, d , v , a);
    notifier.startPeriodic(dt);

    System.out.println("Initialized");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    System.out.println("Finished");
    return leftFollower.isFinished() && rightFollower.isFinished();
    //return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    notifier.stop();
    chassis.tankDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
  class RunProfile implements java.lang.Runnable{
    int segmentNumber = 0;
    
    @Override
    public void run(){
    
    
    double leftOutput = leftFollower.calculate((int) chassis.getEncoderLeft());
    double rightOutput = rightFollower.calculate((int) chassis.getEncoderRight());
    
    double gyroHeading = 0;
    
    double desiredHeading = Pathfinder.d2r(leftFollower.getHeading());
    
    double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
    
    double turn = 0.08 *  (-1. / 80.) * angleDifference;
    
    chassis.tankDrive(leftOutput - turn, rightOutput - turn);
    
    segmentNumber++;
    }
  }
}
*/