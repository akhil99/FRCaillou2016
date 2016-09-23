package org.usfirst.frc.team115.lib;

public class Commands {
	
	public enum FlywheelRequest {
		NONE, INTAKE, BATTER_SHOOT, SHOOT
	}
	
	public enum IntakeRequest {
		NONE, INTAKE, EXHAUST
	}
	
	public enum PunchRequest {
		PUNCH, NONE, INTAKE
	}
	
	public enum ArmRequest {
		INTAKE, OUTERWORKS, SPY, BATTER, LOWGOAL, MANUAL, RESET, NONE
	}
	
	public enum ActiveIntakeRequest {
		INTAKE, EXHAUST, NONE
	}
	
	public enum CameraServoRequest {
		LOWBAR, STD, UP, DOWN
	}
	
	public IntakeRequest intakeRequest;
	public ActiveIntakeRequest activeIntakeRequest;
	public FlywheelRequest flywheelRequest;
	public PunchRequest punchRequest;
	public ArmRequest armRequest;
	public CameraServoRequest camRequest;
	public boolean cancelRoutine;
}
