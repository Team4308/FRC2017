package org.usfirst.frc.team4308.util;

/**
 * Regulates the change of an input, typically from a joystick.
 * 
 * @author Samson Close
 *
 */
public class ValueChangeRegulator {

	private double maxChange;
	private double currentFilteredValue;

	public ValueChangeRegulator(double maxChangePerFilter) {
		this.maxChange = maxChangePerFilter;
		currentFilteredValue = 0;
	}

	public double filter(double desiredValue) {
		double desiredDelta = desiredValue - currentFilteredValue;

		// If going up faster than the maxChange
		if (desiredDelta > maxChange) {
			// If it will overshoot the maxChange for accelerating up
			currentFilteredValue += maxChange;
		} // If going down faster than the maxChange
		else if (desiredDelta < -maxChange) {
			currentFilteredValue -= maxChange;
		}

		// DriverStation.reportWarning("DesiredDelta: " + desiredDelta, false);
		// DriverStation.reportWarning("CurrentDelta: " + (desiredValue -
		// currentFilteredValue), false);

		return currentFilteredValue;
	}
}
