package org.usfirst.frc.team4308.util;

import edu.wpi.first.wpilibj.buttons.Button;

public class DualButton extends Button {

	private Button primary;
	private Button secondary;

	public DualButton(Button primary, Button secondary) {
		this.primary = primary;
		this.secondary = secondary;
	}

	public boolean positve() {
		return primary.get() && !secondary.get();
	}

	public boolean negative() {
		return secondary.get() && !primary.get();
	}

	public boolean neutral() {
		return !primary.get() && !secondary.get();
	}

	public int getInteger() {
		if (!primary.get() && secondary.get()) {
			return -1;
		} else if (!secondary.get() && primary.get()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean get() {
		return primary.get() && secondary.get();
	}

}
