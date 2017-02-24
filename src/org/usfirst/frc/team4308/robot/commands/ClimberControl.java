package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Continuous command that sends the state of two (specified in {@link OI})
 * buttons which control the motors on the {@link Climber} subsystem.
 * 
 * @author Michael Brown
 *
 */
public class ClimberControl extends Command {

	public ClimberControl() {
		super();
		requires(Robot.climber);
	}

	@Override
	protected void execute() {
		Robot.climber.set(Robot.oi.getClimbButtons().getInteger());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.climber.stopMotor();
	}
}
