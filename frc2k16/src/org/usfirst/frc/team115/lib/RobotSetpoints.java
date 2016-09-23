package org.usfirst.frc.team115.lib;

public class RobotSetpoints {
	
	public enum FlywheelAction {
		NONE, INTAKE, BATTER_SHOOT, SHOOT
	}
	
	public enum IntakeAction {
		NONE, INTAKE, EXHAUST
	}
	
	public enum PunchAction {
		NONE, PUNCH, INTAKE
	}
	
	public enum ArmPosition {
		INTAKE, OUTERWORKS, SPY, BATTER, LOWGOAL, NONE, MANUAL
	}
	
	public enum CamPosition {
		LOWBAR, STD, INCREMENT, DECREMENT
	}
	
	public FlywheelAction flywheelAction;
	public IntakeAction intakeAction;
	public PunchAction punchAction;
	public ArmPosition armSetpoint;
	public CamPosition camSetpoint;
	
	public void reset() {
		flywheelAction = FlywheelAction.NONE;
		intakeAction = IntakeAction.NONE;
		punchAction = PunchAction.NONE;
		camSetpoint = CamPosition.LOWBAR;
		//armSetpoint = ArmPosition.NONE;
	}

}
