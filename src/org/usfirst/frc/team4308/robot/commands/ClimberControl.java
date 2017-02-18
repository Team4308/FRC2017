package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberControl extends Command {

	public ClimberControl() {
		this(RobotMap.GAME.maxTimeSeconds);
	}

	public ClimberControl(double timeout) {
		super(timeout);
		requires(Robot.climber);
	}

	@Override
	protected void execute() {
		Robot.climber.execute();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.stopMotor();
		Robot.climber.stopMotor();
	}
}
