package org.usfirst.frc.team4308.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class TalonSequence extends InstantCommand {

	private static final int MAX_ID = 6;
	public int currentID = -1;

	@Override
	protected void execute() {
		super.execute();

		if (currentID != -1) {
			new Talon(currentID).stopMotor();
			DriverStation.reportWarning("Stopping Motor: " + currentID, false);
		}

		currentID++;
		if (currentID > MAX_ID)
			currentID = 0;

		new Talon(currentID).set(0.65);

		DriverStation.reportWarning("Starting Motor: " + currentID, false);
	}
}
