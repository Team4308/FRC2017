package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

public class DriveLinear extends DriveControl {

	public DriveLinear() {
		this(RobotMap.Autonomous.defaultDistance);
	}

	public DriveLinear(double distance) {
		this(distance, RobotMap.Autonomous.maxLinearSpeed);
	}

	public DriveLinear(double distance, double maxSpeed) {
		this(distance, maxSpeed, RobotMap.Autonomous.defaultTimeout);
	}

	public DriveLinear(double distance, double maxSpeed, double timeout) {
		super(timeout);
		Robot.drive.enable();
		Robot.drive.resetEncoders();
		Robot.drive.linearInitialize();
		Robot.drive.setSetpoint(distance);
		Robot.drive.setPercentTolerance(RobotMap.Autonomous.distancePercentTolerance);
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
