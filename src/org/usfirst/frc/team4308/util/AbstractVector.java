package org.usfirst.frc.team4308.util;

public abstract class AbstractVector {

	protected double[] dimensions;

	public AbstractVector(double... axes) {
		dimensions = axes;
	}

	public double sqrMagnitude() {
		double sum = 0;
		for (double d : dimensions) {
			sum += Math.pow(d, 2);
		}
		return sum;
	}

	public double magnitude() {
		return Math.sqrt(sqrMagnitude());
	}

	public AbstractVector inverse() {
		for (double d : dimensions) {
			d = -d;
		}
		return this;
	}

	public AbstractVector sum(AbstractVector operand) {
		for (int i = 0; i < dimensions.length; i++) {
			dimensions[i] += operand.dimensions[i];
		}
		return this;
	}

	public AbstractVector sub(AbstractVector operand) {
		for (int i = 0; i < dimensions.length; i++) {
			dimensions[i] -= operand.dimensions[i];
		}
		return this;
	}

	public AbstractVector mult(AbstractVector operand) {
		for (int i = 0; i < dimensions.length; i++) {
			dimensions[i] *= operand.dimensions[i];
		}
		return this;
	}

	public AbstractVector div(AbstractVector operand) {
		for (int i = 0; i < dimensions.length; i++) {
			dimensions[i] /= operand.dimensions[i];
		}
		return this;
	}

}
