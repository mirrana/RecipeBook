package com.abopu.recipebook.common.units;

import java.math.BigDecimal;
import java.math.BigInteger;

abstract class TemperatureUnit extends UnitOfMeasurement {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	TemperatureUnit(int value) {
		super(value);
	}

	TemperatureUnit(long value) {
		super(value);
	}

	TemperatureUnit(float value) {
		super(value);
	}

	TemperatureUnit(double value) {
		super(value);
	}

	TemperatureUnit(BigInteger value) {
		super(value);
	}

	TemperatureUnit(BigDecimal value) {
		super(value);
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
	public abstract TemperatureUnit asCelcius();

	/**
	 *
	 * @return
	 */
	public abstract TemperatureUnit asFahrenheit();
}
