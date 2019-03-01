/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Vision;
import frc.robot.commands.gantry.GantryLeftLimit;
import frc.robot.subsystems.Gantry;

public class AlignGantry extends Command {
  double cx;
  Gantry gantry;
  public AlignGantry() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Gantry.getInstance());
    requires(Vision.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    gantry = Gantry.getInstance();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {  
    cx = Vision.getInstance().getCX();
    if(0 < cx && cx < 213.3){
      while(!gantry.isAtLeftLimit()){
        gantry.driveGantryMotor(-1);
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
//2176.25
