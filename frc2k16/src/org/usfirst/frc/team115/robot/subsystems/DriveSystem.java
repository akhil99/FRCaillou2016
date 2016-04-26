package org.usfirst.frc.team115.robot.subsystems;

import java.util.function.DoubleFunction;

import org.usfirst.frc.team115.lib.DriveBase;
import org.usfirst.frc.team115.lib.Loopable;

public class DriveSystem {

	DriveBase drive;
	private static final double SENSITIVITY = 0.75;
	private DoubleFunction<Double> limiter = limiter(-1.0, 1.0);

	public DriveSystem(DriveBase drive){
		this.drive = drive;
	}
	
	public double squareInputs(double speed) {
    return Math.signum(speed) * speed * speed;
  }

	private double oldWheel = 0.0;
	private double negativeInertiaAccumulator = 0.0;
	private double quickStopAccumulator = 0.0;
	private double wheelDeadband = 0.02;
	private double throttleDeadband = 0.02;

	/**
	 * Drive the DriveTrain in the Austin/Cheesy style. Originally pioneered by Austin
	 * Schuh in 2008, this style of drive has been stolen 254's public repos by practically every
	 * single team.
	 *
	 * @param throttle  the throttle to drive with
	 * @param wheel     the wheel to drive with
	 * @param quickturn whether or not to apply quickturn
	 */

	public void drive(double throttle, double wheel, boolean quickturn) {
		wheel = limiter.apply(wheel);
		throttle = limiter.apply(throttle);

		double negativeInertia = wheel - oldWheel;
		oldWheel = wheel;

		double wheelNonLinearity = 0.6; // tune this

		wheel = handleDeadband(wheel, wheelDeadband);
		throttle = handleDeadband(throttle, throttleDeadband);
		
		wheel = dampen(wheel, wheelNonLinearity);
    wheel = dampen(wheel, wheelNonLinearity);

		double leftPwm, rightPwm, overPower;
		double sensitivity = SENSITIVITY;

		double angularPower;
		double linearPower;

		double negativeInertiaScalar;

		if (wheel * negativeInertia > 0) {
			negativeInertiaScalar = 2.5;
		} else {
			if (Math.abs(wheel) > 0.65) {
				negativeInertiaScalar = 5.0;
			} else {
				negativeInertiaScalar = 3.0;
			}
		}

		double negativeInertiaPower = negativeInertia * negativeInertiaScalar;
		negativeInertiaAccumulator += negativeInertiaPower;

		wheel += negativeInertiaPower;

		if (negativeInertiaAccumulator > 1) {
			negativeInertiaAccumulator -= 1;
		} else if (negativeInertiaAccumulator < -1) {
			negativeInertiaAccumulator += 1;
		} else {
			negativeInertiaAccumulator = 0;
		}

		linearPower = throttle;

		if (quickturn) {
			if (Math.abs(linearPower) < 0.2) {
				double alpha = 0.1;
				quickStopAccumulator =
						(1 - alpha) * quickStopAccumulator + alpha * limiter(0.0, 1.0).apply(wheel) * 5;
			}
			overPower = 1.0;
			sensitivity = 1.0;
			angularPower = wheel;
		} else {
			overPower = 0.0;
			angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
			if (quickStopAccumulator > 1) {
				quickStopAccumulator -= 1.0;
			} else if (quickStopAccumulator < -1) {
				quickStopAccumulator += 1.0;
			} else {
				quickStopAccumulator = 0.0;
			}
		}

		rightPwm = leftPwm = linearPower;
		leftPwm += angularPower;
		rightPwm -= angularPower;

		if (leftPwm > 1.0) {
			rightPwm -= overPower * (leftPwm - 1.0);
			leftPwm = 1.0;
		} else if (rightPwm > 1.0) {
			leftPwm -= overPower * (rightPwm - 1.0);
			rightPwm = 1.0;
		} else if (leftPwm < -1.0) {
			rightPwm += overPower * (-1.0 - leftPwm);
			leftPwm = -1.0;
		} else if (rightPwm < -1.0) {
			leftPwm += overPower * (-1.0 - rightPwm);
			rightPwm = -1.0;
		}

		drive.setOpenLoop(leftPwm, rightPwm);
	}

	private DoubleFunction<Double> limiter(double minimum, double maximum) {
		if (maximum < minimum) {
			throw new IllegalArgumentException("The minimum value cannot exceed the maximum value");
		}

		return (double value) -> {
			if (value > maximum) {
				return maximum;
			}
			if (value < minimum) {
				return minimum;
			}
			return value;
		};
	}

	public double handleDeadband(double val, double deadband){
		return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	}
	
	private static double dampen(double wheel, double wheelNonLinearity) {
    double factor = Math.PI * wheelNonLinearity;
    return Math.sin(factor * wheel) / Math.sin(factor);
  }

}
