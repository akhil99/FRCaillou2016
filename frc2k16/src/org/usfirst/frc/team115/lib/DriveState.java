package org.usfirst.frc.team115.lib;

public class DriveState {
	
	private double leftDist;
	private double rightDist;
	private double leftVel;
	private double rightVel;
	private double heading;
	private double headingVel;
	
	public DriveState(double leftDist, double rightDist, double leftVel, double rightVel, double heading, double headingVel) {
		this.leftDist = leftDist;
		this.rightDist = rightDist;
		this.leftVel = leftVel;
		this.rightVel = rightVel;
		this.heading = heading;
		this.headingVel = headingVel;
	}
	
	public double getLeftDistance() {
		return leftDist;
	}
	
	public double getRightDistance() {
		return rightDist;
	}
	
	public double getLeftVelocity() {
		return leftVel;
	}
	
	public double getRightVelocity() {
		return rightVel;
	}
	
	public double getHeading() {
		return heading;
	}
	
	public double getHeadingVelocity() {
		return headingVel;
	}
	
	public void reset(double leftDist, double rightDist, double leftVel, double rightVel, double heading, double headingVel) {
		this.leftDist = leftDist;
		this.rightDist = rightDist;
		this.leftVel = leftVel;
		this.rightVel = rightVel;
		this.heading = heading;
		this.headingVel = headingVel;
	}

}
