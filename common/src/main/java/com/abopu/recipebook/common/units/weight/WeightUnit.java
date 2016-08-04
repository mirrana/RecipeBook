package com.abopu.recipebook.common.units.weight;

import com.abopu.recipebook.common.units.UnitOfMeasurement;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class WeightUnit extends UnitOfMeasurement {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	WeightUnit(int value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	WeightUnit(long value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	WeightUnit(float value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	WeightUnit(double value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	WeightUnit(BigInteger value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	WeightUnit(BigDecimal value) {
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
	public final WeightUnit asMetric() {
		return asGrams();
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

	public abstract WeightUnit asMilligrams();

	public abstract WeightUnit asGrams();

	public abstract WeightUnit asKilograms();

	public abstract WeightUnit asOunces();
}
