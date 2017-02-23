package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {

	public TankDrive() {
		super();
		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		Robot.drive.tankDrive(Robot.oi.getJoystick(), Robot.oi.getLeftAxis(), Robot.oi.getRightAxis());
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
