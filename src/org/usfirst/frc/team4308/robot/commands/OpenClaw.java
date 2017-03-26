package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class OpenClaw extends InstantCommand {

	public OpenClaw() {
		super();
	}

	@Override
	protected void execute() {
		Robot.arm.claw(false);
	}

}
