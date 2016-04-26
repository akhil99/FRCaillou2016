package org.usfirst.frc.team115.lib;

import org.usfirst.frc.team115.lib.Commands.FlywheelRequest;
import org.usfirst.frc.team115.lib.Commands.IntakeRequest;
import org.usfirst.frc.team115.lib.Commands.PunchRequest;
import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.HardwareAdaptor;
import org.usfirst.frc.team115.robot.controllers.PunchAction;
import org.usfirst.frc.team115.robot.subsystems.Flywheel;
import org.usfirst.frc.team115.robot.subsystems.Intake;
import org.usfirst.frc.team115.robot.subsystems.Punch;
import org.usfirst.frc.team115.robot.subsystems.ShooterArm;

public class BehaviorManager {
	
	public Flywheel leftFlywheel = HardwareAdaptor.kLeftFlywheel;
	public Flywheel rightFlywheel = HardwareAdaptor.kRightFlywheel;
	public Intake intake = HardwareAdaptor.kIntake;
	public ShooterArm angler = HardwareAdaptor.kAngler;
	public Punch punch = HardwareAdaptor.kPunch;
	
	
	public void update(Commands commands) {
		
		if(commands.flywheelRequest == FlywheelRequest.INTAKE) {
			leftFlywheel.setSpeedPidSetpoint(Constants.kFlywheelIntake);
			rightFlywheel.setSpeed(-leftFlywheel.getVoltage());
		} else if (commands.flywheelRequest == FlywheelRequest.SHOOT) {
			leftFlywheel.setSpeedPidSetpoint(Constants.kFlywheelMax);
			rightFlywheel.setSpeed(-leftFlywheel.getVoltage());
		} else if(commands.flywheelRequest == FlywheelRequest.BATTER_SHOOT) {
			leftFlywheel.setSpeedPidSetpoint(Constants.kFlywheelBatter);
			rightFlywheel.setSpeed(-leftFlywheel.getVoltage());
		} else if(commands.flywheelRequest == FlywheelRequest.NONE){
			leftFlywheel.setSpeedPidSetpoint(0);
			rightFlywheel.setSpeed(-leftFlywheel.getVoltage());
		}
		
		if(commands.intakeRequest == IntakeRequest.INTAKE) {
			intake.setState(Intake.State.INTAKE);
		} else {
			intake.setState(Intake.State.NEUTRAL);
		}
		
		if(commands.punchRequest == PunchRequest.PUNCH) {
			punch.setState(Punch.State.PUNCH);
		} else {
			punch.setState(Punch.State.RETRACT);
		}
		
		
	}
	
}
