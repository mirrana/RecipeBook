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

package com.abopu.recipebook.common.units.volume;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @author Sarah Skanes
 * @created September 24, 2016.
 */
public class Pint extends VolumeUnit {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	Pint(int value) {
		super(value);
	}

	Pint(long value) {
		super(value);
	}

	Pint(float value) {
		super(value);
	}

	Pint(double value) {
		super(value);
	}

	Pint(BigInteger value) {
		super(value);
	}

	Pint(BigDecimal value) {
		super(value);
	}



	/***************************************************************************
	 *
	 *
	 *
	 **************************************************************************/

	@Override
	public Litre asLitres() {
		return new Litre(getValue().multiply(BigDecimal.valueOf(0.473)));
	}

	@Override
	public Teaspoon asTeaspoons() {
		return new Teaspoon(getValue().multiply(BigDecimal.valueOf(96)));
	}

	@Override
	public Tablespoon asTablespoons() {
		return new Tablespoon(getValue().multiply(BigDecimal.valueOf(32)));
	}

	@Override
	public FluidOunce asOunces() {
		return new FluidOunce(getValue().multiply(BigDecimal.valueOf(16)));
	}

	@Override
	public Pint asPints() {
		return this;
	}

	@Override
	public Quart asQuarts() {
		return new Quart(getValue().divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP));
	}

	@Override
	public Gallon asGallons() {
		return new Gallon(getValue().divide(BigDecimal.valueOf(8), RoundingMode.HALF_UP));
	}
}
