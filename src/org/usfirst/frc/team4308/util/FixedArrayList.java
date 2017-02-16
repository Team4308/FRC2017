package org.usfirst.frc.team4308.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Extension of the ArrayList object that will refuse to add elements that would
 * increase the size of the ArrayList beyond a limit stated when the object was
 * created
 * 
 * @author mike_
 *
 * @param <E>
 */
public class FixedArrayList<E> extends ArrayList<E> {

	private final int fixedSize;

	public FixedArrayList(int size) {
		super();
		fixedSize = size;
	}

	public FixedArrayList(int size, Collection<? extends E> arg0) {
		super(arg0);
		fixedSize = size;
	}

	@Override
	public boolean add(E arg0) {
		if (this.size() < fixedSize) {
			return super.add(arg0);
		} else {
			return false;
		}
	}

	@Override
	public void add(int arg0, E arg1) {
		if (this.size() < fixedSize && arg0 <= fixedSize) {
			super.add(arg0, arg1);
		} else {
			return;
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		if (this.size() + arg0.size() <= fixedSize) {
			return super.addAll(arg0);
		} else {
			return false;
		}
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		if (this.size() + arg1.size() <= fixedSize) {
			return super.addAll(arg0, arg1);
		} else {
			return false;
		}
	}

}
