/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Sarah Skanes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.abopu.recipebook.common.units.v2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;

/**
 * 
 */
public abstract class UnitOfMeasurement {

	/***************************************************************************
	 *
	 * Local Fields
	 *
	 **************************************************************************/

	/**
	 * Scalar scalar for this {@link UnitOfMeasurement}
	 */
	private BigDecimal scalar;
	
	private Unit unit;
	
	

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	/**
	 * Constructs a {@link UnitOfMeasurement} using an {@link Integer} scalar.
	 */
	UnitOfMeasurement(int scalar) {
		this.scalar = BigDecimal.valueOf(scalar);
	}

	/**
	 * Constructs a {@link UnitOfMeasurement} using a {@link Long} scalar.
	 */
	UnitOfMeasurement(long scalar) {
		this.scalar = BigDecimal.valueOf(scalar);
	}

	/**
	 * Constructs a {@link UnitOfMeasurement} using a {@link Float} scalar.
	 */
	UnitOfMeasurement(float scalar) {
		this.scalar = BigDecimal.valueOf(scalar);
	}

	/**
	 * Constructs a {@link UnitOfMeasurement} using a {@link Double} scalar.
	 */
	UnitOfMeasurement(double scalar) {
		this.scalar = BigDecimal.valueOf(scalar);
	}

	/**
	 * Constructs a {@link UnitOfMeasurement} using aa {@link BigInteger} scalar.
	 */
	UnitOfMeasurement(BigInteger scalar) {
		this.scalar = new BigDecimal(scalar);
	}

	/**
	 * Constructs a {@link UnitOfMeasurement} using a {@link BigDecimal} scalar.
	 */
	UnitOfMeasurement(BigDecimal scalar) {
		this.scalar = scalar;
	}
	
	

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
	 * Get the scalar scalar for this {@link UnitOfMeasurement}
	 */
	public BigDecimal getScalar() { return scalar; }



	/***************************************************************************
	 *
	 * Classes
	 *
	 **************************************************************************/
	
	interface Unit {
		String getAbbreviation();
		BigDecimal metricMultiplier();
	}
}
