package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Continuous command to send the throttle axis (specified in {@link IO}) to the {@link Arm} subsystem, in order to control the pitch of the arm.
 * 
 * @author Michael Brown
 *
 */
public class ArmControl extends Command {

	public ArmControl() {
		super();
		requires(Robot.arm);
	}

	@Override
	protected void execute() {
		if (Robot.arm != null)
			Robot.arm.set(1.0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		if (Robot.arm != null)
			Robot.arm.stopMotor();
	}

}
