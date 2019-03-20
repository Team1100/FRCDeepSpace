/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.input.XboxController;
import frc.robot.subsystems.Climber;

public class DefaultClimber extends Command {
  Climber climber;
  XboxController xbox;
  public DefaultClimber() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Climber.getInstance());
    climber = Climber.getInstance();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    xbox = OI.getInstance().getXbox();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(xbox.getAxis(XboxController.XboxAxis.kLeftTrigger) > 0.5){
      if(climber.isUp6()){
        climber.extendSix();
      }
      else{
        climber.liftSix();
      }
    }

    else if(xbox.getAxis(XboxController.XboxAxis.kRightTrigger) > 0.5){
      if(climber.isUp8()){
        climber.extendBoth();
      }
      else{
        climber.liftBoth();
      }
    }
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
