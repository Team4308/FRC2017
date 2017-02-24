package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Abstract superclass containing all common methods and functions that are
 * required for autonomous control of the {@link DriveTrain}.
 * 
 * @author Michael Brown
 *
 */
public abstract class AutoDrive extends Command {

	public AutoDrive() {
		this(RobotMap.Game.maxTimeSeconds);
	}

	public AutoDrive(double timeout) {
		super(timeout);
		Robot.drive.resetEncoders();
		Robot.drive.enable();
		requires(Robot.drive);
	}

	@Override
	protected boolean isFinished() {
		return Robot.drive.onTarget() || isTimedOut();
	}

	@Override
	protected void end() {
		Robot.drive.disable();
		Robot.drive.stopMotor();
	}
}
