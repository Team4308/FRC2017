package org.usfirst.frc.team4308.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Extension of the ArrayList object that will remove the first element(s) of the array in order to make room for new elements
 * 
 * @author mike_
 *
 * @param <E>
 */
public class OverflowingArrayList<E> extends ArrayList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 967926892949969569L;
	private final int fixedSize;

	public OverflowingArrayList(int size) {
		super();
		fixedSize = size;
	}

	public OverflowingArrayList(int size, Collection<? extends E> arg0) {
		super(arg0);
		fixedSize = size;
	}

	@Override
	public boolean add(E arg0) {
		if (this.size() < fixedSize) {
			return super.add(arg0);
		} else {
			this.remove(0);
			return super.add(arg0);
		}
	}

	@Override
	public void add(int arg0, E arg1) {
		if (this.size() < fixedSize && arg0 <= fixedSize) {
			super.add(arg0, arg1);
		} else {
			this.remove(0);
			super.add(arg0, arg1);
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		if (this.size() + arg0.size() <= fixedSize) {
			return super.addAll(arg0);
		} else {
			for (int i = 0; i < arg0.size(); i++) {
				this.remove(0);
			}
			return super.addAll(arg0);
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
