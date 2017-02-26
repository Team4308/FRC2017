package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Continuous command that sends the state of two (specified in {@link OI})
 * buttons which control the motors on the {@link Climber} subsystem.
 * 
 * @author Michael Brown
 *
 */
public class ClimberControl extends InstantCommand {

	public ClimberControl() {
		super();
		requires(Robot.climber);
	}

	@Override
	protected void execute() {
		Robot.climber.set(RobotMap.Climb.maxForward);
	}

	@Override
	protected void end() {
		Robot.climber.stopMotor();
	}
}
