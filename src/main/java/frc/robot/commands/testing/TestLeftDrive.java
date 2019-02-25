
package frc.robot.commands.testing;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Drive;
import frc.robot.input.XboxController;

/**
 * This command is used in the testing portion of the program to test the left side of the drivetrain.
 */

 public class TestLeftDrive extends Command {

    double left; 
    boolean end = false;

    public TestLeftDrive() {
        requires(Drive.getInstance()); 
    }

    /**
     * Not Used
     */
    protected void initialize() { 

    }

    /**
     * Runs when command is executed. (In this case always because command is default command.)
     * 
     * Reads value of left and joysticks and drives robot in TankDrive configuration using those values.
     */

    protected void execute() {
      left = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYLeft);
      Drive.getInstance().tankDrive(-left, 0);
    }

    /**
     * Never ends because command is default command for Drive subsytem.
     */

    protected boolean isFinished() {
      return end;
    }

    /**
     * Stops drivetrain when command ends
     */

    protected void end() {
      Drive.getInstance().tankDrive(0, 0);
    }

    /**
     * Stops drivetrain when command gets interrupted.
     */

    protected void interrupted() {
      Drive.getInstance().tankDrive(0, 0);
    }

}