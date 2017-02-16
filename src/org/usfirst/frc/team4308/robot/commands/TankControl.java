package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

public class TankControl extends Control {

	public TankControl() {
		super();
	}

	@Override
	protected void execute() {
		Robot.drivetrain.tankDrive(Robot.oi.getJoystick());
	}

}
