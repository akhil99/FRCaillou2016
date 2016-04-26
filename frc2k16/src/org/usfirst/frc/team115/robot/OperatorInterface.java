package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.lib.Commands;
import org.usfirst.frc.team115.lib.Commands.FlywheelRequest;
import org.usfirst.frc.team115.lib.Commands.IntakeRequest;
import org.usfirst.frc.team115.lib.Commands.PunchRequest;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OperatorInterface {

	private Commands commands = new Commands();

	Joystick throttle = HardwareAdaptor.kThrottle;
	Joystick wheel = HardwareAdaptor.kWheel;
	Joystick buttonBoard = HardwareAdaptor.kButtonBoard;

	public void reset() {
		commands = new Commands();
	}

	public Commands getCommands() {
		if(throttle.getTrigger()) {
			commands.intakeRequest = IntakeRequest.INTAKE;
			commands.flywheelRequest = FlywheelRequest.INTAKE;
		} else {
			commands.intakeRequest = IntakeRequest.NONE;
		}
		
		if(buttonBoard.getRawButton(4)) {
			commands.punchRequest = PunchRequest.PUNCH;
		} else {
			commands.punchRequest = PunchRequest.NONE;
		}
				

		if(buttonBoard.getRawButton(3)) {
			commands.flywheelRequest = FlywheelRequest.BATTER_SHOOT;
		} else if (buttonBoard.getRawButton(2)) {
			commands.flywheelRequest = FlywheelRequest.SHOOT;
		} else if (buttonBoard.getRawButton(1)){
			commands.flywheelRequest = FlywheelRequest.NONE;
		}
		
		return commands;
	}

}

