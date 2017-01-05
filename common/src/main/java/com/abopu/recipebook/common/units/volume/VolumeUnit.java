package com.abopu.recipebook.common.units.volume;

import com.abopu.recipebook.common.units.UnitOfMeasurement;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class VolumeUnit extends UnitOfMeasurement {

	/***************************************************************************
	 *
	 * Static Members
	 *
	 **************************************************************************/

	public static VolumeUnit convert(VolumeUnit from, Class<? extends VolumeUnit> to) {
		if (from == null) throw new NullPointerException("Parameter 'from' is null.");
		if (to == null) throw new NullPointerException("Parameter 'to' is null.");
		if (to == VolumeUnit.class) throw new IllegalArgumentException("Must supply a concrete implementation of Volume");
		
		if (to == Litre.class) {
			return from.asLitres();
		}

		throw new IllegalArgumentException("Unsupported unit type: " + to.getSimpleName());
	}
	
	
	
	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	VolumeUnit(int value) {
		super(value);
	}

	VolumeUnit(long value) {
		super(value);
	}

	VolumeUnit(float value) {
		super(value);
	}

	VolumeUnit(double value) {
		super(value);
	}

	VolumeUnit(BigInteger value) {
		super(value);
	}

	VolumeUnit(BigDecimal value) {
		super(value);
	}
	
	

	/***************************************************************************
	 *
	 * Public API
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final VolumeUnit asMetric() {
		return asLitres();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final VolumeUnit asImperial() {
		return asQuarts();
	}



	/***************************************************************************
	 *
	 * Abstract Methods
	 *
	 **************************************************************************/
	
	/**
	 *
	 * @return
	 */
	public abstract VolumeUnit asLitres();

	/**
	 *
	 * @return
	 */
	public abstract VolumeUnit asTeaspoons();

	/**
	 *
	 * @return
	 */
	public abstract VolumeUnit asTablespoons();

	/**
	 *
	 * @return
	 */
	public abstract VolumeUnit asOunces();

	/**
	 *
	 * @return
	 */
	public abstract VolumeUnit asPints();

	/**
	 *
	 * @return
	 */
	public abstract VolumeUnit asQuarts();

	/**
	 *
	 * @return
	 */
	public abstract VolumeUnit asGallons();
}
