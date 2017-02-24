package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

/**
 * Autonomous command responsible for bi-directional linear movement of the
 * robot, determined by either user-specified distances or by default values
 * (specified in {@link OI}).
 * 
 * @author Michael Brown
 *
 */
public class DriveLinear extends AutoDrive {

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
		Robot.drive.linearInitialize();
		Robot.drive.setSetpoint(distance);
		Robot.drive.setPercentTolerance(RobotMap.Autonomous.distancePercentTolerance);
	}

	@Override
	protected void end() {
		Robot.drive.resetEncoders();
		Robot.drive.linearInitialize();
		super.end();
	}

}
