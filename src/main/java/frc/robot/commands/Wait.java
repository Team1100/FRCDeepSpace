
package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command makes the robot wait for a specified number of seconds. This is 
 * used in auto for ensuring we don't hit another robot in CrossLine.java
 * @see org.usfirst.frc.team1100.robot.commands.Wait
 */
public class Wait extends Command {
    
    Timer t;
	double time;
    
    public Wait(double time) {
    	t = new Timer();
    	this.time = time;
    }
    
    protected void initialize() {
    	t.start();
    }

    protected void execute() {
    }
    
    protected boolean isFinished() {
        return t.get()>time;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}