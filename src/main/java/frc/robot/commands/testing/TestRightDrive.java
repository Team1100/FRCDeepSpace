package frc.robot.commands.testing;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Drive;
import frc.robot.input.XboxController;

/**
 * This command is used in the testing portion of the program to test the right side of the drivetrain.
 */

public class TestRightDrive extends Command {

    double right; 
    boolean end = false;

    public TestRightDrive() {
      requires(Drive.getInstance()); 
    }

    /**
     * Not Used
     */

    protected void initialize() {
        
    }

    /**
     * Runs when command is executed.
     * 
     * Reads value of right joysticks and drives robot in TankDrive configuration using those values.
     */

    protected void execute() {
      right = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYLeft);
      Drive.getInstance().tankDrive(0, -right);

    }

    /**
     * Never ends
     */

    protected boolean isFinished() {
      return end;
    }



    /**
     * Stops drivetrain when it ends
     */

    protected void end() {
      Drive.getInstance().tankDrive(0, 0);
    }



    /**
     * Stops drivetrain when interrupted
     */

    protected void interrupted() {
      Drive.getInstance().tankDrive(0, 0);
    }

}