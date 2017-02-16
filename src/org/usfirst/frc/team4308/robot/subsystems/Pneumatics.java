package org.usfirst.frc.team4308.robot.subsystems;

import org.usfirst.frc.team4308.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {
	
	private AnalogInput pressureSensor;
	private Compressor compressor;

	public Pneumatics() {
		super();
		compressor = new Compressor();
		pressureSensor = new AnalogInput(RobotMap.pressureSensorChannel);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
