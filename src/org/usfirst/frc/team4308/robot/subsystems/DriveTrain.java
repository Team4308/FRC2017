package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.commands.JoystickControl;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	protected SpeedController leftMaster;
	protected SpeedController leftSlave;
	protected SpeedController rightMaster;
	protected SpeedController rightSlave;

	public DriveTrain() {
		// TODO Auto-generated constructor stub
	}

	public DriveTrain(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickControl());
	}

}
