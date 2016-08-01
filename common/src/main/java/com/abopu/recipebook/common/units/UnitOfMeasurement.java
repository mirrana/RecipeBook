package com.abopu.recipebook.common.units;

import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class UnitOfMeasurement {

	protected BigDecimal value;

	public UnitOfMeasurement(int value) { this.value = BigDecimal.valueOf(value); }

	public UnitOfMeasurement(long value) { this.value = BigDecimal.valueOf(value); }

	public UnitOfMeasurement(float value) { this.value = BigDecimal.valueOf(value); }

	public UnitOfMeasurement(double value) { this.value = BigDecimal.valueOf(value); }

	public UnitOfMeasurement(BigInteger value) { this.value = new BigDecimal(value); }

	public UnitOfMeasurement(BigDecimal value) { this.value = value; }

	BigDecimal getValue() { return value; }
}
