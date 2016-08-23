package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.Loopable;
import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.controllers.Controller;
import org.usfirst.frc.team115.robot.controllers.ShooterPidController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class ShooterArm extends Loopable {
	
	CANTalon arm;
	Controller controller;
	
	public ShooterArm() {
		arm = new CANTalon(Constants.kRightShooter);
		int absolutePosition = arm.getPulseWidthPosition() & 0xFFF;
		arm.setEncPosition(absolutePosition);
		arm.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		arm.reverseSensor(false);
	}
	
	public void setPidSetpoint(double setpoint) {
		arm.changeControlMode(TalonControlMode.Position);
		if(!(controller instanceof ShooterPidController))
			controller = new ShooterPidController(0.75, 0.0, 0.01, 0.1, arm); //tune pid values
		((ShooterPidController)controller).setSetpoint(setpoint);
	}
	
	@Override
	public void update() {
		if(controller instanceof ShooterPidController) {
			((ShooterPidController)controller).update();
		}
	}
	
	public double getPosition() {
		return arm.getPosition();
	}
	
	public double getOutput() {
		return arm.getOutputVoltage();
	}
	
	public boolean isOnTarget() {
		return ((ShooterPidController)controller).onTarget();
	}

}
