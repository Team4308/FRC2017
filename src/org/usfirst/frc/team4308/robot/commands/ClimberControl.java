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

	public static boolean enable;
	
	public ClimberControl() {
		super(false);
		
		toggleOff();
	}

	@Override
	protected void toggleOn() {
		enable = isToggled();
		//Robot.climber.set(RobotMap.Climb.maxForward);
		DriverStation.reportWarning("moving forward", true);
	}

	@Override
	protected void toggleOff() {
		enable = isToggled();
		//Robot.climber.stopMotor();
		DriverStation.reportWarning("stopping", true);
	}
}
