package com.abopu.recipebook.common.units;

import com.abopu.recipebook.common.units.temperature.TemperatureUnit;
import com.abopu.recipebook.common.units.weight.WeightUnit;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Abstract class representing a quantity of a given unit of measurement.
 * Implementing classes should implement convenience methods for allowing
 * simple conversion between different units of the same class.
 * <p/>
 * Scalar values for the unit are stored as {@link BigInteger}.
 *
 * @author Sarah Skanes &lt;agent154@aboopu.com&gt;
 * @see TemperatureUnit
 * @see WeightUnit
 */
public abstract class UnitOfMeasurement {

	/***************************************************************************
	 *
	 * Local Fields
	 *
	 **************************************************************************/

	/**
	 * Scalar value for this {@link UnitOfMeasurement}
	 */
	private BigDecimal value;



	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/


	/**
	 * Constructs a {@link UnitOfMeasurement} using an {@link Integer} value.
	 */
	protected UnitOfMeasurement(int value) { this.value = BigDecimal.valueOf(value); }

	/**
	 * Constructs a {@link UnitOfMeasurement} using a {@link Long} value.
	 */
	protected UnitOfMeasurement(long value) { this.value = BigDecimal.valueOf(value); }

	/**
	 * Constructs a {@link UnitOfMeasurement} using a {@link Float} value.
	 */
	protected UnitOfMeasurement(float value) { this.value = BigDecimal.valueOf(value); }

	/**
	 * Constructs a {@link UnitOfMeasurement} using a {@link Double} value.
	 */
	protected UnitOfMeasurement(double value) { this.value = BigDecimal.valueOf(value); }

	/**
	 * Constructs a {@link UnitOfMeasurement} using aa {@link BigInteger} value.
	 */
	protected UnitOfMeasurement(BigInteger value) { this.value = new BigDecimal(value); }

	/**
	 * Constructs a {@link UnitOfMeasurement} using a {@likn BigDecimal} value.
	 */
	protected UnitOfMeasurement(BigDecimal value) { this.value = value; }



	/***************************************************************************
	 *
	 * Public API
	 *
	 **************************************************************************/

	/**
	 * Return this {@link UnitOfMeasurement} in metric form.
	 */
	public abstract UnitOfMeasurement asMetric();

	/**
	 * Return this {@link UnitOfMeasurement} in imperial form.
	 */
	public abstract UnitOfMeasurement asImperial();

	/**
	 * Get the scalar value for this {@link UnitOfMeasurement}
	 */
	public BigDecimal getValue() { return value; }
}
