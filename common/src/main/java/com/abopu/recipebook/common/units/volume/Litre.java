package com.abopu.recipebook.common.units.volume;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Litre extends VolumeUnit {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	Litre(int value) {
		super(value);
	}

	Litre(long value) {
		super(value);
	}

	Litre(float value) {
		super(value);
	}

	Litre(double value) {
		super(value);
	}

	Litre(BigInteger value) {
		super(value);
	}

	Litre(BigDecimal value) {
		super(value);
	}



	/***************************************************************************
	 *
	 * Implementation
	 *
	 **************************************************************************/

	@Override
	public VolumeUnit asLitres() {
		return null;
	}
}
