package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.lib.Commands;
import org.usfirst.frc.team115.lib.Commands.FlywheelRequest;
import org.usfirst.frc.team115.lib.Commands.PunchRequest;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OperatorInterface {

	private Commands commands = new Commands();

	Joystick throttle = HardwareAdaptor.kThrottle;
	Joystick wheel = HardwareAdaptor.kWheel;
	Joystick xBoxController = HardwareAdaptor.kXboxController;
	Servo camServo = HardwareAdaptor.kCameraServo;

	public void reset() {
		commands = new Commands();
	}

	public Commands getCommands() {
		if(throttle.getTrigger()) {
			commands.punchRequest = PunchRequest.INTAKE;
			commands.activeIntakeRequest = Commands.ActiveIntakeRequest.INTAKE;
			commands.flywheelRequest = FlywheelRequest.INTAKE;
		} else {
			commands.activeIntakeRequest = Commands.ActiveIntakeRequest.NONE;
			commands.punchRequest = PunchRequest.NONE;
		}

		if(xBoxController.getRawButton(1)) {
			commands.armRequest = Commands.ArmRequest.INTAKE;
		} else if(xBoxController.getRawButton(2)) {
			commands.armRequest = Commands.ArmRequest.BATTER;
		} else if(xBoxController.getRawButton(4)) {
			commands.armRequest = Commands.ArmRequest.OUTERWORKS;
		} else if(xBoxController.getRawButton(3)) {
			commands.armRequest = Commands.ArmRequest.RESET;
		}

		if(xBoxController.getRawButton(5)) {
			commands.flywheelRequest = FlywheelRequest.BATTER_SHOOT;
		} else if(xBoxController.getRawButton(6)) {
			commands.flywheelRequest = FlywheelRequest.SHOOT;
		}

		if(xBoxController.getRawAxis(2) >= 0.1) {
			commands.flywheelRequest = FlywheelRequest.NONE;
		} 

		if(xBoxController.getRawAxis(3) >= 0.1) {
			commands.punchRequest = PunchRequest.PUNCH;
		}

		if(xBoxController.getRawButton(10)) {
			commands.armRequest = Commands.ArmRequest.MANUAL;
		} else if(xBoxController.getRawButton(9)) {
			commands.armRequest = Commands.ArmRequest.NONE;
		}

		if(xBoxController.getPOV() == 0) {
			commands.camRequest = Commands.CameraServoRequest.STD;
		} else if(xBoxController.getPOV() == 180) {
			commands.camRequest = Commands.CameraServoRequest.LOWBAR;
		} else if(xBoxController.getPOV() == 90) {
			commands.camRequest = Commands.CameraServoRequest.UP;
		} else if(xBoxController.getPOV() == 270) {
			commands.camRequest = Commands.CameraServoRequest.DOWN;
		}

		return commands;
	}

}


