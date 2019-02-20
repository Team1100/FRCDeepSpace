package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.input.XboxController.*;
import frc.robot.input.XboxController;
import frc.robot.subsystems.Elevator;


public class DefaultElevator extends Command {
    double speed;
    XboxAxis leftJoystickY = XboxAxis.kYLeft;

    public DefaultElevator() {
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
        speed = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYRight);
        System.out.println(speed);
	    Elevator.getInstance().extend(speed/2);
    	
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
