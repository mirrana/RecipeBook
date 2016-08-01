package com.abopu.recipebook.common.units;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Grams extends WeightUnit {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	public Grams(int value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Grams(long value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Grams(float value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Grams(double value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Grams(BigInteger value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Grams(BigDecimal value) {
		super(value);
	}



	/***************************************************************************
	 *
	 * Implementation
	 *
	 **************************************************************************/

	@Override
	public WeightUnit asMetric(int exp) {
		return new MetricUnit(value.multiply(BigDecimal.valueOf(10 ^ exp)));
	}

	@Override
	public WeightUnit asMilligrams() {
		return asMetric(-3);
	}

	@Override
	public WeightUnit asGrams() {
		return this;
	}

	@Override
	public WeightUnit asKilograms() {
		return asMetric(3);
	}

	@Override
	public WeightUnit asOunces() {
		return null;
	}
}
