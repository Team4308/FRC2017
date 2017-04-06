package org.usfirst.frc.team4308.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Joystick-oriented button that reads the output of two buttons and combines
 * their outputs
 * 
 * @author Michael Brown
 *
 */
public class DualButton extends Trigger {

	private Joystick joystick;
	private int primary;
	private int secondary;

	/**
	 * Registers two buttons on an existing joystick. Buttons are assigned by
	 * their channel number.
	 * 
	 * @param joystick
	 *            The joystick containing the buttons.
	 * @param primary
	 *            The integer ID for the primary or first button.
	 * @param secondary
	 *            The integer ID for the secondary or second button.
	 */
	public DualButton(Joystick joystick, int primary, int secondary) {
		this.joystick = joystick;
		this.primary = primary;
		this.secondary = secondary;
	}

	/**
	 * @return Whether the button assigned as primary is pushed down, and the
	 *         secondary button is not.
	 */
	public boolean positive() {
		return joystick.getRawButton(primary) && !joystick.getRawButton(secondary);
	}

	/**
	 * 
	 * @return If the button assigned at secondary is pushed down, and the
	 *         primary is not.
	 */
	public boolean negative() {
		return joystick.getRawButton(secondary) && !joystick.getRawButton(primary);
	}

	/**
	 * 
	 * @return If both buttons are not pressed.
	 */
	public boolean neutral() {
		return !joystick.getRawButton(primary) && !joystick.getRawButton(secondary);
	}

	/**
	 * Converting the 4 boolean states of the two buttons into a combination of
	 * integers.
	 * 
	 * @return 1 for primary on, -1 for primary off, and 0 for others.
	 */
	public int getInteger() {
		if (negative()) {
			return -1;
		} else if (positive()) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Standard button check if both buttons are pressed.
	 */
	@Override
	public boolean get() {
		return joystick.getRawButton(primary) && joystick.getRawButton(secondary);
	}

}
