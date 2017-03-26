package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4308.robot.subsystems.ValueChangeRegulator;

/**
 * Continuous command that takes the common Y axis of two analog sticks on a two joystick controller, in order to allow individual control of the left and right side motors of the {@link DriveTrain}.
 * 
 * @author Michael Brown
 *
 */
public class TankDrive extends OperatorDrive {

	private static final boolean TEST_REGULATOR = true;
	private ValueChangeRegulator regulator;

	public TankDrive() {
		super();

		regulator = new ValueChangeRegulator(0.05);
	}

	@Override
	protected void execute() {
		double left = Robot.io.getJoystick().getRawAxis(RobotMap.Control.Standard.leftY);
		double right = Robot.io.getJoystick().getRawAxis(RobotMap.Control.Standard.rightY);

		if (TEST_REGULATOR) {
			left = regulator.filter(left);
			right = regulator.filter(right);
		}

		Robot.drive.robotDrive.tankDrive(left, right);
		// Robot.drive.tankDrive(Robot.io.getJoystick().getRawAxis(RobotMap.Control.Standard.leftY), Robot.io.getJoystick().getRawAxis(RobotMap.Control.Standard.rightY));
	}

}
