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
	
	public double magnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}
	
	public double sqrMagnitude() {
		return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
	}

}
