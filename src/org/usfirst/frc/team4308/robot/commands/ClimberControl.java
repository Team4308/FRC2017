package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.robot.subsystems.Climber;

/**
 * Continuous command that sends the state of a (specified in {@link IO}) button which controls the motors on the {@link Climber} subsystem.
 * 
 * @author Michael Brown
 *
 */
public class ClimberControl extends ToggleCommand {

	private boolean forwards;

	public ClimberControl(boolean forwards) {
		super(false);
		this.forwards = forwards;
		requires(Robot.pneumatics);
		requires(Robot.climber);
		toggleOff();
	}

	@Override
	protected void toggleOn() {
		if (Robot.pneumatics != null)
			Robot.pneumatics.stop();
		if (Robot.climber != null)
			Robot.climber.set(forwards ? RobotMap.Climb.maxBackward : RobotMap.Climb.maxForward);
	}

	@Override
	protected void toggleOff() {
		if (Robot.climber != null)
			Robot.climber.stopMotor();
	}
}
