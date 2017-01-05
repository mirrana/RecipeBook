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

public class Volume extends UnitOfMeasurement {

	public Volume(int value, Unit unit) {
		this(BigDecimal.valueOf(value), unit);
	}

	public Volume(long value, Unit unit) {
		this(BigDecimal.valueOf(value), unit);
	}

	public Volume(float value, Unit unit) {
		this(BigDecimal.valueOf(value), unit);
	}

	public Volume(double value, Unit unit) {
		this(BigDecimal.valueOf(value), unit);
	}

	public Volume(BigInteger value, Unit unit) {
		this(new BigDecimal(value), unit);
	}

	public Volume(BigDecimal value, Unit unit) {
		super(inLitres(value, unit));
	}
	
	
	
	/***************************************************************************
	 *
	 * Public API
	 *
	 **************************************************************************/
	
	@Override
	public UnitOfMeasurement asMetric() {
		return null;
	}

	@Override
	public UnitOfMeasurement asImperial() {
		return null;
	}



	/***************************************************************************
	 *
	 * Private API
	 *
	 **************************************************************************/
	
	private static BigDecimal inLitres(BigDecimal value, Unit unit) {
		return value.multiply(unit.metricMultiplier());
	}



	/***************************************************************************
	 *
	 * Units
	 *
	 **************************************************************************/
	
	public enum Unit implements UnitOfMeasurement.Unit {
		// Metric Units
		MILLILITRE("ml", new BigDecimal("0.001")),
		LITRE("l", BigDecimal.ONE),
		
		// Imperial Units
		TEASPOON("tsp", new BigDecimal("0.004929")),
		TABLESPOON("tbsp", new BigDecimal("0.014786")),
		FLUID_OUNCE("fl. oz", new BigDecimal("0.029573")),
		PINT("pt", new BigDecimal("0.473163")),
		QUART("qt", new BigDecimal("0.946326")),
		GALLON("gal", new BigDecimal("3.785306"));
		
		private String abbr;
		private BigDecimal multiplier;
		
		Unit(String abbr, BigDecimal multiplier) {
			this.abbr = abbr;
			this.multiplier = multiplier;
		}

		@Override
		public String getAbbreviation() {
			return abbr;
		}

		@Override
		public BigDecimal metricMultiplier() {
			return multiplier;
		}
	}
}
