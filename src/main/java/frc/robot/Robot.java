/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;
import frc.robot.commands.auto.*;
import frc.robot.commands.claw.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.elevator.*;
import frc.robot.commands.gantry.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.rollers.*;
import frc.robot.commands.testing.*;
import frc.robot.input.*;
import frc.robot.subsystems.*;

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
  private TestingDashboard testingDashboard;
  //public static Vision vision;

  Command autoCommand;
  SendableChooser<Command> auto_chooser = new SendableChooser<>();
  CameraServer cs;
  
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
    NavX.getInstance();
    Vision.getInstance();
    Climber.getInstance();

    //Prints out our logo to the console
    //ShowLogo.ShowLogoOnStartup();

    cs = CameraServer.getInstance();
    cs.startAutomaticCapture("Front Camera", 0).setResolution(540, 360);
    cs.startAutomaticCapture("Rear Camera", 1);
    
    // Create TestingDashboard last
    testingDashboard = TestingDashboard.getInstance();

    // Add components to testing dashboard
    testingDashboard.createTestingDashboard();

  }

  public void setupAutoChooser() {
    //vision = Vision.getInstance();
    // chooser.addOption("My Auto", new MyAutoCommand());
    auto_chooser.addOption("Middle_RightRocket_2Hatch", new Middle_RightRocket_2Hatch());
    auto_chooser.addOption("Middle_LeftRocket_2Hatch", new Middle_LeftRocket_2Hatches());
    auto_chooser.addOption("Left_LeftRocket_2Hatch", new Left_LeftRocket_2Hatches());
    auto_chooser.addOption("Right_RightRocket_2Hatch", new Right_RightRocket_2Hatches());
    SmartDashboard.putData("Auto mode", auto_chooser);
    // auto_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
  }

  public void setupTestingChooser() {
    SendableChooser<Command>  testing_chooser = new SendableChooser<>();

    if (AttackThree.isInTesting){
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
      testing_chooser.addOption("Test Gantry", new DefaultGantry());
    }
    else
    {
      SmartDashboard.delete("Testing mode");
    }
    SmartDashboard.putData("Testing mode", testing_chooser);

    SmartDashboard.putBoolean("Is In Testing", AttackThree.isInTesting);
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
    SmartDashboard.putBoolean("Left Limit", Gantry.getInstance().isAtLeftLimit());
    SmartDashboard.putBoolean("Right Limit", Gantry.getInstance().isAtRightLimit());
    SmartDashboard.putNumber("Intake Pot", BallIntake.getInstance().getVoltage());
    SmartDashboard.putBoolean("Level 3 Limit Elevator", Elevator.getInstance().isAtLevelThree());
    SmartDashboard.putBoolean("Level 2 Limit Elevator", Elevator.getInstance().isAtLevelTwo());
    SmartDashboard.putBoolean("Level 1 Limit Elevator", Elevator.getInstance().isAtLevelOne());
    SmartDashboard.putBoolean("Top Switch", BallIntake.getInstance().isUp());
    SmartDashboard.putBoolean("Can Climb?", Climber.getInstance().canClimb());
    SmartDashboard.putNumber("Voltage", Climber.getInstance().getVoltage());
    SmartDashboard.putNumber("Pressure", Climber.getInstance().getCurrentPressure());


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
    autoCommand = auto_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (autoCommand != null) {
      autoCommand.start();
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
    if (autoCommand != null) {
      autoCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    testingDashboard.updateDebugTab();
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
   
  }
}
