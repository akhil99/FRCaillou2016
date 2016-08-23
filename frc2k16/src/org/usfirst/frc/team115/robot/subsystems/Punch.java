package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.Loopable;
import org.usfirst.frc.team115.robot.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Punch extends Loopable {

	public enum State {
		INTAKE, PUNCH, NONE
	}

	CANTalon punchWheel;
	DoubleSolenoid solenoid;
	State state;

	public Punch(int a, int b) {
		punchWheel = new CANTalon(Constants.kPunchWheel);
		//this.solenoid = new DoubleSolenoid(1, a, b);
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getLog() {
		return "Extended?" + (state == State.PUNCH);
	}

	public void update() {
		if(state == State.INTAKE) {
			punchWheel.set(-0.75);
			//solenoid.set(Value.kReverse);
		} if(state == State.PUNCH) {	
			punchWheel.set(0.9);
			//solenoid.set(Value.kForward);
		} if(state == State.NONE) {
			punchWheel.set(0);
		}
	}
}
