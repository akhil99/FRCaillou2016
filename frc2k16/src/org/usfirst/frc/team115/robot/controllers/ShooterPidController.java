package org.usfirst.frc.team115.robot.controllers;

import org.usfirst.frc.team115.robot.HardwareAdaptor;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class ShooterPidController extends PidController {
	
	private CANTalon talon;
	private double tolerance;
	
	public ShooterPidController(double p, double i, double d, double tolerance, CANTalon talon) {
		super(p, i, d);
		this.talon = talon;
		talon.setPID(0.02, 0.001, 0, 0, 0, 0, 1);
		talon.setPID(p, i, d, 0, 0, 0, 0);
		talon.configNominalOutputVoltage(+0f, -0f);
    talon.configPeakOutputVoltage(+12f, -12f);
    talon.setAllowableClosedLoopErr(0);
	}

	public void reset() {
		int absolutePosition = talon.getPulseWidthPosition() & 0xFFF;
		talon.setEncPosition(absolutePosition);
		talon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	}
	
	@Override
	public void update() {
		if(HardwareAdaptor.kTopLimit.get()) {
			reset();
		} else if (HardwareAdaptor.kBottomLimit.get()
				|| Math.abs(talon.getPosition() - setpoint) <= tolerance) {
			talon.setProfile(1);
		}
			talon.set(setpoint);
	}
	
	public boolean onTarget() {
		return Math.abs(talon.getPosition() - setpoint) <= tolerance;
	}

}
