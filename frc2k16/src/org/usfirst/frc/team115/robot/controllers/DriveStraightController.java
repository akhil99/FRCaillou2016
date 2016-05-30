package org.usfirst.frc.team115.robot.controllers;

import org.usfirst.frc.team115.lib.DriveSignal;
import org.usfirst.frc.team115.lib.DriveState;
import org.usfirst.frc.team115.trajectory.lib.TrajectoryFollower;
import org.usfirst.frc.team115.trajectory.lib.TrajectoryFollower.TrajectorySetpoint;

public class DriveStraightController extends Controller {
	
	
	private TrajectoryFollowingController distanceController;
	private double goalSetpoint;
	private DriveState relativeState;
	
	public DriveStraightController(DriveState drivestate, double goalSetpoint, double maxVelocity) {
		TrajectoryFollower.TrajectoryConfig config = new TrajectoryFollower.TrajectoryConfig();
		config.dt = 1 / 200; //replace with constants
		config.maxAcc = 10; //replace with constants
		config.maxVel = maxVelocity;
		
		distanceController = new TrajectoryFollowingController(0, 0, 0, 0, 0, 0, config); //add constants
		
		TrajectorySetpoint initSetpoint = new TrajectorySetpoint();
		initSetpoint.pos = encoderDist(drivestate);
		initSetpoint.vel = encoderVel(drivestate);
		distanceController.setGoal(initSetpoint, goalSetpoint);
		
		relativeState = new DriveState(
				drivestate.getLeftDistance(),
				drivestate.getRightDistance(),
				0,
				0,
				drivestate.getHeading(),
				drivestate.getHeadingVelocity());
		
	}
	
	public DriveSignal update(DriveState state) {
		distanceController.update(encoderDist(state), encoderVel(state));
		double throttle = distanceController.get();
		double turn = 0; //use pid to calculate
		
		return new DriveSignal(throttle + turn, throttle - turn);
	}
	
	
	public static double encoderDist(DriveState state) {
		return (state.getLeftDistance() + state.getRightDistance()) / 2.0;
	}
	
	public static double encoderVel(DriveState state) {
		return (state.getLeftVelocity() + state.getRightVelocity()) / 2.0;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
