package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import java.lang.*;
import frc.robot.input.AttackThree.AttackThreeAxis;
import frc.robot.subsystems.Drive;

/**
 * This command runs as a default command and allows user drive with two joystick in the TankDrive configuration.
 */
public class DefaultDrive extends Command {

    double left, right;
    AttackThreeAxis yAxis = AttackThreeAxis.kY;

    public DefaultDrive() {
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
     * Reads value of left and right joysticks and drives robot in TankDrive configuration using those values.
     */
    protected void execute() {
        left  = OI.getInstance().getLeftStick().getAxis(yAxis);
        right = OI.getInstance().getRightStick().getAxis(yAxis);

        Drive.getInstance().tankDrive(left, right);
    }

    /**
     * Never ends because command is default command for Drive subsytem.
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Not Used
     */
    protected void end() {
    }

    /**
     * Not Used
     */
    protected void interrupted() {
    }
}