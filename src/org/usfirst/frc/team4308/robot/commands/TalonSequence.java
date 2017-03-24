package org.usfirst.frc.team4308.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TalonSequence extends CommandGroup {

	public TalonSequence() {
		for(int i = 0; i < 8 ;i++){
			addSequential(new TalonTest(i));
		}
		
	}

}
