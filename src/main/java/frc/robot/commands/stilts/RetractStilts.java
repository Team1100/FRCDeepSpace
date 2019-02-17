/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stilts;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Stilts;
import edu.wpi.first.wpilibj.Timer;

public class RetractStilts extends Command {

  Timer timer;
  private static final double PERIOD = 5;
  private static final double RETRACT_SPEED = -0.5;

  private Stilts stilts;

  public RetractStilts() {
    requires(Stilts.getInstance());
    timer = new Timer();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
    stilts.setSpeed(RETRACT_SPEED);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.hasPeriodPassed(PERIOD);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    stilts.setSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    stilts.setSpeed(0);
  }
}
