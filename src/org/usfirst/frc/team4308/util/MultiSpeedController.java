package org.usfirst.frc.team4308.util;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Wrapper class allowing any set of motor controllers to be synced with each
 * other, and controlled from a single point. Taken from a reddit post
 * explaining this concept.
 * 
 * @author Michael Brown
 *
 */
public class MultiSpeedController implements SpeedController {

	private static final double restingSpeed = 0.0;

	private final SpeedController[] controllers;
	private double speed;
	private boolean isInverted;

	public MultiSpeedController(SpeedController... controllers) {
		this(false, controllers);
	}

	public MultiSpeedController(boolean isInverted, SpeedController... controllers) {
		this.isInverted = isInverted;
		this.controllers = controllers;
		this.set(restingSpeed);
	}

	@Override
	public void pidWrite(double output) {
		this.set(output);

	}

	@Override
	public double get() {
		return this.speed;
	}

	@Override
	public void set(double speed) {
		double newSpeed = speed;
		if (isInverted)
			newSpeed = -speed;
		this.speed = newSpeed;
		for (SpeedController controller : controllers) {
			controller.set(newSpeed);
		}

	}

	@Override
	public void setInverted(boolean isInverted) {
		this.isInverted = isInverted;
	}

	@Override
	public boolean getInverted() {
		return this.isInverted;
	}

	@Override
	public void disable() {
		for (SpeedController controller : controllers) {
			controller.disable();
		}

	}

	@Override
	public void stopMotor() {
		for (SpeedController controller : controllers) {
			controller.stopMotor();
		}
		this.speed = (restingSpeed);
	}

}
