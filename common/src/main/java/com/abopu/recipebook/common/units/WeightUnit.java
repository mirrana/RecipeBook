package com.abopu.recipebook.common.units;

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
	 * Abstract Methods
	 *
	 **************************************************************************/

	/**
	 * Converts this unit to a metric representation.
	 * <p>
	 * This method expects an integer value for {@code x} in the
	 * following equation:
	 * <p/>
	 * {@code}asGrams() * 10^x
	 * <p/>
	 * A value of {@code 0} is equivalent to calling {@link #asGrams()}.
	 *
	 * @param exp
	 * @return a
	 */
	public abstract WeightUnit asMetric(int exp);

	public abstract WeightUnit asMilligrams();

	public abstract WeightUnit asGrams();

	public abstract WeightUnit asKilograms();

	public abstract WeightUnit asOunces();
}
