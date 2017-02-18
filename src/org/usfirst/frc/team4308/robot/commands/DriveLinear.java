package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.subsystems.DriveSamson;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLinear extends Command {

	private static final int defaultTimeout = 2;
	private static final double tolerance = 0.1;
	private static final double kP = -1.0 / 5.0;

	private DriveSamson drive;
	private final double distance;
	private final double maxSpeed;
	private double error;

	public DriveLinear(DriveSamson drive) {
		this(drive, RobotMap.AUTONOMOUS.defaultDistance);
	}

	public DriveLinear(DriveSamson drive, double distance) {
		this(drive, distance, RobotMap.AUTONOMOUS.maxLinearSpeed);
	}

	public DriveLinear(DriveSamson drive, double distance, double maxSpeed) {
		this(drive, distance, maxSpeed, defaultTimeout);
	}

	public DriveLinear(DriveSamson drive, double distance, double maxSpeed, double timeout) {
		super(timeout);
		this.drive = drive;
		this.distance = distance;
		this.maxSpeed = Math.abs(maxSpeed);
	}

	@Override
	protected void execute() {
		error = distance - drive.getDistance();
		// Uses the speed (maxSpeed * kP * error) within the range of -maxSpeed to maxSpeed
		double speed = Math.max(Math.min(maxSpeed * kP * error, maxSpeed), -maxSpeed);
		drive.setMotorOutputs(speed, speed);
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(error) <= tolerance) || isTimedOut();
	}

	@Override
	protected void end() {
		drive.stopMotor();
	}

}
