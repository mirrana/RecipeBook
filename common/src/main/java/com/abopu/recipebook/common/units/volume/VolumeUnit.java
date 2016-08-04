package com.abopu.recipebook.common.units.volume;

import com.abopu.recipebook.common.units.UnitOfMeasurement;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class VolumeUnit extends UnitOfMeasurement {

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

	@Override
	public UnitOfMeasurement asImperial() {
		return null;
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

}
