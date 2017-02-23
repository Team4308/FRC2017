package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {

	public ArcadeDrive() {
		super();
		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		Robot.drive.arcadeDrive(Robot.oi.getJoystick(), Robot.oi.getMoveAxis(), Robot.oi.getRotateAxis());
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
