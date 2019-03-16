/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
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
  //public static Vision vision;

  Command autoCommand;
  SendableChooser<Command> auto_chooser = new SendableChooser<>();
  CameraServer cs;

  NetworkTableEntry isClosed, isForward, voltage, isAtLeftLimit, isAtRightLimit, isAtLevelThree, isAtLevelTwo, isAtLevelOne;
  
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

    //Prints out our logo to the console
    //ShowLogo.ShowLogoOnStartup();

    cs = CameraServer.getInstance();
    cs.startAutomaticCapture("Front Camera", 0);
    cs.startAutomaticCapture("Rear Camera", 1);
    
    //setupAutoChooser();
    createTestingDashboard();
  }

  public void createTestingDashboard() {
    // CLAW Commands
    ShuffleboardTab claw_tab = Shuffleboard.getTab("Claw");
    claw_tab.add(Claw.getInstance());
    ShuffleboardLayout claw_g1 = claw_tab.getLayout("BasicOpen", BuiltInLayouts.kList);
    claw_g1.add(new OpenClaw());
    claw_g1.add(new CloseClaw());
    ShuffleboardLayout claw_g2 = Shuffleboard.getTab("Claw").getLayout("BasicPush", BuiltInLayouts.kList);
    claw_g2.add(new PushClawForward());
    claw_g2.add(new PullClawBack());
    ShuffleboardLayout claw_g3 = Shuffleboard.getTab("Claw").getLayout("Ball", BuiltInLayouts.kList);
    claw_g3.add(new ClawAcquireBall());
    //claw_g3.add(new CloseOnBall());
    claw_g3.add(new AcquireBallFromIntake());
    claw_g3.add(new ScoreCargo());
    claw_g3.add(new LaunchBall());
    ShuffleboardLayout claw_g4 = Shuffleboard.getTab("Claw").getLayout("Hatch", BuiltInLayouts.kList);
    claw_g4.add(new PlaceHatch());
    claw_g4.add(new PickupHatch());
    claw_g4.add(new PickupHatchHPS_Vision());
    claw_g4.add(new PickupHatchHPS());
    claw_g4.add(new DeployHatch());
    ShuffleboardLayout claw_g5 = Shuffleboard.getTab("Claw").getLayout("Complex", BuiltInLayouts.kList);
    claw_g5.add(new CloseClawWhenSensed());

    // DRIVE commands
    ShuffleboardTab drive_tab = Shuffleboard.getTab("Drive");
    drive_tab.add(Drive.getInstance());
    ShuffleboardLayout drive_g1 = drive_tab.getLayout("Basic", BuiltInLayouts.kList);
    drive_g1.add(new DefaultDrive());
    drive_g1.add(new ChangeHeading(0, 0.5));
    drive_g1.add(new MeasuredDrive(0.5, 0.5));
    ShuffleboardLayout drive_g2 = drive_tab.getLayout("Climbing", BuiltInLayouts.kList);


    // INTAKE commands
    ShuffleboardTab intake_tab = Shuffleboard.getTab("Intake");
    intake_tab.add(BallIntake.getInstance());
    ShuffleboardLayout intake_g1 = intake_tab.getLayout("Basic", BuiltInLayouts.kList);
    intake_g1.add(new DefaultIntake());
    intake_g1.add(new IntakeDown());
    intake_g1.add(new IntakeUp());
    intake_g1.add(new StopIntake());
    ShuffleboardLayout intake_g2 = intake_tab.getLayout("Climbing", BuiltInLayouts.kList);
    intake_g2.add(new ClimbingIntakeDown(0.5));
    intake_g2.add(new IncrementIntakePower(0.1));
    intake_g2.add(new ClimbWhileIntake(5, 5));
    intake_g2.add(new IntakePistonDown());
    intake_g2.add(new IntakePistonUp());
    ShuffleboardLayout intake_g3 = intake_tab.getLayout("BallCommands", BuiltInLayouts.kList);
    intake_g3.add(new IntakeCargo());
    intake_g3.add(new AcquireBall());
    intake_g3.add(new ScoreCargo_Intake());
    intake_g3.add(new ScoreCargo_RocketL1_Intake());
    intake_g3.add(new Intake_Down_RocketL1());

    ShuffleboardLayout intake_g4 = intake_tab.getLayout("HatchCommands", BuiltInLayouts.kList);
    intake_g4.add(new PickupHatchHPS());

    // KICKER commands

    // ROLLERS commands
    ShuffleboardTab rollers_tab = Shuffleboard.getTab("Rollers");
    rollers_tab.add(Rollers.getInstance());
    ShuffleboardLayout rollers_g1 = rollers_tab.getLayout("Basic", BuiltInLayouts.kList);
    rollers_g1.add(new DefaultRollers());
    rollers_g1.add(new RollersIn());
    rollers_g1.add(new StopRollers());
    rollers_g1.add(new PushBallOut(5));
    rollers_g1.add(new MoveBallToChute(5));

    // VISION commands
    ShuffleboardTab vision_tab = Shuffleboard.getTab("Vision");
    vision_tab.add(Vision.getInstance());
    ShuffleboardLayout vision_g1 = vision_tab.getLayout("Basic", BuiltInLayouts.kList);


    // GANTRY commands
    ShuffleboardTab gantry_tab = Shuffleboard.getTab("Gantry");
    gantry_tab.add(Gantry.getInstance());
    ShuffleboardLayout gantry_g1 = gantry_tab.getLayout("Basic", BuiltInLayouts.kList);
    gantry_g1.add(new CenterGantry());
    gantry_g1.add(new DefaultGantry());
    gantry_g1.add(new GantryLeftLimit());
    gantry_g1.add(new GantryRightLimit());
    ShuffleboardLayout gantry_g2 = gantry_tab.getLayout("Complex", BuiltInLayouts.kList);
    gantry_g2.add(new MoveGantryToCenter());
    gantry_g2.add(new MoveToSetpoint(0.5));

    //ELEVATOR commands
    ShuffleboardTab elevator_tab = Shuffleboard.getTab("Elevator");
    elevator_tab.add(Elevator.getInstance());
    ShuffleboardLayout elevator_g1 = elevator_tab.getLayout("Basic", BuiltInLayouts.kList);
    elevator_g1.add(new DefaultElevator());
    elevator_g1.add(new Elevator_L1());
    elevator_g1.add(new Elevator_Rocket_L2());
    elevator_g1.add(new Elevator_Rocket_L3());
    ShuffleboardLayout elevator_g2 = elevator_tab.getLayout("PID", BuiltInLayouts.kList);
    elevator_g2.add(new PIDElevator(5));
    elevator_g2.add(new PIDElevatorL1());
    elevator_g2.add(new PIDElevatorL2());
    elevator_g2.add(new PIDElevatorL3());

    ShuffleboardLayout elevator_g3 = elevator_tab.getLayout("Placement", BuiltInLayouts.kList);
    elevator_g3.add(new L1_Placement());
    elevator_g3.add(new L2_Placement());
    elevator_g3.add(new L3_Placement());

    createDebugTab();
  }

  public void createDebugTab() {
    ShuffleboardTab debug_tab = Shuffleboard.getTab("Debug");
    isClosed = debug_tab.add("Claw is Closed", Claw.getInstance().isClosed()).getEntry();
    isForward = debug_tab.add("Claw is Pushed Forward", Claw.getInstance().isForward()).getEntry();
    voltage = debug_tab.add("Intake Pot", BallIntake.getInstance().getVoltage()).getEntry();
    isAtLeftLimit = debug_tab.add("Left Limit Gantry", Gantry.getInstance().isAtLeftLimit()).getEntry();
    isAtRightLimit = debug_tab.add("Right Limit Gantry", Gantry.getInstance().isAtRightLimit()).getEntry();
    debug_tab.add("Gantry Encoder", Gantry.getInstance().getEncoder());
    isAtLevelThree = debug_tab.add("Level 3 Switch", Elevator.getInstance().isAtLevelThree()).getEntry();
    isAtLevelTwo = debug_tab.add("Level 2 Switch", Elevator.getInstance().isAtLevelTwo()).getEntry();
    isAtLevelOne = debug_tab.add("Level 1 Switch", Elevator.getInstance().isAtLevelOne()).getEntry();
  }

  public void updateDebugTab() {
    isClosed.setBoolean((Claw.getInstance().isClosed()));
    isForward.setBoolean((Claw.getInstance().isForward()));
    voltage.setValue(BallIntake.getInstance().getVoltage());
    isAtLeftLimit.setBoolean(Gantry.getInstance().isAtLeftLimit());
    isAtRightLimit.setBoolean(Elevator.getInstance().isAtLevelThree());
    isAtLevelTwo.setBoolean(Elevator.getInstance().isAtLevelTwo());
    isAtLevelOne.setBoolean(Elevator.getInstance().isAtLevelOne());
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
    updateDebugTab();
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
   
  }
}
