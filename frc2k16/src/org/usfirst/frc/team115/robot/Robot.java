
package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.auto.AutoModeExecuter;
import org.usfirst.frc.team115.auto.modes.DoNothingAutoMode;
import org.usfirst.frc.team115.auto.modes.GenericDefenseAutoMode;
import org.usfirst.frc.team115.auto.modes.LowBarAutoMode;
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

	DriveBase drive = HardwareAdaptor.kDrive;
	ShooterArm angler = HardwareAdaptor.kAngler;
	Flywheel rightFlywheel = HardwareAdaptor.kRightFlywheel;
	Flywheel leftFlywheel = HardwareAdaptor.kLeftFlywheel;
	Punch punch = HardwareAdaptor.kPunch;
	Intake intake = HardwareAdaptor.kIntake;


	MultiLooper slowLooper = new MultiLooper("Slow Loopers", 1 / 100.0);
	MultiLooper looper = new MultiLooper("Loopers", 1 / 200.0);
	Looper driveLooper = new Looper("Drive", drive, 1 / 100.0);
	Looper anglerLooper = new Looper("Angler", angler, 1 / 200.0);
	Looper rightFlywheelLooper = new Looper("rightFlywheel", rightFlywheel, 1 / 200.0);
	Looper leftFlywheelLooper = new Looper("rightFlywheel", leftFlywheel, 1 / 200.0);
	Looper punchLooper = new Looper("punch", punch, 1 / 200.0);
	Looper intakeLooper = new Looper("intake", intake, 1 / 200.0);
	//Looper logLooper = new Looper("Log", log, 1 / 200.0);
	Looper logUpdater = new Looper("Logger", log, 1 / 50.0);


	AutoModeExecuter autoRunner = new AutoModeExecuter();


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
		//slowLooper.registerLoopable(drive);
		compressor.start();
		HardwareAdaptor.kRightDriveEncoder.setDistancePerPulse(Constants.kDriveDistancePerTick);
		HardwareAdaptor.kRightDriveEncoder.setReverseDirection(true);
		HardwareAdaptor.kLeftDriveEncoder.setDistancePerPulse(Constants.kDriveDistancePerTick);
		SmartDashboard.putString("Auton", "DoNothing");
		angler.reset();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit(){

		autoRunner.stop();
		
		behaviorManager.reset();
		
		driveLooper.stop();
		anglerLooper.stop();
		rightFlywheelLooper.stop();
		leftFlywheelLooper.stop();
		punchLooper.stop();
		intakeLooper.stop();
		logUpdater.stop();
		//logLooper.stop();
		//looper.stop();
		//slowLooper.stop();

		operatorInterface.reset();

		drive.setOpenLoop(DriveSignal.NEUTRAL);
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
		//		slowLooper.start();
		String autonString = SmartDashboard.getString("Auton", "DoNothing");
		if(autonString.equalsIgnoreCase("GenericAuton")) {
			autoRunner.setAutoMode(new GenericDefenseAutoMode());
		} else if(autonString.equalsIgnoreCase("DoNothing")) {
			autoRunner.setAutoMode(new DoNothingAutoMode());
		} else if(autonString.equalsIgnoreCase("LowBar")) {
			autoRunner.setAutoMode(new LowBarAutoMode());
		}
		autoRunner.start();
		
		driveLooper.start();
		anglerLooper.start();
		rightFlywheelLooper.start();		
		leftFlywheelLooper.start();
		punchLooper.start();
		intakeLooper.start();
		logUpdater.start();


	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
	}

	public void teleopInit() {
		driveLooper.start();
		anglerLooper.start();
		rightFlywheelLooper.start();		
		leftFlywheelLooper.start();
		punchLooper.start();
		intakeLooper.start();
		logUpdater.start();
		//drive.setDistanceSetpoint(18, 9);
		//drive.setOpenLoop(new DriveSignal(1, 1));
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

		//driveHandler.drive(-throttle.getY(), turn, quickturn);
		behaviorManager.update(operatorInterface.getCommands());

		SmartDashboard.putNumber("Left Flywheel Speed", leftFlywheel.getSpeedRPM());
		SmartDashboard.putNumber("Right Flywheel Speed", rightFlywheel.getSpeedRPM());
		SmartDashboard.putNumber("Angle", angler.getPosition());
		SmartDashboard.putNumber("Angler ouput", angler.getOutput());
		SmartDashboard.putBoolean("Compressor enabled?", compressor.enabled());
		SmartDashboard.putNumber("Breakbeam:", HardwareAdaptor.kBreakbeam.getVoltage());
		SmartDashboard.putNumber("Left Drive Encoder", HardwareAdaptor.kLeftDriveEncoder.get());
		SmartDashboard.putNumber("Right Drive Encoder", HardwareAdaptor.kRightDriveEncoder.get());
		//SmartDashboard.putNumber("Angle:", HardwareAdaptor.kAngleJoystick.getZ()*Constants.kIntakePosition);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
