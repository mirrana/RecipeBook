package com.abopu.recipebook.common.units.temperature;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Fahrenheit extends TemperatureUnit {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	public Fahrenheit(int value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Fahrenheit(long value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Fahrenheit(float value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Fahrenheit(double value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Fahrenheit(BigInteger value) {
		super(value);
	}

	/**
	 * {@inheritDoc}
	 */
	public Fahrenheit(BigDecimal value) {
		super(value);
	}

	/**
	 * Construct a new instance of {@link Fahrenheit} by converting from a given {@link Celsius} value.
	 */
	public Fahrenheit(Celsius celsius) {
		super(celsius.getValue().multiply(BigDecimal.valueOf(9/5)).add(BigDecimal.valueOf(32)));
	}

	
	
	/***************************************************************************
	 *
	 * Implementation
	 *
	 **************************************************************************/

	@Override
	public Celsius asCelsius() {
		return new Celsius(this);
	}

	@Override
	public Fahrenheit asFahrenheit() {
		return this;
	}
}
