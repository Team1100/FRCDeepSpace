/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stilts;

import frc.robot.input.XboxController;
import frc.robot.input.XboxController.XboxAxis;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Stilts;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;

public class DefaultStilts extends Command {

  private Stilts stilts;
  double speed;

  public DefaultStilts() {
    requires(Stilts.getInstance());
    stilts = Stilts.getInstance();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    stilts.setSpeed(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    speed = 0;
    if((OI.getInstance().getXboxClimb().getAxis(XboxController.XboxAxis.kLeftTrigger) == 0) && (OI.getInstance().getXboxClimb().getAxis(XboxController.XboxAxis.kRightTrigger)== 0)){
      speed = 0;
    }else if((OI.getInstance().getXboxClimb().getAxis(XboxController.XboxAxis.kLeftTrigger) > (OI.getInstance().getXboxClimb().getAxis(XboxController.XboxAxis.kRightTrigger)))){
      speed = (OI.getInstance().getXboxClimb().getAxis(XboxController.XboxAxis.kLeftTrigger));
    }else{
      speed = (OI.getInstance().getXboxClimb().getAxis(XboxController.XboxAxis.kRightTrigger));
    }
    stilts.setSpeed(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
