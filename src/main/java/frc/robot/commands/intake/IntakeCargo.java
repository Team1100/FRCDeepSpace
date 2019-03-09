/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
//import frc.robot.subsystems.BeamBreak;
import frc.robot.commands.rollers.*;

/**
 * This command group is to be used by the operator to put the intak down
 * and run the rollers in to collect a ball.
 */
public class IntakeCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeCargo() {
    //BeamBreak beamBreak;

    //beamBreak = BeamBreak.getInstance();

    //addSequential(new IntakeDown());
    addParallel(new RollersIn());
    addSequential(new AcquireBall());
    addSequential(new StopRollers());
    //addSequential(new IntakeUp());
    /*
    addSequential(new IntakeUp());
    addSequential(new MoveBallToChute(1));
    addSequential(new StopRollers());
    */
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
