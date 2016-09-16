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
	
	public FlywheelAction flywheelAction;
	public IntakeAction intakeAction;
	public PunchAction punchAction;
	public ArmPosition armSetpoint;
	
	public void reset() {
		flywheelAction = FlywheelAction.NONE;
		intakeAction = IntakeAction.NONE;
		punchAction = PunchAction.NONE;
		//armSetpoint = ArmPosition.NONE;
	}

}
