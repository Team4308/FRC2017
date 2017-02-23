package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberControl extends Command {

	public ClimberControl() {
		super();
		requires(Robot.climber);
	}

	@Override
	protected void execute() {
		Robot.climber.set(Robot.oi.getClimbButtons().getInteger());

		/*
		 * switch (Robot.oi.getClimbButtons().getInteger()) { case 0:
		 * Robot.climber.set(RobotMap.CLIMBER.restingSpeed); break; case 1:
		 * Robot.climber.set(RobotMap.CLIMBER.maxForward); case -1:
		 * Robot.climber.set(RobotMap.CLIMBER.maxBackward); }
		 */
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
