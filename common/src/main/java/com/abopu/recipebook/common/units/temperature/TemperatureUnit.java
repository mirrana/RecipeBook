//package com.abopu.recipebook.common.units.temperature;
//
//import com.abopu.recipebook.common.units.UnitOfMeasurement;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//
///**
// * Abstract class representing a measurement of temperature.
// */
//public abstract class TemperatureUnit extends UnitOfMeasurement {
//
//	/***************************************************************************
//	 *
//	 * Constructors
//	 *
//	 **************************************************************************/
//
//	/**
//	 * {@inheritDoc}
//	 */
//	TemperatureUnit(int value) {
//		super(value);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	TemperatureUnit(long value) {
//		super(value);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	TemperatureUnit(float value) {
//		super(value);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	TemperatureUnit(double value) {
//		super(value);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	TemperatureUnit(BigInteger value) {
//		super(value);
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	TemperatureUnit(BigDecimal value) {
//		super(value);
//	}
//
//
//
//	/***************************************************************************
//	 *
//1	 * Public API
//	 *
//	 **************************************************************************/
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public final TemperatureUnit asMetric() {
//		return asCelsius();
//	}
//
//	@Override
//	public TemperatureUnit asImperial() {
//		return asFahrenheit();
//	}
//
//
//
//	/***************************************************************************
//	 *
//	 * Abstract Methods
//	 *
//	 **************************************************************************/
//
//	/**
//	 *
//	 * @return
//	 */
//	public abstract TemperatureUnit asCelsius();
//
//	/**
//	 *
//	 * @return
//	 */
//	public abstract TemperatureUnit asFahrenheit();
//}
