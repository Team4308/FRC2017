package org.usfirst.frc.team4308.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;

public class TankDrive extends Drive {

	public TankDrive(SpeedController...controllers) {
		super(controllers);
	}

	public TankDrive(String name, SpeedController...controllers) {
		super(name, controllers);
		
	}

}
