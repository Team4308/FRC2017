package org.usfirst.frc.team4308.util;

public class Polar {

	private double radius;
	private double angle;

	public Polar(double radius, double angle) {
		this.radius = radius;
		this.angle = angle;
	}

	public double radius() {
		return radius;
	}

	public double radius(double radius) {
		return this.radius = radius;
	}

	public double angle() {
		return angle;
	}

	public double angle(double angle) {
		return this.angle = angle;
	}

	public void inverse() {
		if (angle > 0) {
			angle -= 180;
		} else if (angle < 0) {
			angle += 180;
		}
	}

	public void simpleAngle() {
		double rot = angle / 360;
		for (int i = 1; i < rot; i++) {
			angle -= 360;
		}
	}

	public Cartesian toCartesian() {
		return new Cartesian(Math.cos(angle) * radius, Math.sin(angle) * radius);
	}

}
