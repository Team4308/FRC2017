package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;

/**
 * Continuous command to send input from a joystick (specified in {@link IO}) to the motors of the {@link DriveTrain}, controlling its movement.
 * 
 * @author Michael Brown
 *
 */
public class ArcadeDrive extends OperatorDrive {

	private int xAxisID;
	private int yAxisID;

	public ArcadeDrive(int xAxisID, int yAxisID) {
		super();
		requires(Robot.drive);

		this.xAxisID = xAxisID;
		this.yAxisID = yAxisID;
	}

	@Override
	protected void execute() {
		Robot.drive.arcadeDrive(Robot.io.getJoystick().getRawAxis(xAxisID), Robot.io.getJoystick().getRawAxis(yAxisID));
	}

}
