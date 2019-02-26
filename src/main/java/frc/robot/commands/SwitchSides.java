/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.input.AttackThree;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Switches the robots interpretation of the left and right joysticks.
 * DOES NOT WORK YET.
 */
public class SwitchSides extends Command {
  boolean leftState = AttackThree.leftIsLeft;
  boolean end = false;

  public SwitchSides() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (leftState == true){
      AttackThree.leftIsLeft = false;
      if(AttackThree.leftIsLeft){
        OI.leftStick = new AttackThree(RobotMap.U_JOYSTICK_LEFT, 0.05);
        OI.rightStick = new AttackThree(RobotMap.U_JOYSTICK_RIGHT, 0.05);
      } else if (!AttackThree.leftIsLeft){
        OI.leftStick = new AttackThree(RobotMap.U_JOYSTICK_RIGHT, 0.05);
        OI.rightStick = new AttackThree(RobotMap.U_JOYSTICK_LEFT, 0.05);
      }
    }

    if (leftState == false){
      AttackThree.leftIsLeft = true;
    }
    end = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return end;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    end = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
