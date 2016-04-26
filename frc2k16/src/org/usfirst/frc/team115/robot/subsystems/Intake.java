package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.Loopable;

import edu.wpi.first.wpilibj.CANTalon;

public class Intake extends Loopable {

	public enum State {
		NEUTRAL, INTAKE
	}

	CANTalon talon;
	State state;
	
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
		}
	}

}
