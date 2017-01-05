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

/**
 * @author Sarah Skanes
 * @created September 24, 2016.
 */
public class FluidOunce extends VolumeUnit {
	FluidOunce(int value) {
		super(value);
	}

	FluidOunce(long value) {
		super(value);
	}

	FluidOunce(float value) {
		super(value);
	}

	FluidOunce(double value) {
		super(value);
	}

	FluidOunce(BigInteger value) {
		super(value);
	}

	FluidOunce(BigDecimal value) {
		super(value);
	}

	@Override
	public VolumeUnit asLitres() {
		return null;
	}

	@Override
	public VolumeUnit asTeaspoons() {
		return null;
	}

	@Override
	public VolumeUnit asTablespoons() {
		return null;
	}

	@Override
	public VolumeUnit asOunces() {
		return null;
	}

	@Override
	public VolumeUnit asPints() {
		return null;
	}

	@Override
	public VolumeUnit asQuarts() {
		return null;
	}

	@Override
	public VolumeUnit asGallons() {
		return null;
	}
}
