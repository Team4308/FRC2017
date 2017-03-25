package org.usfirst.frc.team4308.util;

public class Vector2 extends AbstractVector {

	public double x;
	public double y;

	public Vector2(double... axes) {
		super(axes);
		x = axes[0];
		y = axes[1];
	}

	public Vector2 toCartesian() {
		double x = Math.cos(this.y) * this.x;
		double y = Math.sin(this.y) * this.x;
		return new Vector2(x, y);
	}

	public Vector2 toPolar() {
		double radius = Math.sqrt(x * x + y * y);
		double angle = Math.acos(this.x / radius);
		return new Vector2(radius, angle);
	}

}
