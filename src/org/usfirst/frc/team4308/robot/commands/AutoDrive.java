package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Abstract superclass containing all common methods and functions that are
 * required for autonomous control of the {@link DriveTrain}.
 * 
 * @author Michael Brown
 *
 */
public abstract class AutoDrive extends PIDCommand {

	public AutoDrive() {
		super(RobotMap.Constant.proportional, RobotMap.Constant.integral, RobotMap.Constant.differential,
				RobotMap.Constant.feedForward);
		requires(Robot.drive);
	}

	@Override
	protected boolean isFinished() {
		return getPIDController().onTarget();
	}

	@Override
	protected void end() {
		Robot.drive.stopMotor();
	}

}
