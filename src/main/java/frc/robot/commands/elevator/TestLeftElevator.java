package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.input.XboxController.XboxAxis;
import frc.robot.subsystems.Elevator;


public class TestLeftElevator extends Command {
    double speed;
    XboxAxis leftJoystickY = XboxAxis.kYLeft;
    boolean end;

    public TestLeftElevator() {
        requires(Elevator.getInstance());
    }
    
    /**
     * Not Used
     */
    protected void initialize() {
    }

    /**
     * Uses left stick of xbox controller to move elevator up and down
     */
    protected void execute() {
	    speed = OI.getInstance().getXbox().getAxis(leftJoystickY);
	    Elevator.getInstance().extendLeft(speed);
    }
    
    /**
     * Always false because default commands never stop
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
