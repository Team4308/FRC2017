package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

public class DriveAngular extends DriveControl {

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
		Robot.drive.enable();
		Robot.drive.resetEncoders();
		// TODO: change angle degrees to encoder readings
		Robot.drive.setSetpoint(angle);
		Robot.drive.angularInitialize();
		Robot.drive.setPercentTolerance(RobotMap.Autonomous.angularPercentTolerance);

	}

	@Override
	protected boolean isFinished() {
		return Robot.drive.onTarget() || isTimedOut();
	}

	@Override
	protected void end() {
		Robot.drive.disable();
		Robot.drive.resetEncoders();
		Robot.drive.linearInitialize();
		super.end();
	}

}
