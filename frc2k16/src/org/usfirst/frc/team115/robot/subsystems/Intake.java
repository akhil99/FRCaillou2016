package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.Loopable;
import org.usfirst.frc.team115.robot.HardwareAdaptor;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;

public class Intake extends Loopable {

	public enum State {
		NEUTRAL, INTAKE, EXHAUST
	}

	CANTalon talon;
	State state;
	AnalogInput breakbeam = HardwareAdaptor.kBreakbeam;
	
	double intakeSpeed = -0.35;

	public Intake(int talonId) {
		this.talon = new CANTalon(talonId);
	}

	public void setState(State state) {
		this.state = state;
	}

	
	public String getLog() {
		return "Intaking?" + (state == State.INTAKE) + 
				"Intake Speed" + intakeSpeed;
	}

	public void update() {
		if(state == State.NEUTRAL) {
			talon.set(0);
		} else if(state == State.INTAKE) {			
			talon.set(intakeSpeed);
		} else if(state == State.EXHAUST) {
			talon.set(-intakeSpeed);
		}
	}

	public boolean breakbeamTriggered() {
		return breakbeam.getVoltage() >= 1.5;
	}

}
