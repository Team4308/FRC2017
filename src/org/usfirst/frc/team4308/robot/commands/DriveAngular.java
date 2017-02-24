package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

public class DriveAngular extends AutoDrive {

	public DriveAngular() {
		this(RobotMap.Autonomous.defaultOrientation);
	}

	public DriveAngular(double angle) {
		this(angle, RobotMap.Autonomous.maxRotateSpeed);
	}

	public DriveAngular(double angle, double maxSpeed) {
		this(angle, maxSpeed, RobotMap.Autonomous.defaultTimeout);
	}

	public DriveAngular(double angle, double maxSpeed, double timeout) {
		super(timeout);
		Robot.drive.angularInitialize();
		Robot.drive.setSetpoint(angle);
		Robot.drive.setPercentTolerance(RobotMap.Autonomous.angularPercentTolerance);
	}

	@Override
	protected void end() {
		Robot.drive.resetEncoders();
		Robot.drive.linearInitialize();
		super.end();
	}

}
