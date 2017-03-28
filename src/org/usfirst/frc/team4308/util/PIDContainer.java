package org.usfirst.frc.team4308.util;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDContainer implements PIDSource, PIDOutput {

	private double value;
	private PIDSourceType type;

	public PIDContainer(PIDSourceType type) {
		this.type = type;
		value = 0;
	}

	@Override
	public void pidWrite(double output) {
		value = output;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		type = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return type;
	}

	@Override
	public double pidGet() {
		return value;
	}

}
