package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.RobotMap;
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
public class ArmControlDown extends Command {

	public ArmControlDown() {
		super();
		requires(Robot.arm);
	}

	@Override
	protected boolean isFinished() {
		return Robot.arm.isArmDown();
	}

	@Override
	protected void execute() {
		super.execute();
		Robot.arm.set(RobotMap.GearArm.speedDown);
	}

	@Override
	protected void end() {

	}
}
