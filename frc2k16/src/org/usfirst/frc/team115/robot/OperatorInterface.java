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

		if(wheel.getRawButton(3)) {
			commands.cancelRoutine = true;
		} else {
			commands.cancelRoutine = false;
		}

		if(buttonBoard.getRawButton(4)) {
			commands.punchRequest = PunchRequest.PUNCH;
		} else if(commands.punchRequest != PunchRequest.INTAKE) {
			commands.punchRequest = PunchRequest.NONE;
		}


		if(buttonBoard.getRawButton(3)) {
			commands.flywheelRequest = FlywheelRequest.SHOOT;
		} /*else if (buttonBoard.getRawButton(2)) {
			commands.flywheelRequest = FlywheelRequest.SHOOT;
		} */else if (buttonBoard.getRawButton(1)){
			commands.flywheelRequest = FlywheelRequest.NONE;
		}

		if(buttonBoard.getRawButton(2)) {
			commands.armRequest = Commands.ArmRequest.INTAKE;
		} else if(buttonBoard.getRawButton(5)) {
			commands.armRequest = Commands.ArmRequest.OUTERWORKS;
		}


		return commands;
	}

}

