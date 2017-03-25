package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;

/**
 * Instantly firing command that will modify the {@link DriveTrain}'s maximum motor output, allowing for the same input ranges, but a much reduced corresponding motor speed.
 * 
 * @author Ningsong Shen
 *
 */
public class SlowMode extends ToggleCommand {

	public SlowMode() {
		super(false);
		requires(Robot.drive);
	}

	@Override
	protected void toggleOn() {
		Robot.drive.setMaxOutput(RobotMap.Drive.Slow.slow);
	}

	@Override
	protected void toggleOff() {
		Robot.drive.setMaxOutput(RobotMap.Drive.Slow.normal);
	}
}
