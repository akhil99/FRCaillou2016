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
	Joystick buttonBoard = HardwareAdaptor.kButtonBoard;
	Joystick xBoxController = HardwareAdaptor.kXboxController;
	Servo camServo = HardwareAdaptor.kCameraServo;

	public void reset() {
		commands = new Commands();
	}

	public Commands getCommands(){
		/*f(throttle.getRawButton(3)) {
			commands.intakeRequest = IntakeRequest.INTAKE;
		} else {
			commands.intakeRequest = IntakeRequest.NONE;
		}*/
		if(throttle.getTrigger()) {
			commands.punchRequest = PunchRequest.INTAKE;
			commands.activeIntakeRequest = Commands.ActiveIntakeRequest.INTAKE;
			commands.flywheelRequest = FlywheelRequest.INTAKE;
		} else {
			commands.activeIntakeRequest = Commands.ActiveIntakeRequest.NONE;
			commands.punchRequest = PunchRequest.NONE;
		}

		/*if(wheel.getRawButton(3)) {
			commands.cancelRoutine = true;
		} else {
			commands.cancelRoutine = false;
		}*/
/*
		if(buttonBoard.getRawButton(4)) {
			commands.punchRequest = PunchRequest.PUNCH;
		} else if(commands.punchRequest != PunchRequest.INTAKE) {
			commands.punchRequest = PunchRequest.NONE;
		}*/

		
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
		} else {
			commands.punchRequest = PunchRequest.NONE;
		}
		
		if(xBoxController.getRawButton(10)) {
			commands.armRequest = Commands.ArmRequest.MANUAL;
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
		
		
		/*if(buttonBoard.getRawButton(3)) {
			commands.flywheelRequest = FlywheelRequest.SHOOT;
		} /*else if (buttonBoard.getRawButton(2)) {
			commands.flywheelRequest = FlywheelRequest.SHOOT;
		} *//*else */
		/*if(buttonBoard.getRawButton(3)){
			commands.flywheelRequest = FlywheelRequest.BATTER_SHOOT;
		}	else	if (buttonBoard.getRawButton(1)){
			commands.flywheelRequest = FlywheelRequest.NONE;
		}
		
		if(HardwareAdaptor.kAngleJoystick.getTrigger()) {
			commands.flywheelRequest = FlywheelRequest.SHOOT;
		}

		if(buttonBoard.getRawButton(2)) {
			commands.armRequest = Commands.ArmRequest.BATTER;
		} else if(buttonBoard.getRawButton(5)) {
			commands.armRequest = Commands.ArmRequest.OUTERWORKS;
		} else if(HardwareAdaptor.kAngleJoystick.getRawButton(2)) {
			commands.armRequest = Commands.ArmRequest.INTAKE;
		} else if(HardwareAdaptor.kAngleJoystick.getRawButton(3)) {
			commands.armRequest = Commands.ArmRequest.MANUAL;
		}*/

		return commands;
	}

}

