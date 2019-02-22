/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Elevator;
/**
 * Command that drives the elevator upwards until it hits the top limit switch.
 */
public class ElevatorTop extends Command {

  Elevator elevator;
  public ElevatorTop() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Elevator.getInstance());
    elevator = Elevator.getInstance();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Elevator.getInstance().extend(1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return elevator.isAtTop();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Elevator.getInstance().extend(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Elevator.getInstance().extend(0);
  }
}
