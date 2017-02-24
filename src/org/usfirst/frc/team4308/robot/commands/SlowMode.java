package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Instantly firing command that will modify the {@link DriveTrain}'s maximum
 * motor output, allowing for the same input ranges, but a much reduced
 * corresponding motor speed.
 * 
 * @author Ningsong Shen
 *
 */
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
