/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  //List of all PWM ports
	private final static int PWM_0 = 0;
	private final static int PWM_1 = 1;
	private final static int PWM_2 = 2;
	private final static int PWM_3 = 3;
	private final static int PWM_4 = 4;
	private final static int PWM_5 = 5;
	private final static int PWM_6 = 6;
	private final static int PWM_7 = 7;
	private final static int PWM_8 = 8;
	private final static int PWM_9 = 9;
	// see https://www.pdocs.kauailabs.com/navx-mxp/installation/io-expansion/
	private final static int PWM_MXP_0 = 10;
	private final static int PWM_MXP_1 = 11;
	private final static int PWM_MXP_2 = 12;
	private final static int PWM_MXP_3 = 13;
	private final static int PWM_MXP_4 = 14;
	private final static int PWM_MXP_5 = 15;
	private final static int PWM_MXP_6 = 16;
	private final static int PWM_MXP_7 = 17;
	private final static int PWM_MXP_8 = 18;
	private final static int PWM_MXP_9 = 19;

	// List of PCM CAN IDs
	private final static int PCM_CAN = 17;
	private final static int PCM_CAN_LED = 1;

	// List of CAN IDs
	private final static int CAN_0 = 0;
	private final static int CAN_1 = 1;
	private final static int CAN_2 = 2;
	private final static int CAN_3 = 3;
	private final static int CAN_4 = 4;
	private final static int CAN_5 = 5;
	private final static int CAN_6 = 6;
	private final static int CAN_7 = 7;
	private final static int CAN_8 = 8;
	private final static int CAN_9 = 9;
	private final static int CAN_10 = 10;
	private final static int CAN_11 = 11;
	private final static int CAN_12 = 12;
	private final static int CAN_13 = 13;
	private final static int CAN_14 = 14;
	private final static int CAN_15 = 15;

	//List of all analog ports
	private final static int ANALOG_0 = 0;
	private final static int ANALOG_1 = 1;
	private final static int ANALOG_2 = 2;
	private final static int ANALOG_3 = 3;
	// see https://www.pdocs.kauailabs.com/navx-mxp/installation/io-expansion/
	private final static int ANALOG_MXP_0 = 4;
	private final static int ANALOG_MXP_1 = 5;
	private final static int ANALOG_MXP_2 = 6;
	private final static int ANALOG_MXP_3 = 7;

	//List of all relays
	private final static int RELAY_0 = 0;
	private final static int RELAY_1 = 1;
	private final static int RELAY_2 = 2;
	private final static int RELAY_3 = 3;

	//List of all DIO ports
	private final static int DIO_0 = 0;
	private final static int DIO_1 = 1;
	private final static int DIO_2 = 2;
	private final static int DIO_3 = 3;
	private final static int DIO_4 = 4;
	private final static int DIO_5 = 5;
	private final static int DIO_6 = 6;
	private final static int DIO_7 = 7;
	private final static int DIO_8 = 8;
	private final static int DIO_9 = 9;

	// see https://www.pdocs.kauailabs.com/navx-mxp/installation/io-expansion/
	private final static int DIO_MXP_0 = 10;
	private final static int DIO_MXP_1 = 11;
	private final static int DIO_MXP_2 = 12;
	private final static int DIO_MXP_3 = 13;
	private final static int DIO_MXP_4 = 18;
	private final static int DIO_MXP_5 = 19;
	private final static int DIO_MXP_6 = 20;
	private final static int DIO_MXP_7 = 21;
	private final static int DIO_MXP_8 = 22;
	private final static int DIO_MXP_9 = 23;

	//List of all USB ports
	private static final int USB_0 = 0;
	private static final int USB_1 = 1;
	private static final int USB_2 = 2;
	private static final int USB_3 = 3;

	// Pneumatic Control Module (PCM) ports
	private static final int PCM_0 = 0;
	private static final int PCM_1 = 1;
	private static final int PCM_2 = 2;
	private static final int PCM_3 = 3;
	private static final int PCM_4 = 4;
	private static final int PCM_5 = 5;
	private static final int PCM_6 = 6;
	private static final int PCM_7 = 7;
	private static final int PCM_8 = 8;

	//List of all PDP ports
	private static final int PDP_0 = 0;
	private static final int PDP_1 = 1;
	private static final int PDP_2 = 2;
	private static final int PDP_3 = 3;
	private static final int PDP_4 = 4;
	private static final int PDP_5 = 5;
	private static final int PDP_6 = 6;
	private static final int PDP_7 = 7;
	private static final int PDP_8 = 8;
	private static final int PDP_9 = 9;
	private static final int PDP_10 = 10;
	private static final int PDP_11 = 11;
	private static final int PDP_12 = 12;
	private static final int PDP_13 = 13;
	private static final int PDP_14 = 14;
	private static final int PDP_15 = 15;
	private static final int PDP_16 = 16;

  //TODO:Update with proper ports
  //[D]rive
  public static final int D_FRONT_LEFT = CAN_14;
  public static final int D_FRONT_RIGHT = CAN_1;
  public static final int D_BACK_LEFT = CAN_15;
  public static final int D_BACK_RIGHT = CAN_0;
  public static final Port D_NAVX = SPI.Port.kMXP;
  public static final int D_ENCODER_LEFT_A = DIO_6;
  public static final int D_ENCODER_LEFT_B = DIO_7;
  public static final int D_ENCODER_RIGHT_A = DIO_8;
  public static final int D_ENCODER_RIGHT_B = DIO_9;
  
  //[E]levator
  public static final int E_LEFT = PWM_4;
  public static final int E_RIGHT = PWM_5;
  public static final int E_TOP_SWITCH = DIO_1;
  public static final int E_BOTTOM_SWITCH = DIO_2;
  public static final int E_POT = ANALOG_0;

  //[S]tilts
  public static final int S_LEFT = PWM_8;
  public static final int S_RIGHT = PWM_9;

  //[B]allIntake
  public static final int B_ROLLERS = PWM_6;
  public static final int B_AXIS_MOVEMENT = PWM_7;
  public static final int B_BEAM_BREAK = DIO_0;
  public static final int B_TOP_SWITCH = ANALOG_1;
  public static final int B_BOTTOM_SWITCH = ANALOG_2;

  //[C]law
  public static final int C_CLAW_0 = PCM_0;
  public static final int C_CLAW_1 = PCM_1;
  public static final int C_CLAW_CAN = PCM_CAN;

  //[G]antry
  //TODO: Update with proper ports
  public static final int G_GANTRY = CAN_2;
  public static final int G_ENCODER_CW = DIO_4;
  public static final int G_ENCODER_CCW = DIO_5;

  //[U]ser Input
  public static final int U_JOYSTICK_LEFT = 0;
  public static final int U_JOYSTICK_RIGHT = 1;
  public static final int U_XBOX_CONTROLLER = 2;

}
