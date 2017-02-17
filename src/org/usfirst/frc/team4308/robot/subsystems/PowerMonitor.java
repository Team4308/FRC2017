package org.usfirst.frc.team4308.robot.subsystems;

import java.util.HashMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PowerMonitor extends Subsystem {

	private HashMap<String, CANTalon> motors;

	public PowerMonitor() {
		super();
		motors = new HashMap<String, CANTalon>();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void add(String name, CANTalon motor) {
		motors.put(name, motor);
	}

	public double motorVoltage(String name) {
		return motors.get(name).getOutputVoltage();
	}

	public double motorCurrent(String name) {
		return motors.get(name).getOutputCurrent();
	}

}
