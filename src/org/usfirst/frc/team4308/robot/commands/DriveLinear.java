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
		super();
		setSetpoint(distance);
		Robot.drive.resetEncoders();
		getPIDController().setPercentTolerance(RobotMap.Autonomous.distancePercentTolerance);
	}

	@Override
	protected double returnPIDInput() {
		return Robot.drive.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.drive.setLeftRightMotorOutputs(output, output);
	}
	
	@Override
	protected void end() {
		Robot.drive.resetEncoders();
		super.end();
	}

}
