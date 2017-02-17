package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberControl extends Command {

	public ClimberControl() {
		this(RobotMap.GAME.maxTimeSeconds);
	}

	public ClimberControl(double timeout) {
		super(timeout);
	}

	@Override
	protected void execute() {
		switch (Robot.oi.getClimbButtons().getInteger()) {
		case 0:
			Robot.climber.set(Climber.restingSpeed);
			break;
		case 1:
			Robot.climber.set(Climber.maxForward);
			break;
		case -1:
			Robot.climber.set(Climber.maxBackward);
			break;
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
