package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveControl extends Command {

	public DriveControl() {
		this(RobotMap.Game.maxTimeSeconds);
	}

	public DriveControl(double timeout) {
		super(timeout);
		requires(Robot.drive);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.stopMotor();
	}
}
