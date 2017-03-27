package org.usfirst.frc.team4308.robot.commands;

import org.usfirst.frc.team4308.robot.io.IO;
import org.usfirst.frc.team4308.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Continuous command that sends the state of a (specified in {@link IO}) button which controls the motors on the {@link Climber} subsystem.
 * 
 * @author Michael Brown
 *
 */
public abstract class ToggleCommand extends InstantCommand {

	private static boolean state;

	public ToggleCommand() {
		this(false);
	}

	/**
	 * The toggle command will run toggleOff() or toggleOn() upon initialization
	 * 
	 * @param startState
	 *            whether to begin by toggling on or off
	 */
	public ToggleCommand(boolean startState) {
		super();
		state = startState;
	}

	@Override
	protected void execute() {
		state = !state;

		if (state) {
			toggleOn();
		} else {
			toggleOff();
		}
	}

	public boolean isToggled() {
		return state;
	}

	protected abstract void toggleOn();

	protected abstract void toggleOff();
}
