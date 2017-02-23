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
		Robot.drive.setMaxOutput(slow ? RobotMap.Drive.Slow.slow : RobotMap.Drive.Slow.normal);
		slow = !slow;
		end();
	}

}
