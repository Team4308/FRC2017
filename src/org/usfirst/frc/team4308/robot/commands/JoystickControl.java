package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickControl extends Command {

	public JoystickControl() {
		super();
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.tankDrive(Robot.oi.getJoystick());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

}
