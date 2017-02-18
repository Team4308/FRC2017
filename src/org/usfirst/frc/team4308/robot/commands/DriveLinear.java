package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

public class DriveLinear extends DriveControl {

	private static final int defaultTimeout = 2;
	private static final double tolerance = 0.1;
	private static final double kP = -1.0 / 5.0;

	private final double distance;
	private final double maxSpeed;
	private double error;

	public DriveLinear() {
		this(RobotMap.AUTONOMOUS.defaultDistance);
	}

	public DriveLinear(double distance) {
		this(distance, RobotMap.AUTONOMOUS.maxLinearSpeed);
	}

	public DriveLinear(double distance, double maxSpeed) {
		this(distance, maxSpeed, defaultTimeout);
	}

	public DriveLinear(double distance, double maxSpeed, double timeout) {
		super(timeout);
		this.distance = distance;
		this.maxSpeed = Math.abs(maxSpeed);
		Robot.drive.resetEncoders();
	}

	@Override
	protected void execute() {
		error = distance - Robot.drive.getDistance();
		// Uses the speed (maxSpeed * kP * error) within the range of -maxSpeed to maxSpeed
		double speed = Math.max(Math.min(maxSpeed * kP * error, maxSpeed), -maxSpeed);
		Robot.drive.setMotorOutputs(speed, speed);
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(error) <= tolerance) || isTimedOut();
	}

	@Override
	protected void end() {
		Robot.drive.stopMotor();
		Robot.drive.resetEncoders();
	}
}
