package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

public class DriveAngular extends Control {

	private static final int defaultTimeout = 2;
	private static final double maxError = 2.0;
	private static final double kP = -1.0 / 5.0;

	private final double angle;
	private final double maxSpeed;
	private double error;

	public DriveAngular(double angle) {
		this(angle, RobotMap.AUTONOMOUS.maxRotateSpeed);
	}

	public DriveAngular(double angle, double maxSpeed) {
		this(angle, maxSpeed, defaultTimeout);
	}

	public DriveAngular(double angle, double maxSpeed, double timeout) {
		super(timeout);
		this.angle = angle;
		this.maxSpeed = maxSpeed;
	}

	@Override
	protected void execute() {
		error = angle - Robot.navx.yaw();
		if (maxSpeed * kP * error >= maxSpeed) {
			if (angle < Robot.navx.yaw()) {
				Robot.drivetrain.arcadeDrive(0, -maxSpeed);
			} else {
				Robot.drivetrain.arcadeDrive(0, maxSpeed);
			}
		} else {
			if (angle < Robot.navx.yaw()) {
				Robot.drivetrain.arcadeDrive(0, -maxSpeed * kP * error);
			} else {
				Robot.drivetrain.arcadeDrive(0, maxSpeed * kP * error);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(error) <= maxError) || isTimedOut();
	}

}
