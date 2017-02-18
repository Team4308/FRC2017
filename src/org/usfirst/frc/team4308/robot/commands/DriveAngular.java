package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

public class DriveAngular extends DriveControl {

	private static final int defaultTimeout = 2;
	private static final double tolerance = 2.0;

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
		requires(Robot.navx);
	}

	@Override
	protected void execute() {
		error = angle - Robot.navx.yaw();
		if (error > angle + tolerance && error > angle - tolerance) {
			Robot.drive.setMotorOutputs(maxSpeed, -maxSpeed); // Turn right TODO might need to flip the negative on these
		} else if (error < angle + tolerance && error < angle - tolerance) {
			Robot.drive.setMotorOutputs(-maxSpeed, maxSpeed); // Turn left TODO might need to flip the negative on these
		} else {
			end();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
