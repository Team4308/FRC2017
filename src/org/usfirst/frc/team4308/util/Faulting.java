package org.usfirst.frc.team4308.util;

public interface Faulting {

	public boolean[] getFaults();

	public boolean[] getStickyFaults();

	public void clearStickyFaults();

}
