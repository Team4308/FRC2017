package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.subsystems.DriveSamson;

public class DriveAngular extends Control {

	private static final int defaultTimeout = 2;
	private static final double tolerance = 2.0;

	private DriveSamson drive;
	private final double angle;
	private final double maxSpeed;
	private double error;

	public DriveAngular(DriveSamson drive, double angle) {
		this(drive, angle, RobotMap.AUTONOMOUS.maxRotateSpeed);
		requires(Robot.navx);
	}

	public DriveAngular(DriveSamson drive, double angle, double maxSpeed) {
		this(drive, angle, maxSpeed, defaultTimeout);
	}

	public DriveAngular(DriveSamson drive, double angle, double maxSpeed, double timeout) {
		super(timeout);
		this.drive = drive;
		this.angle = angle;
		this.maxSpeed = maxSpeed;
	}

	@Override
	protected void execute() {
		error = angle - Robot.navx.yaw();
		if (error > angle + tolerance && error > angle - tolerance) {
			drive.setMotorOutputs(maxSpeed, -maxSpeed); // Turn right TODO might need to flip the negative on these
		} else if (error < angle + tolerance && error < angle - tolerance) {
			drive.setMotorOutputs(-maxSpeed, maxSpeed); // Turn left TODO might need to flip the negative on these
		} else {
			end();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
