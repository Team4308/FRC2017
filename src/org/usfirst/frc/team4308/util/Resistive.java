package org.usfirst.frc.team4308.util;

import com.ctre.CANTalon;

public interface Resistive extends Powered {

	/**
	 * Returns a ratio that represents the current power consumption compared to
	 * how much power is being supplied. In the case of a ratio of 1, all
	 * supplied power is used, else there is resistance detected in the motors
	 * (all cases when the motors are driving an object).
	 * 
	 * @param talon
	 *            The CANTalon that will be measured.
	 * @return The ratio of power supply to power use.
	 */
	public default double outputRatio(CANTalon talon) {
		return talon.getOutputVoltage() / talon.getBusVoltage();
	}
}
