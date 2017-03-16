package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

/**
 * Autonomous command that will rotate the robot to a user-specified or default
 * (specified in {@link OI} orientation.
 * 
 * @author Michael Brown
 *
 */
public class DriveAngular extends AutoDrive {

	public DriveAngular() {
		this(RobotMap.Autonomous.defaultOrientation);
	}

	public DriveAngular(double angle) {
		super();
		setSetpoint(angle);
		getPIDController().setInputRange(-180.0f, 180.0f);
		getPIDController().setOutputRange(-1.0, 1.0);
		getPIDController().setAbsoluteTolerance(RobotMap.Autonomous.angularToleranceDegrees);
		getPIDController().setContinuous(true);
	}

	@Override
	protected double returnPIDInput() {
		return Robot.gyro.yaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setLeftRightMotorOutputs(-output, output);
	}

}
