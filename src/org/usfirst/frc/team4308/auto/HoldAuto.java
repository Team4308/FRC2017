package org.usfirst.frc.team4308.auto;

import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HoldAuto extends CommandGroup {

	public HoldAuto() {
		super();
		// TODO? addParallel(new WaitForPressure());
		addParallel(new WaitCommand(RobotMap.kAutonomousTime));
	}

}
