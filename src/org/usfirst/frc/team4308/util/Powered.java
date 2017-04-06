package org.usfirst.frc.team4308.util;

/**
 * Allows a subsystem or component to output power consumption based values, for
 * logging and safety management. Default values output non-numbers to indicate
 * a lack of a proper sensor.
 * 
 * @author Michael Brown
 *
 */
public interface Powered {

	public default double voltage() {
		return Double.NaN;
	}

	public default double current() {
		return Double.NaN;
	}

	public default double temperature() {
		return Double.NaN;
	}

}
