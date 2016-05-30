
package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.lib.BehaviorManager;
import org.usfirst.frc.team115.lib.DriveBase;
import org.usfirst.frc.team115.lib.DriveSignal;
import org.usfirst.frc.team115.lib.Logger;
import org.usfirst.frc.team115.lib.Looper;
import org.usfirst.frc.team115.lib.MultiLooper;
import org.usfirst.frc.team115.robot.subsystems.DriveSystem;
import org.usfirst.frc.team115.robot.subsystems.Flywheel;
import org.usfirst.frc.team115.robot.subsystems.Intake;
import org.usfirst.frc.team115.robot.subsystems.Punch;
import org.usfirst.frc.team115.robot.subsystems.ShooterArm;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	
	Logger log = new Logger("/home/lvuser/robotlog.csv");
	
	MultiLooper slowLooper = new MultiLooper("Slow Loopers", 1 / 100.0);
	MultiLooper looper = new MultiLooper("Loopers", 1 / 200.0);
	Looper logUpdater = new Looper("Logger", log, 1 / 50.0);
	
	DriveBase drive = HardwareAdaptor.kDrive;
	ShooterArm angler = HardwareAdaptor.kAngler;
	Flywheel rightFlywheel = HardwareAdaptor.kRightFlywheel;
	Flywheel leftFlywheel = HardwareAdaptor.kLeftFlywheel;
	Punch punch = HardwareAdaptor.kPunch;
	Intake intake = HardwareAdaptor.kIntake;
	
	DriveSystem driveHandler = new DriveSystem(drive);
	
	Joystick throttle = HardwareAdaptor.kThrottle;
	Joystick wheel = HardwareAdaptor.kWheel;
	
	BehaviorManager behaviorManager = new BehaviorManager();
	OperatorInterface operatorInterface = new OperatorInterface();

	Compressor compressor = new Compressor(1);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		slowLooper.registerLoopable(drive);
		looper.registerLoopable(angler);
		looper.registerLoopable(rightFlywheel);
		looper.registerLoopable(leftFlywheel);
		looper.registerLoopable(punch);
		looper.registerLoopable(intake);
		compressor.start();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit(){

		looper.stop();
		slowLooper.stop();
		logUpdater.stop();

		operatorInterface.reset();
		
		drive.setOpenLoop(new DriveSignal(0, 0));
		rightFlywheel.setOpenLoop(0.0);
		leftFlywheel.setOpenLoop(0.0);
		
		System.gc();
	}

	public void disabledPeriodic() {
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		slowLooper.start();
		looper.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	public void teleopInit() {
		looper.start();
		logUpdater.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		boolean quickturn = throttle.getRawButton(2);
		double turn = wheel.getRawAxis(0);
		
		if(quickturn) {
			driveHandler.squareInputs(turn);
		}
		
		driveHandler.drive(-throttle.getY(), turn, quickturn);
		behaviorManager.update(operatorInterface.getCommands());
		SmartDashboard.putNumber("Left Flywheel Speed", leftFlywheel.getSpeedRPM());
		SmartDashboard.putNumber("Right Flywheel Speed", rightFlywheel.getSpeedRPM());
		SmartDashboard.putBoolean("Compressor enabled?", compressor.enabled());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
