/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.SwitchSides;
import frc.robot.commands.claw.AcquireBallFromIntake;
import frc.robot.commands.claw.CloseClaw;
import frc.robot.commands.claw.CloseClawWhenSensed;
import frc.robot.commands.claw.DeployHatch;
import frc.robot.commands.claw.OpenClaw;
import frc.robot.commands.claw.PickupHatch;
import frc.robot.commands.claw.ScoreCargo;
import frc.robot.commands.claw.ToggleForwardBack;
import frc.robot.commands.claw.ToggleOpenClose;
import frc.robot.commands.climber.BothPistonDown;
import frc.robot.commands.climber.BothPistonUp;
import frc.robot.commands.climber.SixPistonDown;
import frc.robot.commands.climber.SixPistonUp;
import frc.robot.commands.drive.ChangeHeading;
import frc.robot.commands.drive.DriveStraight;
import frc.robot.commands.elevator.*;
import frc.robot.commands.gantry.CenterGantry;
import frc.robot.commands.gantry.MoveGantryLeft;
import frc.robot.commands.gantry.MoveGantryRight;
import frc.robot.commands.gantry.StopGantry;
import frc.robot.commands.intake.IntakeUp;
import frc.robot.commands.intake.IntakeCargo;
import frc.robot.commands.intake.IntakeDown;
import frc.robot.commands.vision.AlignGantry;
import frc.robot.input.AttackThree;
import frc.robot.input.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static OI oi;

  public static AttackThree leftStick;
  public static AttackThree rightStick;
  private static XboxController xbox;
  private static XboxController xbox_climb;

   /**
   * Used outside of the OI class to return an instance of the class.
   * @return Returns instance of OI class formed from constructor.
   */
  public static OI getInstance(){
    if (oi == null){
      oi = new OI();
    }
    return oi;
  }

  public OI(){
    //User Input
    //TODO:Tune deadband
    leftStick = new AttackThree(RobotMap.U_JOYSTICK_LEFT, 0.2);
    rightStick = new AttackThree(RobotMap.U_JOYSTICK_RIGHT, 0.2);

    xbox = new XboxController(RobotMap.U_XBOX_CONTROLLER, 0.3);
    //xbox_climb = new XboxController(RobotMap.U_XBOX_CONTROLLER_CLIMB, 0.3);
    
    //Now Mapping Commands to XBox 

    //xbox.getButtonY().whenPressed(new Elevator_Rocket_L3());
    xbox.getButtonA().whenPressed(new ToggleOpenClose());
    //xbox.getButtonB().whenPressed(new AlignGantry());
    xbox.getButtonB().whenPressed(new CloseClawWhenSensed());
    //xbox.getButtonA().whenPressed(new PullClawBack());
    //xbox.getButtonA().whenPressed(new Elevator_L1());
    
    xbox.getButtonX().whenPressed(new Elevator_Rocket_L2());
    xbox.getButtonY().whenPressed(new ToggleForwardBack());

    xbox.getButtonLeftBumper().whenPressed(new PickupHatch());
    xbox.getButtonRightBumper().whenPressed(new DeployHatch());

    xbox.getDPad().getRight().whenPressed(new AcquireBallFromIntake());
    xbox.getDPad().getDown().whenPressed(new IntakeDown());
    xbox.getDPad().getLeft().whenPressed(new IntakeCargo());
    //xbox.getDPad().getDown().whenPressed(new IntakeCargo());
    //xbox.getDPad().getLeft().whenPressed(new ScoreCargo_RocketL1_Intake());
    xbox.getDPad().getUp().whenPressed(new IntakeUp());

    xbox.getButtonStart().whenPressed(new CenterGantry());
    xbox.getButtonBack().whenPressed(new ScoreCargo());
    //xbox.getButtonBack().whenPressed(new ToggleOpenClose());

    leftStick.getButton(6).whenPressed(new SwitchSides());
    rightStick.getButton(6).whenPressed(new SwitchSides());

    leftStick.getButton(2).whenPressed(new MoveGantryLeft());
    leftStick.getButton(2).whenReleased(new StopGantry());

    rightStick.getButton(2).whenPressed(new MoveGantryRight());
    rightStick.getButton(2).whenReleased(new StopGantry());

    /*
    rightStick.getButton(4).whenPressed(new BothPistonDown());
    rightStick.getButton(5).whenPressed(new BothPistonUp());
    leftStick.getButton(4).whenPressed(new BothPistonDown());
    leftStick.getButton(5).whenPressed(new BothPistonUp());
    */
    
    rightStick.getButton(10).whenPressed(new AlignGantry());

    leftStick.getButton(3).whenPressed(new DriveStraight(0.5));

    //rightStick.getButton(3).whenPressed(new DriveWhileAlign());


  }

  /**
   * Returns the left Joystick
   * @return the leftStick
   */
  public AttackThree getLeftStick() {
    return leftStick;
  }

  /**
   * Returns the right Joystick
   * @return the rightStick
   */
  public AttackThree getRightStick() {
    return rightStick;
  }

  /**
   * Returns the Xbox Controller
   * @return the Xbox Controller
   */
  public XboxController getXbox() {
      return xbox;
  }

  public XboxController getXboxClimb() {
    return xbox_climb;
}

}
