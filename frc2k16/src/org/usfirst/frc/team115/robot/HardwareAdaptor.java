package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.lib.DriveBase;
import org.usfirst.frc.team115.robot.subsystems.Flywheel;
import org.usfirst.frc.team115.robot.subsystems.Intake;
import org.usfirst.frc.team115.robot.subsystems.Punch;
import org.usfirst.frc.team115.robot.subsystems.ShooterArm;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class HardwareAdaptor {
	
	//Subsystems
	public static DriveBase kDrive = new DriveBase();
	public static ShooterArm kAngler = new ShooterArm();
	public static Flywheel kLeftFlywheel = new Flywheel("Left Flywheel", Constants.kLeftFlywheel, true);
	public static Flywheel kRightFlywheel = new Flywheel("Right Flywheel", Constants.kRightFlywheel, false);
	public static Intake kIntake = new Intake(Constants.kIntake);
	public static Punch kPunch = new Punch(Constants.kPunchA, Constants.kPunchB);

	//Interface
	public static Joystick kThrottle = new Joystick(0);
	public static Joystick kWheel = new Joystick(1);
	public static Joystick kButtonBoard = new Joystick(2);

}
