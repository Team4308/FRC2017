package org.usfirst.frc.team4308.util;

public class Vector2 extends AbstractVector {

	public double x;
	public double y;

	public Vector2(double... axes) {
		super(axes);
		x = axes[0];
		y = axes[1];
	}

}
