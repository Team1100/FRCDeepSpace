package frc.robot.commands.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.input.AttackThree.AttackThreeAxis;
import frc.robot.subsystems.Drive;

/**
 * This command runs as a default command and allows user drive with two joystick in the TankDrive configuration.
 */
public class DefaultDrive extends Command {

    double targetLeft, targetRight;
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
        targetLeft  = OI.getInstance().getLeftStick().getAxis(yAxis) * 4096 * 10.0;
        targetRight = OI.getInstance().getRightStick().getAxis(yAxis) * 4096 * 10.0;

        Drive.getInstance().leftMaster.set(ControlMode.MotionMagic, targetLeft);
        Drive.getInstance().rightMaster.set(ControlMode.MotionMagic, targetRight);

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
