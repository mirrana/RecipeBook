package com.abopu.recipebook.common.units.temperature;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Celcius extends TemperatureUnit {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	public Celcius(int value) {
		super(value);
	}

	public Celcius(long value) {
		super(value);
	}

	public Celcius(float value) {
		super(value);
	}

	public Celcius(double value) {
		super(value);
	}

	public Celcius(BigInteger value) {
		super(value);
	}

	public Celcius(BigDecimal value) {
		super(value);
	}



	/***************************************************************************
	 *
	 * Implementation
	 *
	 **************************************************************************/

	@Override
	public TemperatureUnit asCelcius() {
		return this;
	}

	@Override
	public TemperatureUnit asFahrenheit() {
		BigDecimal celcius = getValue();
		return new Fahrenheit(celcius.multiply(BigDecimal.valueOf(9/5)).add(BigDecimal.valueOf(32)));
	}
}
