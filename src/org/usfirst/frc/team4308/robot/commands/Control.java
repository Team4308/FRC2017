package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public abstract class Control extends Command {

	public Control() {
		super();
		requires(Robot.drivetrain);
	}

	public Control(double timeout) {
		super(timeout);
		requires(Robot.drivetrain);
	}

	@Override
	protected abstract void execute();

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drivetrain.stopMotor();
	}

}
