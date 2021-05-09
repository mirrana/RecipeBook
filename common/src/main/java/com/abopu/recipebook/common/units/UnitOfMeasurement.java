//package com.abopu.recipebook.common.units;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//
///**
// * Abstract class representing a quantity of a given unit of measurement.
// * Implementing classes should implement convenience methods for allowing
// * simple conversion between different units of the same class.
// * <p/>
// * Scalar values for the unit are stored as {@link BigInteger}.
// *
// * @author Sarah Skanes &lt;mirrana@abopu.com&gt;
// */
//public abstract class UnitOfMeasurement {
//
//	private Integer id;
//	private String name;
//	private String abbreviation;
//	private BigDecimal ratioAsMetric;
//	private UnitClass category;
//
//	private BigDecimal scalarValue;
//
//
//
//	/***************************************************************************
//	 *
//	 * Constructors
//	 *
//	 **************************************************************************/
//
//	protected UnitOfMeasurement(UnitClass category, double ratioAsMetric) {
//		this(category, BigDecimal.valueOf(ratioAsMetric));
//	}
//
//	protected UnitOfMeasurement(UnitClass category, BigDecimal ratioAsMetric) {
//		this.category = category;
//		this.ratioAsMetric = ratioAsMetric;
//	}
//
//
//
//	/***************************************************************************
//	 *
//	 * Public API
//	 *
//	 **************************************************************************/
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getAbbreviation() {
//		return abbreviation;
//	}
//
//	public void setAbbreviation(String abbreviation) {
//		this.abbreviation = abbreviation;
//	}
//
//	public BigDecimal getRatioAsMetric() {
//		return ratioAsMetric;
//	}
//
//	public void setRatioAsMetric(BigDecimal ratioAsMetric) {
//		this.ratioAsMetric = ratioAsMetric;
//	}
//
//	public UnitClass getCategory() {
//		return category;
//	}
//
//	public void setCategory(UnitClass category) {
//		this.category = category;
//	}
//
//	public BigDecimal getScalarValue() {
//		return scalarValue;
//	}
//
//	public void setScalarValue(BigDecimal scalarValue) {
//		this.scalarValue = scalarValue;
//	}
//
//
//
//	/***************************************************************************
//	 *
//	 * Abstract API
//	 *
//	 **************************************************************************/
//
//	public abstract UnitType getUnitType();
//}
