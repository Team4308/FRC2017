package org.usfirst.frc.team4308.robot.io;

import edu.wpi.first.wpilibj.Joystick;

public enum JoystickType {
	/** A classic stick with buttons on it */
	FLIGHT(3),
	/** A standard console controller (like XBox or PlayStation) */
	STANDARD(6);

	private int axisCount;

	JoystickType(int axisCount) {
		this.axisCount = axisCount;
	}

	public int getAxisCount() {
		return axisCount;
	}

	public static JoystickType fromJoystick(Joystick joystick) {
		for (JoystickType t : values()) {
			if (t.getAxisCount() == joystick.getAxisCount())
				return t;
		}
		return null;
	}
}
