package org.usfirst.frc.team115.lib;

public class ManualRoutine extends Routine {

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RobotSetpoints update(Commands commands, RobotSetpoints currentSetpoints) {
		
		if(currentSetpoints.flywheelAction == RobotSetpoints.FlywheelAction.NONE) {
			if(commands.flywheelRequest == Commands.FlywheelRequest.SHOOT) {
				currentSetpoints.flywheelAction = RobotSetpoints.FlywheelAction.SHOOT;
			} else if(commands.flywheelRequest == Commands.FlywheelRequest.BATTER_SHOOT) {
				currentSetpoints.flywheelAction = RobotSetpoints.FlywheelAction.BATTER_SHOOT;
			} else if(commands.flywheelRequest == Commands.FlywheelRequest.INTAKE) {
				currentSetpoints.flywheelAction = RobotSetpoints.FlywheelAction.INTAKE;
			} else {
				currentSetpoints.flywheelAction = RobotSetpoints.FlywheelAction.NONE;
			}
		}
		
		if(currentSetpoints.intakeAction == RobotSetpoints.IntakeAction.NONE) {
			if(commands.activeIntakeRequest == Commands.ActiveIntakeRequest.INTAKE) {
				currentSetpoints.intakeAction = RobotSetpoints.IntakeAction.INTAKE;
			} else if(commands.activeIntakeRequest == Commands.ActiveIntakeRequest.EXHAUST) {
				currentSetpoints.intakeAction = RobotSetpoints.IntakeAction.EXHAUST;
			} else {
				currentSetpoints.intakeAction = RobotSetpoints.IntakeAction.NONE;
			}
		}
		
		if(currentSetpoints.punchAction == RobotSetpoints.PunchAction.NONE) {
			if(commands.punchRequest == Commands.PunchRequest.PUNCH) {
				currentSetpoints.punchAction = RobotSetpoints.PunchAction.PUNCH;
			} else if(commands.punchRequest == Commands.PunchRequest.INTAKE) {
				currentSetpoints.punchAction = RobotSetpoints.PunchAction.INTAKE;
			}
		}
		
		if(commands.armRequest == Commands.ArmRequest.INTAKE) {
			currentSetpoints.armSetpoint = RobotSetpoints.ArmPosition.INTAKE;
		} else if(commands.armRequest == Commands.ArmRequest.OUTERWORKS) {
			currentSetpoints.armSetpoint = RobotSetpoints.ArmPosition.OUTERWORKS;
		} 
		
		
		return currentSetpoints;
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
