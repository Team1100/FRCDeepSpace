/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.input.AttackThree;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private static OI oi;

  private static AttackThree leftStick;
  private static AttackThree rightStick;

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
    leftStick = new AttackThree(RobotMap.U_JOYSTICK_LEFT, 0.1);
    rightStick = new AttackThree(RobotMap.U_JOYSTICK_RIGHT, 0.1);

  }

  /**
   * @return the leftStick
   */
  public AttackThree getLeftStick() {
    return leftStick;
  }

  /**
   * @return the rightStick
   */
  public AttackThree getRightStick() {
    return rightStick;
  }

}
