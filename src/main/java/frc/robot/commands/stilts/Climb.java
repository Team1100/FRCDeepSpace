/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.stilts;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.intake.IncrementIntakePower;

public class Climb extends CommandGroup {
 
  private static final double BALANCE_SPEED = 0.5;
  private static final double LOWER_INTAKE_INC = 0.1;

  public Climb() {

    // climb up logic
    // 1. lower ball intake by LOWER_INTAKE_INC
    // 2. adjust stilts to 0 pitch (leveled robot)
    // 3. repeat this until the ball intake is at full power
    // this applies to both level2 and level3 climb
    //addSequential(new MeasuredDrive(.1, -1));
    for (double i = 0; i < 1; i=i+LOWER_INTAKE_INC) {
      addSequential(new IncrementIntakePower(LOWER_INTAKE_INC));
      addSequential(new BalanceStilts(BALANCE_SPEED));
    }
    /*
    
    addSequential(new RaiseIntakeAndDrive());
    addSequential(new RetractStilts());
    addSequential(new MeasuredDrive(.5, -.7));
    */
    // TODO: after this is done:
    // 1. slowly drive forward and raise ball intake in parallel
    // 2. retract stilts
    // 3. drive forward
  }

}
