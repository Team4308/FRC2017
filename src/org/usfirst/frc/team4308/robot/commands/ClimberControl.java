package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Continuous command that sends the state of a (specified in {@link IO}) button which controls the motors on the {@link Climber} subsystem.
 * 
 * @author Michael Brown
 *
 */
public class ClimberControl extends InstantCommand {

	private static boolean isRunning = true;

	public ClimberControl() {
		super();
		requires(Robot.climber);
	}

	@Override
	protected void execute() {
		isRunning = !isRunning;

		if (isRunning) {
			Robot.climber.set(RobotMap.Climb.maxForward);
		} else {
			Robot.climber.stopMotor();
		}
	}
}
