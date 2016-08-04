package com.abopu.recipebook.common.units.weight;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Gram extends WeightUnit {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	public Gram(int value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Gram(long value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Gram(float value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Gram(double value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Gram(BigInteger value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Gram(BigDecimal value) {
		super(value);
	}



	/***************************************************************************
	 *
	 * Implementation
	 *
	 **************************************************************************/

	@Override
	public WeightUnit asMilligrams() {
		return null;
	}

	@Override
	public WeightUnit asGrams() {
		return this;
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
