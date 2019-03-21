/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import frc.robot.subsystems.*;
import frc.robot.commands.auto.*;
import frc.robot.commands.claw.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.elevator.*;
import frc.robot.commands.gantry.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.rollers.*;
import frc.robot.commands.testing.*;

/**
 * This class sets up a testing dashboard using
 * WPILib's ShuffleBoard. The testing dashboard
 * contains a single tab for every subsystem
 * containing the subsystem status and all
 * commands associated with that subsystem.
 * 
 * There is also a debug tab that contains sensor
 * variables and debug values.
 * 
 * This class is a singleton that should be
 * instantiated in the robotInit method
 * of the Robot class.
 */
public class TestingDashboard {
    private static TestingDashboard testingDashboard;

    // Sensor variables
    NetworkTableEntry isClosed, isForward, voltage, isAtLeftLimit, isAtRightLimit, isAtLevelThree, isAtLevelTwo, isAtLevelOne;

    // Debug variables
    public NetworkTableEntry intakePosition;

    private TestingDashboard() {
    }

    public static TestingDashboard getInstance() {
        if (testingDashboard == null) {
            testingDashboard = new TestingDashboard();
        }
        return testingDashboard;
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
        intake_g2.add(new HoldPosition(2.0));
        intake_g2.add(new ClimbingIntakeDown(0.5));
        intake_g2.add(new IncrementIntakePower(0.1));
        intake_g2.add(new ClimbWhileIntake(5, 5));
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
        intakePosition = debug_tab.add("Intake Position", 1.8).getEntry();
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
}
