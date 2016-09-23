package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.lib.DriveBase;
import org.usfirst.frc.team115.robot.subsystems.Flywheel;
import org.usfirst.frc.team115.robot.subsystems.Intake;
import org.usfirst.frc.team115.robot.subsystems.Punch;
import org.usfirst.frc.team115.robot.subsystems.ShooterArm;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;

public class HardwareAdaptor {

	//Subsystems
	public static DriveBase kDrive = new DriveBase();
	public static ShooterArm kAngler = new ShooterArm();
	public static Flywheel kLeftFlywheel = new Flywheel("Left Flywheel", Constants.kLeftFlywheel, false);
	public static Flywheel kRightFlywheel = new Flywheel("Right Flywheel", Constants.kRightFlywheel, true);
	public static Intake kIntake = new Intake(Constants.kIntake);
	public static Punch kPunch = new Punch(Constants.kPunchA, Constants.kPunchB);
	public static Servo kCameraServo = new Servo(0);

	//Interface
	public static Joystick kThrottle = new Joystick(0);
	public static Joystick kWheel = new Joystick(1);
	public static Joystick kButtonBoard = new Joystick(3);
	//public static Joystick kAngleJoystick = new Joystick(3);
	public static Joystick kXboxController = new Joystick(2);

	//Gyro
	//public static AHRS kGyro = new AHRS(SPI.Port.kMXP);
	
	//Breakbeam
	//public static AnalogInput kBreakbeam = new AnalogInput(Constants.kBreakbeamPort);

	//Encoders
	public static Encoder kLeftDriveEncoder = new Encoder(Constants.kLeftEncoderA, Constants.kLeftEncoderB);
	public static Encoder kRightDriveEncoder = new Encoder(Constants.kRightEncoderA, Constants.kRightEncoderB);
	
	//Limit Switches
	public static DigitalInput kTopLimit = new DigitalInput(Constants.kTopLimit);
	public static DigitalInput kBottomLimit = new DigitalInput(Constants.kBottomLimit);
	
	//Flashlight
	public static DigitalOutput kFlashlight = new DigitalOutput(Constants.kFlashlightPort);

}
