package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Continuous command that sends the state of a (specified in {@link IO}) button which controls the motors on the {@link Climber} subsystem.
 * 
 * @author Michael Brown
 *
 */
public class ClimberControl extends ToggleCommand {

	public ClimberControl() {
		super(false);
		requires(Robot.climber);
		toggleOff();
	}

	@Override
	protected void toggleOn() {
		Robot.climber.set(RobotMap.Climb.maxForward);
		DriverStation.reportWarning("moving forward", true);
	}

	@Override
	protected void toggleOff() {
		Robot.climber.stopMotor();
		DriverStation.reportWarning("stopping", true);
	}
}
