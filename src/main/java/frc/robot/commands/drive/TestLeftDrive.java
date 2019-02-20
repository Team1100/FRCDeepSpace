
package frc.robot.commands.drive;



import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;

import frc.robot.subsystems.Drive;

import frc.robot.input.XboxController;





/**

 * This command runs as a default command and allows user drive with two joystick in the TankDrive configuration.

 */

public class TestLeftDrive extends Command {



    double left; 

    boolean end;



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

     * Reads value of left and right joysticks and drives robot in TankDrive configuration using those values.

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