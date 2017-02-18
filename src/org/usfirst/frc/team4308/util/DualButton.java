package org.usfirst.frc.team4308.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class DualButton extends Trigger {

	private Joystick joystick;
	private int primary;
	private int secondary;

	public DualButton(Joystick joystick, int primary, int secondary) {
		this.joystick = joystick;
		this.primary = primary;
		this.secondary = secondary;
	}

	public boolean positive() {
		return joystick.getRawButton(primary) && !joystick.getRawButton(secondary);
	}

	public boolean negative() {
		return joystick.getRawButton(secondary) && !joystick.getRawButton(primary);
	}

	public boolean neutral() {
		return !joystick.getRawButton(primary) && !joystick.getRawButton(secondary);
	}

	public int getInteger() {
		if (negative()) {
			return -1;
		} else if (positive()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean get() {
		return joystick.getRawButton(primary) && joystick.getRawButton(secondary);
	}

}
