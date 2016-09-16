package org.usfirst.frc.team115.robot.controllers;

import org.usfirst.frc.team115.trajectory.lib.TrajectoryFollower;
import org.usfirst.frc.team115.trajectory.lib.TrajectoryFollower.TrajectorySetpoint;

public class TrajectoryFollowingController extends Controller {

	private TrajectoryFollower follower;
	double goal;
	double error;
	double onTargetDelta;
	double result;

	public TrajectoryFollowingController(double onTargetDelta, double kv, double ka, double kp, double ki, double kd, TrajectoryFollower.TrajectoryConfig config) {
		follower = new TrajectoryFollower();
		follower.configure(kp, ki, kd, kv, ka, config);
		this.onTargetDelta = onTargetDelta;
	}

	public void setGoal(TrajectoryFollower.TrajectorySetpoint currentState, double goal) {
		this.goal = goal;
		follower.setGoal(currentState, goal);
	}

	public double getGoal() {
		return goal;
	}

	public double update(double position, double velocity) {
		error = goal - position;
		result = follower.calculate(position, velocity);
		return result;
	}

	public double get() {
		return result;
	}

	public void reset() {
		result = 0;
		error = 0;
		follower.setGoal(follower.getCurrentSetpoint(), goal);
	}
	
	public TrajectorySetpoint getTrajSetpoint() {
		return follower.getCurrentSetpoint();
	}
	
	public boolean isFinished() {
		return follower.isFinished();
	}

	public boolean onTarget() {
		return follower.isFinished() &&
				Math.abs(error) < onTargetDelta;
	}


}
