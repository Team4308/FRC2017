package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
import org.usfirst.frc.team4308.robot.subsystems.DriveTrain;

/**
 * Continuous command that takes the common Y axis of two analog sticks on a two joystick controller, in order to allow individual control of the left and right side motors of the {@link DriveTrain}.
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
		
		Robot.drive.robotDrive.tankDrive(Robot.io.getJoystick().getRawAxis(RobotMap.Control.Standard.leftY), Robot.io.getJoystick().getRawAxis(RobotMap.Control.Standard.rightY));
		//Robot.drive.tankDrive(Robot.io.getJoystick().getRawAxis(RobotMap.Control.Standard.leftY), Robot.io.getJoystick().getRawAxis(RobotMap.Control.Standard.rightY));
	}

}
