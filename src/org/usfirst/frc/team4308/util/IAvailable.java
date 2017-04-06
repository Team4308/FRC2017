package org.usfirst.frc.team4308.util;

/**
 * Allows a anything to determine if its components are usable, regardless of
 * the object existing or not.
 * 
 * @author Samson Close
 *
 */
public interface IAvailable {

	/**
	 * @return Whether or not the components or devices that this class controls
	 *         are ready to be, or can be used.
	 */
	public abstract boolean isAvailable();
}
