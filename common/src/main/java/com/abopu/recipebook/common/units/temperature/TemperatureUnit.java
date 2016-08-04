package com.abopu.recipebook.common.units.temperature;

import com.abopu.recipebook.common.units.UnitOfMeasurement;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Abstract class representing a
 */
public abstract class TemperatureUnit extends UnitOfMeasurement {

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
	 * Public API
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final TemperatureUnit asMetric() {
		return asCelcius();
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
	public abstract TemperatureUnit asCelcius();

	/**
	 *
	 * @return
	 */
	public abstract TemperatureUnit asFahrenheit();
}
