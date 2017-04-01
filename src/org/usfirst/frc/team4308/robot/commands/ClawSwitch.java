package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.Robot;
import org.usfirst.frc.team4308.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Instantly firing command that indiscriminately switches the state of the {@link Solenoid} controlling the claw on the {@link Arm} subsystem.
 * 
 * @author Michael Brown
 *
 */
public class ClawSwitch extends InstantCommand {

	private boolean toggle;
	private boolean open;

	/**
	 * Toggles the claw on and off
	 */
	public ClawSwitch() {
		super();
		requires(Robot.arm);
		toggle = true;
		open = false;
	}

	/**
	 * Opens or closes the claw
	 * 
	 * @param open
	 *            if this command should set the claw to be open, else it will be closed
	 */
	public ClawSwitch(boolean open) {
		super();
		requires(Robot.arm);

		toggle = false;
		this.open = open;
	}

	@Override
	protected void execute() {
			if (toggle) {
				Robot.arm.claw();
			} else {
				Robot.arm.claw(open);
			}
	}

}
