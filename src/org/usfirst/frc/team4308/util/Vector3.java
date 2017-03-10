package org.usfirst.frc.team4308.util;

public class Vector3 {

	public double x;
	public double y;
	public double z;

	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3 add(Vector3 vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return new Vector3(x, y, z);
	}

	public Vector3 sub(Vector3 vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
		return new Vector3(x, y, z);
	}

	public Vector3 mult(Vector3 vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return new Vector3(x, y, z);
	}

	public Vector3 div(Vector3 vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
		return new Vector3(x, y, z);
	}

	public void inverse() {
		x = -x;
		y = -y;
		z = -z;
	}

	public double magnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}

	public double sqrMagnitude() {
		return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
	}

}
