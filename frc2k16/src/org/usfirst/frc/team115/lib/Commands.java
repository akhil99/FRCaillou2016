package org.usfirst.frc.team115.lib;

public class Commands {
	
	public enum FlywheelRequest {
		NONE, INTAKE, BATTER_SHOOT, SHOOT
	}
	
	public enum IntakeRequest {
		NONE, INTAKE
	}
	
	public enum PunchRequest {
		PUNCH, NONE
	}
	
	public IntakeRequest intakeRequest;
	public FlywheelRequest flywheelRequest;
	public PunchRequest punchRequest;

}
