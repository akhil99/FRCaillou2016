package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.Loopable;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Punch extends Loopable {

	public enum State {
		RETRACT, PUNCH
	}

	DoubleSolenoid solenoid;
	State state;

	public Punch(int a, int b) {
		this.solenoid = new DoubleSolenoid(1, a, b);
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getLog() {
		return "Extended?" + (state == State.PUNCH);
	}

	public void update() {
		if(state == State.PUNCH) {			
			solenoid.set(Value.kForward);
		} else if(state == State.RETRACT) {
			solenoid.set(Value.kReverse);
		}
	}
}
