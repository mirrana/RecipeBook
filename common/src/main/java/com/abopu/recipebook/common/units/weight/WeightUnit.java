package com.abopu.recipebook.common.units.weight;

import com.abopu.recipebook.common.units.UnitClass;
import com.abopu.recipebook.common.units.UnitOfMeasurement;
import com.abopu.recipebook.common.units.UnitType;

import java.math.BigDecimal;
import java.math.BigInteger;

public class WeightUnit extends UnitOfMeasurement {

	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/

	/**
	 * {@inheritDoc}
	 */
	public WeightUnit(UnitClass category, double ratioAsMetric) {
		super(category, ratioAsMetric);
	}

	/**
	 * {@inheritDoc}
	 */
	public WeightUnit(UnitClass category, BigDecimal ratioAsMetric) {
		super(category, ratioAsMetric);
	}



	/***************************************************************************
	 *
	 * Public API
	 *
	 **************************************************************************/

	@Override
	public UnitType getUnitType() {
		return UnitType.WEIGHT;
	}
}
