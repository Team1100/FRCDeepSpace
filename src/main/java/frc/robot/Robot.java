/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.drive.ChangeHeading;
import frc.robot.commands.drive.DefaultDrive;
import frc.robot.commands.drive.TestLeftDrive;
import frc.robot.commands.drive.TestRightDrive;
import frc.robot.commands.elevator.DefaultElevator;
import frc.robot.commands.elevator.TestLeftElevator;
import frc.robot.commands.elevator.TestRightElevator;
import frc.robot.commands.gantry.MoveGantry;
import frc.robot.commands.intake.DefaultIntake;
import frc.robot.commands.intake.IntakeCargo;
import frc.robot.commands.intake.IntakeDown;
import frc.robot.commands.intake.IntakeUp;
import frc.robot.commands.intake.RollersIn;
import frc.robot.commands.intake.StopRollers;
import frc.robot.commands.stilts.TestBothStilts;
import frc.robot.commands.stilts.TestLeftStilt;
import frc.robot.commands.stilts.TestRightStilt;
import frc.robot.commands.stilts.TestRightStilt;
import frc.robot.commands.stilts.TestRightStilt;
import frc.robot.commands.auto.*;
import frc.robot.commands.claw.CloseClaw;
import frc.robot.commands.claw.OpenClaw;
import frc.robot.commands.claw.PullClawBack;
import frc.robot.commands.claw.PushClawForward;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BeamBreak;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Stilts;
import frc.robot.subsystems.Gantry;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.NavX;
import frc.robot.input.AttackThree; 
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;
  public static PowerDistributionPanel pdp;
  public static Vision vision;

  Command m_autonomousCommand, testing_chooser_command;
  SendableChooser<Command>  m_chooser = new SendableChooser<>();
  SendableChooser<Command>  testing_chooser = new SendableChooser<>();
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    /**
     * Ensures that instances of each subsytem are created, which is necessary for the robot to work.
     */
    OI.getInstance();
    BallIntake.getInstance();
    BeamBreak.getInstance();
    Claw.getInstance();
    Drive.getInstance();
    Elevator.getInstance();
    Gantry.getInstance();
    Stilts.getInstance();
    NavX.getInstance();

    vision = Vision.getInstance();
    m_chooser.setDefaultOption("Default Auto", new TestAutoPathCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    m_chooser.addOption("Middle_RightRocket_2Hatch", new Middle_RightRocket_2Hatch());
    m_chooser.addOption("Middle_LeftRocket_2Hatch", new Middle_LeftRocket_2Hatches());
    m_chooser.addOption("Left_LeftRocket_2Hatch", new Left_LeftRocket_2Hatches());
    m_chooser.addOption("Right_RightRocket_2Hatch", new Right_RightRocket_2Hatches());
    m_chooser.addOption("Roll Rollers", new RollersIn());
    m_chooser.addOption("Intake Cargo", new IntakeCargo());

    SmartDashboard.putData("Auto mode", m_chooser);
    SmartDashboard.putData("ChangeHeading", new ChangeHeading(90,0.5));
    
    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    testing_chooser.addOption("Test Both Drive", new DefaultDrive());
    testing_chooser.addOption("Test Right Drive", new TestLeftDrive());
    testing_chooser.addOption("Test Left Drive", new TestRightDrive());
    testing_chooser.addOption("Test Both Elevators", new DefaultElevator());
    testing_chooser.addOption("Test Right Elevator", new TestRightElevator());
    testing_chooser.addOption("Test Left Elevator", new TestLeftElevator());
    testing_chooser.addOption("Test RollersIn", new RollersIn());
    testing_chooser.addOption("Test Pistons Open", new OpenClaw());
    testing_chooser.addOption("Test Pistons Close", new CloseClaw());
    testing_chooser.addOption("Test Push Claw Forward", new PushClawForward());
    testing_chooser.addOption("Test Pull Claw Back", new PullClawBack());
    testing_chooser.addOption("Test Intake Up", new IntakeUp());
    testing_chooser.addOption("Test Intake Down", new IntakeDown());
    testing_chooser.addOption("Test Both Stilts", new TestBothStilts());
    testing_chooser.addOption("Test Right Stilt", new TestLeftStilt());
    testing_chooser.addOption("Test Left Stilt", new TestRightStilt());
    testing_chooser.addOption("Test Gantry", new MoveGantry());

    SmartDashboard.putData("Testing Functionality", testing_chooser);

    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    NavX.getInstance().getNavX().zeroYaw();
    m_autonomousCommand = m_chooser.getSelected();
    System.out.println(m_autonomousCommand.toString());
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    testing_chooser_command = testing_chooser.getSelected();

    if(testing_chooser_command != null){
      testing_chooser_command.start();
    }

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
