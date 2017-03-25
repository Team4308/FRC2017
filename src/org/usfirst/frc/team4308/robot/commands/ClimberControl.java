package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Continuous command that sends the state of a (specified in {@link IO}) button which controls the motors on the {@link Climber} subsystem.
 * 
 * @author Michael Brown
 *
 */
public class ClimberControl extends InstantCommand {

	private boolean on = false;

	public ClimberControl() {
		super();
		requires(Robot.climber);
	}

	@Override
	protected void execute() {
		on = !on;

		if (on) {
			Robot.climber.set(RobotMap.Climb.maxForward);
		} else {
			Robot.climber.stopMotor();
		}
	}
}
