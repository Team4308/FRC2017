package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class RumbleControl extends Command {

	@Override
	protected void execute() {
		super.execute();

		if (Robot.io != null && Robot.io.isAvailable()) {
			Joystick joystick = Robot.io.getJoystick();

			joystick.setRumble(RumbleType.kLeftRumble, joystick.getRawAxis(RobotMap.Control.Standard.leftTrigger));
			joystick.setRumble(RumbleType.kRightRumble, joystick.getRawAxis(RobotMap.Control.Standard.rightTrigger));
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
