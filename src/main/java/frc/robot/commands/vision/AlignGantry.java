/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Gantry;
import frc.robot.subsystems.Vision;

public class AlignGantry extends Command {
  double sp;
  boolean isFinished;
  Gantry gantry;
  Encoder encoder;
  public AlignGantry() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Gantry.getInstance());
    gantry = Gantry.getInstance();
    encoder = Gantry.getInstance().getEncoder();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(5);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    sp = Gantry.getInstance().calculateGantryPosition();
    if(Gantry.getInstance().getEncoder().getDistance() < sp){
      Gantry.getInstance().driveGantryMotor(-1);
      if(((sp-0.02) < encoder.getDistance()) && (encoder.getDistance() <(sp + 0.02))){
        gantry.driveGantryMotor(0);
      }
    }
    else if (encoder.getDistance() > sp){
      gantry.driveGantryMotor(1);

      if(((sp-0.02) < encoder.getDistance()) && (encoder.getDistance() <(sp + 0.02))){
        gantry.driveGantryMotor(0);
      }
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //return(((sp-0.02) < Gantry.getInstance().getEncoder().getDistance()) && (Gantry.getInstance().getEncoder().getDistance() <(sp + 0.02)) || isTimedOut());
    return Vision.getInstance().finishedAligning;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    gantry.driveGantryMotor(0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    gantry.driveGantryMotor(0);

  }
}
