/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.input.AttackThree;
import frc.robot.input.XboxController;
import frc.robot.commands.claw.*;
import frc.robot.commands.elevator.ElevatorLevelOne;
import frc.robot.commands.elevator.ElevatorLevelThree;
import frc.robot.commands.intake.DefaultIntake;
import frc.robot.commands.intake.PushBallOut;
import frc.robot.commands.intake.IntakeUp;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static OI oi;

  private static AttackThree leftStick;
  private static AttackThree rightStick;
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
    
    xbox.getButtonY().whenPressed(new ElevatorLevelOne());
    xbox.getButtonX().whenPressed(new ElevatorLevelThree());
    xbox.getButtonA().whenPressed(new ElevatorLevelThree());
    //xbox.getButtonB().whenPressed(new CollapseElevator());

    xbox.getButtonLeftBumper().whenPressed(new PlaceHatch());
    
		xbox.getDPad().getLeft().whenPressed(new PushBallOut());
    xbox.getDPad().getDown().whenPressed(new DefaultIntake());
    xbox.getDPad().getUp().whenPressed(new IntakeUp());

    //xbox.getButtonStart().whenPressed(new Manual Control);
    
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
