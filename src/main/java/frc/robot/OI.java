/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.SwitchSides;
import frc.robot.commands.claw.CloseClaw;
import frc.robot.commands.claw.CloseClawWhenSensed;
import frc.robot.commands.claw.CloseOnBall;
import frc.robot.commands.claw.DeployHatch;
import frc.robot.commands.claw.OpenClaw;
import frc.robot.commands.claw.PickupHatch;
import frc.robot.commands.claw.PickupHatchHPS;
import frc.robot.commands.claw.PlaceHatch;
import frc.robot.commands.claw.PullClawBack;
import frc.robot.commands.claw.PushClawForward;
import frc.robot.commands.drive.ChangeHeading;
import frc.robot.commands.elevator.*;
import frc.robot.commands.intake.IntakeUp;
import frc.robot.commands.intake.ScoreCargo_Intake;
import frc.robot.commands.intake.ScoreCargo_RocketL1_Intake;
//import frc.robot.commands.intake.ScoreCargo_RocketL1;
import frc.robot.commands.intake.IntakeCargo;
import frc.robot.commands.intake.IntakeDown;
import frc.robot.commands.rollers.MoveBallToChute;
import frc.robot.commands.rollers.RollersIn;
import frc.robot.commands.rollers.StopRollers;
import frc.robot.commands.vision.AlignGantry;
import frc.robot.commands.vision.CenterRobot;
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

    xbox = new XboxController(RobotMap.U_XBOX_CONTROLLER, 0.1);
    
    //Now Mapping Commands to XBox 

    xbox.getButtonY().whenPressed(new Elevator_Rocket_L3());
    xbox.getButtonX().whenPressed(new Elevator_Rocket_L2());
    xbox.getButtonB().whenPressed(new AlignGantry());
    xbox.getButtonA().whenPressed(new Elevator_L1());

    xbox.getButtonBack().whenPressed(new PushClawForward());
    xbox.getButtonStart().whenPressed(new PullClawBack());

    xbox.getButtonLeftBumper().whenPressed(new PickupHatch());
    xbox.getButtonRightBumper().whenPressed(new DeployHatch());

    xbox.getDPad().getRight().whenPressed(new MoveBallToChute(2));
    xbox.getDPad().getDown().whenPressed(new IntakeCargo());
    xbox.getDPad().getLeft().whenPressed(new ScoreCargo_RocketL1_Intake());
    xbox.getDPad().getUp().whenPressed(new IntakeUp());

    /*
    xbox.getButtonY().whenPressed(new OpenClaw());
    xbox.getButtonX().whenPressed(new CloseClaw());
    xbox.getButtonB().whenPressed(new PushClawForward());
    xbox.getButtonA().whenPressed(new PullClawBack());

    xbox.getButtonStart().whenPressed(new AlignGantry());
    xbox.getButtonBack().whenPressed(new CenterRobot(5));
    xbox.getDPad().getRight().whenPressed(new PickupHatch());
    xbox.getDPad().getDown().whenPressed(new Elevator_L1());
    xbox.getDPad().getLeft().whenPressed(new Elevator_Rocket_L2());
    xbox.getDPad().getUp().whenPressed(new Elevator_Rocket_L3());
    */

    //xbox.getButtonLeftBumper().whenPressed(new CloseOnBall());
    //xbox.getButtonRightBumper().whenPressed(new DeployHatch());
    //xbox.getButtonBack().whenPressed(new MoveToSetpoint(0.6));

    /*
    xbox.getButtonY().whenPressed(new PIDElevatorL3());
    xbox.getButtonB().whenPressed(new ElevatorBottom());
    xbox.getButtonA().whenPressed(new PIDElevatorL1());
    xbox.getButtonX().whenPressed(new PIDElevatorL2());
    */
    /*
    xbox.getButtonA().whenPressed(new RollersIn());
    xbox.getButtonB().whenPressed(new StopRollers());
    xbox.getButtonX().whenPressed(new CloseClawWhenSensed());
    xbox.getButtonY().whenPressed(new PickupHatchHPS());

    //xbox.getButtonRightBumper().whenPressed(new RollersIn());
    //xbox.getButtonRightBumper().whenReleased(new StopRollers());

    //xbox.getButtonLeftBumper().whenPressed(new PlaceHatch());
    //xbox.getButtonLeftBumper().whenPressed(new PushBallOut());
    //xbox.getDPad().getRight().whenPressed(new KickCargo());
    //xbox.getDPad().getRight().whenReleased(new RetractCargoKicker());
	//	xbox.getDPad().getLeft().whenPressed(new PushBallOut());
    //xbox.getDPad().getDown().whenPressed(new IntakeCargo());
    xbox.getDPad().getLeft().whenPressed(new OpenClaw());
    xbox.getDPad().getRight().whenPressed(new CloseClaw());
    xbox.getDPad().getUp().whenPressed(new IntakeUp());
    xbox.getDPad().getDown().whenPressed(new IntakeDown());

    xbox.getButtonLeftBumper().whenPressed(new PushClawForward());
    xbox.getButtonRightBumper().whenPressed(new PullClawBack());
    */


    //xbox.getDPad().getUp().whenPressed(new IntakeUp());

    //xbox.getButtonStart().whenPressed(new Manual Control);
    

    leftStick.getButton(8).whenPressed(new SwitchSides());
    rightStick.getButton(8).whenPressed(new SwitchSides());
    leftStick.getButton(5).whenPressed(new ChangeHeading(180, .4));
    //rightStick.getButton(1).whenPressed(new PickupHatchHPS());
    //leftStick.getButton(1).whenPressed(new TranslateClawToCenter(10));
    //leftStick.getButton(3).whenPressed(new CenterRobot(10));
    leftStick.getButton(3).whenPressed(new PlaceHatch());
    //rightStick.getButton(3).whenPressed(new PullClawBack());
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

}
