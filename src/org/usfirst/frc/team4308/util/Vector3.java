package org.usfirst.frc.team4308.util;

public class Vector3 extends AbstractVector {

	public double x;
	public double y;
	public double z;

	public Vector3(double... axes) {
		super(axes);
		x = axes[0];
		y = axes[1];
		z = axes[2];
	}

}
