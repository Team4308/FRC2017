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

}
