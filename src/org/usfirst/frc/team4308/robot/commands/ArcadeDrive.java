package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Continuous command to send input from a joystick (specified in {@link IO}) to
 * the motors of the {@link DriveTrain}, controlling its movement.
 * 
 * @author Michael Brown
 *
 */
public class ArcadeDrive extends OperatorDrive {

	public ArcadeDrive() {
		super();
	}

	@Override
	protected void execute() {
		Robot.drive.arcadeDrive(Robot.io.getMoveValue(), Robot.io.getRotateValue());
	}

}
