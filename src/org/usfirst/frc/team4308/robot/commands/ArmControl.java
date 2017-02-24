package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Continuous command to send the throttle axis (specified in {@link OI}) to the
 * {@link Arm} subsystem, in order to control the pitch of the arm.
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
		Robot.arm.setAngle(Robot.oi.getArmValue());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.arm.reset();
	}

}