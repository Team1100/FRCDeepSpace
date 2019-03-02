/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.gantry;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Gantry;

public class AccurateGantry extends Command {
  double sp;
  boolean isFinished;
  public AccurateGantry(double setpoint) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Gantry.getInstance());
    sp = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      setTimeout(5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Gantry.getInstance().getEncoder().getDistance() < sp){
      Gantry.getInstance().driveGantryMotor(-1);
    }
    else if (Gantry.getInstance().getEncoder().getDistance() > sp){
      Gantry.getInstance().driveGantryMotor(1);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
      return(((sp-0.02) < Gantry.getInstance().getEncoder().getDistance()) && (Gantry.getInstance().getEncoder().getDistance() <(sp + 0.02)) || isTimedOut());
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
