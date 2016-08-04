package com.abopu.recipebook.common.units.temperature;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Fahrenheit extends TemperatureUnit {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	public Fahrenheit(int value) {
		super(value);
	}

	public Fahrenheit(long value) {
		super(value);
	}

	public Fahrenheit(float value) {
		super(value);
	}

	public Fahrenheit(double value) {
		super(value);
	}

	public Fahrenheit(BigInteger value) {
		super(value);
	}

	public Fahrenheit(BigDecimal value) {
		super(value);
	}



	/***************************************************************************
	 *
	 * Implementation
	 *
	 **************************************************************************/

	@Override
	public TemperatureUnit asCelcius() {
		BigDecimal celcius = getValue();
		return new Fahrenheit(celcius.subtract(BigDecimal.valueOf(32)).multiply(BigDecimal.valueOf(5/9)));
	}

	@Override
	public TemperatureUnit asFahrenheit() {
		return this;
	}
}
