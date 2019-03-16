/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Elevator;

/**
 * Command to drive the elevator downwards until it hits the bottom limit switch.
 */
public class Elevator_Rocket_L2 extends Command {

  Elevator elevator;
  Timer t;
  public Elevator_Rocket_L2() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Elevator.getInstance());
    elevator = Elevator.getInstance();
    t = new Timer();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    t.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (t.get() > 0.3){
      Elevator.getInstance().extend(-1);
    }
    else{
      Elevator.getInstance().extend(-.5);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return elevator.isAtLevelTwo();
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
