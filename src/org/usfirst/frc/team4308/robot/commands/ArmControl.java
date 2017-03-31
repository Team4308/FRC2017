package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Continuous command to send the throttle axis (specified in {@link IO}) to the
 * {@link Arm} subsystem, in order to control the pitch of the arm.
 * 
 * @author Michael Brown
 *
 */
public class ArmControl extends Command {

	private boolean direction;

	public ArmControl(boolean direction) {
		super();
		requires(Robot.arm);
		this.direction = direction;

	}

	@Override
	protected void execute() {
		Robot.arm.set(direction ? -0.75 : 0.75);
	}

	@Override
	protected boolean isFinished() {
		return Robot.arm.isArmUp();

	}

	@Override
	protected void end() {
		Robot.arm.stopMotor();
	}

}
