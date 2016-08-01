package com.abopu.recipebook.common.units;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MetricUnit extends WeightUnit {

	private long base;
	private long exponent;

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	MetricUnit(int value) {
		super(value);
	}

	MetricUnit(long value) {
		super(value);
	}

	MetricUnit(float value) {
		super(value);
	}

	MetricUnit(double value) {
		super(value);
	}

	MetricUnit(BigInteger value) {
		super(value);
	}

	MetricUnit(BigDecimal value) {
		super(value);
	}



	/***************************************************************************
	 *
	 * Implementation
	 *
	 **************************************************************************/

	@Override
	public WeightUnit asMetric(int exp) {
		return null;
	}

	@Override
	public WeightUnit asMilligrams() {
		return null;
	}

	@Override
	public WeightUnit asGrams() {
		return null;
	}

	@Override
	public WeightUnit asKilograms() {
		return null;
	}

	@Override
	public WeightUnit asOunces() {
		return null;
	}
}
