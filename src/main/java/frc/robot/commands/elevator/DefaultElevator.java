package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.input.XboxController;
import frc.robot.subsystems.Elevator;

/**
 * Default command to drive the elevator up and down using the joystick on the xbox controller.
 */
public class DefaultElevator extends Command {
    double speed;

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
      //Speed is currently limited because safties have not been implemented.
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
     * Stops elevator if command gets interrupted
     */
    protected void interrupted() {
      Elevator.getInstance().extend(0);
    }
}
