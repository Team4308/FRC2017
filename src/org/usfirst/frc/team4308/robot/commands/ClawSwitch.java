package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Instantly firing command that indiscriminately switches the state of the
 * {@link Solenoid} controlling the claw on the {@link Arm} subsystem.
 * 
 * @author Michael Brown
 *
 */
public class ClawSwitch extends InstantCommand {

	public ClawSwitch() {
		super();
		requires(Robot.arm);
	}

	@Override
	protected void execute() {
		Robot.arm.claw();
	}

}
