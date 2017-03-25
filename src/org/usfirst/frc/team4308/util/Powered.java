package org.usfirst.frc.team4308.util;

public interface Powered {

	public default double voltage() {
		return Double.NaN;
	}

	public default double current() {
		return Double.NaN;
	}

	public default double temperature(){
		return Double.NaN;
	}

}
