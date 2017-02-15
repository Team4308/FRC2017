package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLinear extends Command {

	private static final int defaultTimeout = 2;
	private static final double maxError = 0.1;
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
		this(defaultTimeout, distance, maxSpeed);
	}

	public DriveLinear(double distance, double maxSpeed, double timeout) {
		super(timeout);
		this.distance = distance;
		this.maxSpeed = maxSpeed;
	}

	@Override
	protected void execute() {
		error = distance - Robot.drivetrain.getDistance();
		if (maxSpeed * kP * error >= maxSpeed) {
			Robot.drivetrain.arcadeDrive(maxSpeed, 0);
		} else {
			Robot.drivetrain.arcadeDrive(maxSpeed * kP * error, 0);
		}
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(error) <= maxError) || isTimedOut();
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

}
