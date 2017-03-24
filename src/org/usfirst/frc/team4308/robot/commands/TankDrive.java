package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Continuous command that takes the common Y axis of two analog sticks on a two
 * joystick controller, in order to allow individual control of the left and
 * right side motors of the {@link DriveTrain}.
 * 
 * @author Michael Brown
 *
 */
public class TankDrive extends OperatorDrive {

	public TankDrive() {
		super();
	}

	@Override
	protected void execute() {
		Robot.drive.tankDrive(Robot.io.getLeftValue(), Robot.io.getRightValue());
	}

}
