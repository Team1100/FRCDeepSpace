/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Gantry;
import frc.robot.subsystems.Vision;

public class AlignGantry extends Command {
  double offset = -1;
  public AlignGantry() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Gantry.getInstance());
    requires(Vision.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    offset = Vision.getInstance().getTargetOffset();
      if(offset > 164){
        Gantry.getInstance().driveGantryMotor(-1);
      }
      else if (offset < 156){
        Gantry.getInstance().driveGantryMotor(1);
      }
      else{
        Gantry.getInstance().driveGantryMotor(0);
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
    Gantry.getInstance().driveGantryMotor(0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Gantry.getInstance().driveGantryMotor(0);
  }
}
