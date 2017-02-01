package org.usfirst.frc.team4308.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class MultiSpeedController implements SpeedController {

	private static final double RESTING_SPEED = 0.0;

	private SpeedController[] controllers;
	private double speed = RESTING_SPEED;
	private boolean isInverted = false;

	public MultiSpeedController(SpeedController... controllers) {
		this.controllers = controllers;
		this.set(RESTING_SPEED);
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
		this.set(RESTING_SPEED);
	}

}
