package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SlowMode extends InstantCommand {

	private static boolean slow = false;

	public SlowMode() {
		super();
		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		Robot.drive.setMaxOutput(slow ? RobotMap.DRIVE.SLOW.slow : RobotMap.DRIVE.SLOW.normal);
		slow = !slow;
		end();
	}

}
