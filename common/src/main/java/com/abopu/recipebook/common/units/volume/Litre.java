package com.abopu.recipebook.common.units.volume;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class Litre extends VolumeUnit {
	
	/***************************************************************************
	 *
	 * Static Members
	 *
	 **************************************************************************/
	
	public static Litre from(VolumeUnit unit) {
		return (Litre) unit.asLitres();
	}
	
	

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	Litre(int value) {
		super(value);
	}

	Litre(long value) {
		super(value);
	}

	Litre(float value) {
		super(value);
	}

	Litre(double value) {
		super(value);
	}

	Litre(BigInteger value) {
		super(value);
	}

	Litre(BigDecimal value) {
		super(value);
	}
	
	
	
	/***************************************************************************
	 *
	 * Implementation
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VolumeUnit asLitres() {
		return this;
	}

	@Override
	public VolumeUnit asTeaspoons() {
		return null;
	}

	@Override
	public VolumeUnit asTablespoons() {
		return null;
	}

	@Override
	public VolumeUnit asOunces() {
		return null;
	}

	@Override
	public VolumeUnit asPints() {
		return null;
	}

	@Override
	public VolumeUnit asQuarts() {
		return null;
	}

	@Override
	public VolumeUnit asGallons() {
		return null;
	}
}
