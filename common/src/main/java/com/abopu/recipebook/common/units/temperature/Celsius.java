package com.abopu.recipebook.common.units.temperature;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Celsius extends TemperatureUnit {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	public Celsius(int value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Celsius(long value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Celsius(float value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Celsius(double value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Celsius(BigInteger value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Celsius(BigDecimal value) {
		super(value);
	}

	/**
	 * Construct a new instance of {@link Celsius} by converting from a given {@link Fahrenheit} value.
	 */
	public Celsius(Fahrenheit fahrenheit) {
		super(fahrenheit.getValue().subtract(BigDecimal.valueOf(32)).multiply(BigDecimal.valueOf(5/9)));
	}



	/***************************************************************************
	 *
	 * Implementation
	 *
	 **************************************************************************/

	@Override
	public Celsius asCelsius() {
		return this;
	}

	@Override
	public Fahrenheit asFahrenheit() {
		return new Fahrenheit(this);
	}
}
