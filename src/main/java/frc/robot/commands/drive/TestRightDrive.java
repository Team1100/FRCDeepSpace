package frc.robot.commands.drive;



import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;

import frc.robot.subsystems.Drive;

import frc.robot.input.XboxController;





/**

 * This command runs as a default command and allows user drive with two joystick in the TankDrive configuration.

 */

public class TestRightDrive extends Command {



    double right; 

    boolean end;



    public TestRightDrive() {

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

	    right = OI.getInstance().getXbox().getAxis(XboxController.XboxAxis.kYLeft);

      Drive.getInstance().tankDrive(0, -right);

    }



    /**

     * Never ends because command is default command for Drive subsytem.

     */

    protected boolean isFinished() {

        return end;

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