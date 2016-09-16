package org.usfirst.frc.team115.trajectory.lib;

import edu.wpi.first.wpilibj.Timer;

public class TrajectoryFollower {

	public static class TrajectoryConfig {
		public double dt;
		public double maxAcc;
		public double maxVel;

		public String toString() {
			return "dt: " + dt + ", max_acc" + maxAcc + ", max_vel: " + maxVel;
		}
	}

	public static class TrajectorySetpoint {
		public double pos;
		public double vel;
		public double acc;

		public String toString() {
			return "pos: "  + pos + ", vel: " + vel + ", acc" + acc;
		}
	}

	private double kp;
	private double ki;
	private double kd;
	private double kv;
	private double ka;
	private double lastError;
	private double errorSum;
	private TrajectoryConfig config = new TrajectoryConfig();
	private TrajectorySetpoint currentState = new TrajectorySetpoint();
	private TrajectorySetpoint nextState = new TrajectorySetpoint();
	private double lastTimestamp;
	private double goalPosition;
	private boolean reset = true;

	public TrajectoryFollower() {}

	public void configure(double kp, double ki, double kd, double kv, double ka, TrajectoryConfig config) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		this.kv = kv;
		this.ka = ka;
		this.config = config;
	}
	
	public void setGoal(TrajectorySetpoint currentState, double goal) {
		this.currentState = currentState;
		this.goalPosition = goal;
		reset = true;
		errorSum = 0;
	}
	
	public double calculate(double position, double velocity) {
		double dt = config.dt;
		System.out.println("DT" + config.dt);
		if(!reset) {
			double now = Timer.getFPGATimestamp();
			dt = now - lastTimestamp;
			lastTimestamp = now;
		} else {
			lastTimestamp = Timer.getFPGATimestamp();
		}

		if(isFinished()) {
			System.out.println("finished");
			currentState.pos = goalPosition;
			currentState.vel = 0;
			currentState.acc = 0;
		} else {
			double distanceToGo = goalPosition - currentState.pos;
			double currentVelocity = currentState.vel;
			double currentVelocitySqr = currentVelocity * currentVelocity;
			boolean inverted = false;
			if(distanceToGo < 0) {
				//moving backwards
				inverted = true;
				distanceToGo *= -1;
				currentVelocity *= -1;
			}

			//Find max and min reachable velocity
			double maxReachableVelocityDiscriminant = currentVelocitySqr / 2.0 + config.maxAcc * distanceToGo;
			double minReachableVelocityDiscriminant = currentVelocitySqr / 2.0 - config.maxAcc * distanceToGo;

			double cruiseVelocity = currentVelocity;

			if(minReachableVelocityDiscriminant < 0 || cruiseVelocity < 0) {
				//cruise velocity is the minimum value between the maximum velocity or the max reachable velocity
				System.out.println("Max" + maxReachableVelocityDiscriminant);
				cruiseVelocity = Math.min(config.maxVel, Math.sqrt(maxReachableVelocityDiscriminant));
			}
			
			//Finding time and the distance for acceleration period
			double tAccel = (cruiseVelocity - currentVelocity) / config.maxAcc;
			double accelDist = currentVelocity * tAccel + .5 * config.maxAcc * tAccel * tAccel;

			//Finding time and distance for deceleration period
			double tDecel = Math.abs(cruiseVelocity / config.maxAcc);
			double decelDist = cruiseVelocity * tDecel * - .5 * config.maxAcc * tDecel * tDecel;

			//Finding time and distance for cruising period
			System.out.println("CruiseVel" + cruiseVelocity);
			double cruiseDist = Math.max(0,  distanceToGo - accelDist - decelDist);
			double tCruise = Math.abs(cruiseDist / cruiseVelocity);

			if(tAccel >= dt) {
				//accelerating 
				nextState.pos = currentVelocity * dt + 0.5 * config.maxAcc * dt * dt; // x = vt + (1/2)at^2
				nextState.vel = currentVelocity + config.maxAcc * dt; // v = v + at
				nextState.acc = config.maxAcc; // max acceleration
			} else if(tAccel + tCruise >= dt) {
				//cruising
				nextState.pos = accelDist + cruiseVelocity * (dt - tAccel); // x = x0 + vt
				nextState.vel = cruiseVelocity; // cruise velocity
				nextState.acc = 0; // no acceleration
			} else if(tAccel + tCruise + tDecel >= dt) {
				//decelerating
				double deltaT = dt - tAccel - tCruise;
				nextState.pos = accelDist + cruiseDist + cruiseVelocity * deltaT - 0.5 * config.maxAcc * deltaT * deltaT; // x = vt - (1/2)at^2
				nextState.vel = cruiseVelocity - config.maxAcc * dt; // v = v - at
				nextState.acc = -config.maxAcc; // negative max acceleration
			} else {
				//over
				nextState.pos = distanceToGo;
				nextState.vel = 0;
				nextState.acc = 0;
			}

			if (inverted) {
				//going backwards
				nextState.pos *= -1;
				nextState.vel *= -1;
				nextState.acc *= -1;
			}

			currentState.pos += nextState.pos;
			currentState.vel = nextState.vel;
			currentState.acc = nextState.acc;
		}

		double error = currentState.pos - position;

		if(reset) {
			//prevent jump in derivative
			reset = false;
			lastError = error;
			errorSum = error;
		}
		
		System.out.println("dt" + config.dt);
		//calculate output using feedforward and feedback
		double output = kp * error + kd
        * ((error - lastError) / dt - currentState.vel)
        + (kv * currentState.vel + ka * currentState.acc);

		if(output < 1.0 && output > -1.0) {
			//add to error sum only if output isn't already max or min
			errorSum += error * dt;
		}
		output += ki * errorSum;

		lastError = error;
		
		System.out.println("TrajectoryFollowerOutput" + Double.toString(output));

		return output;
	}

	public boolean isFinished() {
		return Math.abs(currentState.pos - goalPosition) < 1E-3
				&& Math.abs(currentState.vel) < 1E-2;
	}
	
	public double getGoal() {
		return goalPosition;
	}
	
	public TrajectorySetpoint getCurrentSetpoint() {
		return currentState;
	}


}
