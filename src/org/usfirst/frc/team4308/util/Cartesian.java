package org.usfirst.frc.team4308.util;

public class Cartesian {

	public static Cartesian sum(Cartesian operator, Cartesian operand) {
		return new Cartesian(operator.x + operand.x, operator.y + operand.y);
	}

	public static Cartesian sub(Cartesian operator, Cartesian operand) {
		return new Cartesian(operator.x - operand.x, operator.y - operand.y);
	}

	public static Cartesian mult(Cartesian operator, Cartesian operand) {
		return new Cartesian(operator.x * operand.x, operator.y * operand.y);
	}

	public static Cartesian div(Cartesian operator, Cartesian operand) {
		return new Cartesian(operator.x / operand.x, operator.y / operand.y);
	}

	private double x;
	private double y;

	public Cartesian(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double x() {
		return x;
	}

	public double x(double x) {
		return this.x = x;
	}

	public double y() {
		return y;
	}

	public double y(double y) {
		return this.y = y;
	}

	public double sqrMagnitude() {
		return Math.pow(x, 2) + Math.pow(y, 2);
	}

	public double magnitude() {
		return Math.sqrt(sqrMagnitude());
	}

	public void inverse() {
		x = -x;
		y = -y;
	}

	public Polar toPolar() {
		return new Polar(magnitude(), Math.acos(x / magnitude()));
	}

	public Cartesian sum(Cartesian operand) {
		return new Cartesian(this.x + operand.x, this.y + operand.y);
	}

	public Cartesian sub(Cartesian operand) {
		return new Cartesian(this.x - operand.x, this.y - operand.y);
	}

	public Cartesian mult(Cartesian operand) {
		return new Cartesian(this.x * operand.x, this.y * operand.y);
	}

	public Cartesian div(Cartesian operand) {
		return new Cartesian(this.x / operand.x, this.y / operand.y);
	}

}
