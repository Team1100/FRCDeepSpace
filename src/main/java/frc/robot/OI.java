/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.input.AttackThree;
import frc.robot.input.XboxController;
import frc.robot.commands.SwitchSides;
import frc.robot.commands.claw.*;
import frc.robot.commands.elevator.ElevatorTop;
import frc.robot.commands.elevator.ElevatorBottom;
import frc.robot.commands.elevator.*;
import frc.robot.commands.intake.PushBallOut;
import frc.robot.commands.intake.RollersIn;
import frc.robot.commands.intake.StopRollers;
import frc.robot.commands.intake.IntakeUp;
import frc.robot.commands.intake.*;
import frc.robot.commands.drive.*;

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
    leftStick = new AttackThree(RobotMap.U_JOYSTICK_LEFT, 0.05);
    rightStick = new AttackThree(RobotMap.U_JOYSTICK_RIGHT, 0.05);

    xbox = new XboxController(RobotMap.U_XBOX_CONTROLLER, 0.1);

    //Now Mapping Commands to XBox 
    
    xbox.getButtonY().whenPressed(new PIDElevatorL3());
    xbox.getButtonB().whenPressed(new ElevatorBottom());
    xbox.getButtonA().whenPressed(new PIDElevatorL1());
    xbox.getButtonX().whenPressed(new PIDElevatorL2());

    //xbox.getButtonRightBumper().whenPressed(new RollersIn());
    xbox.getButtonRightBumper().whenReleased(new StopRollers());

    xbox.getButtonLeftBumper().whenPressed(new PlaceHatch());
    
		xbox.getDPad().getLeft().whenPressed(new PushBallOut());
    xbox.getDPad().getDown().whenPressed(new IntakeCargo());
    xbox.getDPad().getUp().whenPressed(new IntakeUp());

    //xbox.getButtonStart().whenPressed(new Manual Control);
    

    leftStick.getButton(8).whenPressed(new SwitchSides());
    rightStick.getButton(8).whenPressed(new SwitchSides());
    leftStick.getButton(5).whenPressed(new ChangeHeading(180, .4));
    rightStick.getButton(1).whenPressed(new PickupHatchHPS());
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
