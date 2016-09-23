package org.usfirst.frc.team115.robot;

public class Constants {
	
	
	//Flywheel speeds (RPM)
	public static final double kFlywheelMax = 6500;
	public static final double kFlywheelBatter = 4000;
	public static final double kFlywheelIntake = -4200; 
	
	
	
	
	
	/*DO NOT EDIT PAST THIS LINE
	 * FOR REALSIES DON'T DO IT*/
	
	//Wheel Constants
	public static final int kTicksPerRotation = 243;
	public static final double kRotationsPerTick = 1.0 / kTicksPerRotation;
	public static final int kWheelDiameter = 6;
	public static final double kDriveDistancePerTick = (kWheelDiameter*Math.PI)/kTicksPerRotation;
	
	
	//DriveTrain Talons
	public static final int kLeftDriveFront = 9;
	public static final int kLeftDriveRear = 10;
	public static final int kRightDriveFront = 12;
	public static final int kRightDriveRear = 11;
	
	//Angler Talons
	//public static final int kLeftShooter = 7;
	public static final int kRightShooter = 13;
	
	//Flywheel Talons
	public static final int kLeftFlywheel = 16;
	public static final int kRightFlywheel = 15;
	
	//Intake Talon
	public static final int kIntake = 20;
	
	//Punch Talon
	public static final int kPunchWheel = 14;
	
	//Solenoids
	public static final int kPunchA = 0;
	public static final int kPunchB = 1;
	
	//Arm Positions
	public static final double kIntakePosition = 2.8;
	public static final double kOuterworksPosition = 1.78;
	public static final double kBatterPosition = 1.1;
	
	//Encoder Ports
	public static final int kRightEncoderA = 6;
	public static final int kRightEncoderB = 7;
	public static final int kLeftEncoderA = 0;
	public static final int kLeftEncoderB = 1;
	
	//Breakbeam port
	//public static final int kBreakbeamPort = 6;
	
	//Limit Switch Ports
	public static final int kTopLimit = 4;
	public static final int kBottomLimit = 5;
	
	//Flashlight Ports
	public static final int kFlashlightPort = 9;
	
}
