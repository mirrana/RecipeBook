///*
// * The MIT License (MIT)
// *
// * Copyright (c) 2017 Sarah Skanes
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//
//package com.abopu.recipebook.web.db.dao.impl;
//
//import com.abopu.recipebook.common.dto.FoodItem;
//import com.abopu.recipebook.common.exception.DaoException;
//import com.abopu.recipebook.common.exception.NotImplementedException;
//import com.abopu.recipebook.common.service.FoodItemDao;
//import com.abopu.recipebook.common.units.UnitOfMeasurement;
//import com.abopu.recipebook.web.db.dao.AbstractDao;
//import com.abopu.recipebook.web.db.dao.factory.DaoFactory;
//import com.abopu.recipebook.web.db.factory.ConnectionFactory;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.Map;
//
///**
// * @author Sarah Skanes
// * @created February 07, 2017.
// */
//public class JDBCFoodItemDao extends AbstractDao<FoodItem> implements FoodItemDao {
//
//	/***************************************************************************
//	 *
//	 * SQL Statements
//	 *
//	 **************************************************************************/
//
//	private static final String SQL_GET_FOOD_ITEM = "" +
//			"\n" +
//			"SELECT\n" +
//			"  f.id food_id,\n" +
//			"  f.name,\n" +
//			"  b.id brand_id,\n" +
//			"  fb.serving_weight_scalar,\n" +
//			"  fb.serving_weight_unit_id,\n" +
//			"  fb.serving_volume_scalar,\n" +
//			"  fb.serving_volume_unit_id\n" +
//			"FROM foods f\n" +
//			"  LEFT OUTER JOIN foods_brands fb ON (f.id = fb.food_id)\n" +
//			"  LEFT OUTER JOIN brand_names b ON (b.id = fb.brand_id)\n" +
//			"WHERE fb.id = ?";
//
//	private static final String SQL_GET_ALL_FOOD_ITEMS = "" +
//			"SELECT\n" +
//			"  f.id food_id,\n" +
//			"  f.name,\n" +
//			"  b.id brand_id,\n" +
//			"  fb.serving_weight_scalar,\n" +
//			"  fb.serving_weight_unit_id,\n" +
//			"  fb.serving_volume_scalar,\n" +
//			"  fb.serving_volume_unit_id\n" +
//			"FROM foods f\n" +
//			"  LEFT OUTER JOIN foods_brands fb ON (f.id = fb.food_id)\n" +
//			"  LEFT OUTER JOIN brand_names b ON (b.id = fb.brand_id)";
//
//
//
//	/***************************************************************************
//	 *
//	 * Constructors
//	 *
//	 **************************************************************************/
//
//	public JDBCFoodItemDao(DaoFactory daoFactory) {
//		super(daoFactory);
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
//	@Override
//	public FoodItem create(FoodItem object) throws DaoException {
//		throw new NotImplementedException("create");
//	}
//
//	@Override
//	public boolean delete(Integer id) throws DaoException {
//		throw new NotImplementedException("delete");
//	}
//
//	@Override
//	public Collection<FoodItem> getAll() throws DaoException {
//		try (Connection conn = ConnectionFactory.getWebConnection();
//				 PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL_FOOD_ITEMS);
//				 ResultSet rs = ps.executeQuery()) {
//			return extract(rs, this::processRecord);
//		} catch (SQLException e) {
//			throw new DaoException("Error getting food items.", e);
//		}
//	}
//
//	@Override
//	public FoodItem get(Integer id) throws DaoException {
//		try (Connection conn = ConnectionFactory.getWebConnection();
//				 PreparedStatement ps = conn.prepareStatement(SQL_GET_FOOD_ITEM)) {
//			ps.setInt(1, id);
//			try (ResultSet rs = ps.executeQuery()) {
//				return extract(rs, this::processRecord).stream().findFirst().orElse(null);
//			}
//		} catch (SQLException e) {
//			throw new DaoException(String.format("Error getting food item for id %s", id), e);
//		}
//	}
//
//	@Override
//	public boolean update(FoodItem object) throws DaoException {
//		throw new NotImplementedException("update");
//	}
//
//	@Override
//	public Collection<FoodItem> getByQuery(Map<String, String> query) throws DaoException {
//		throw new NotImplementedException("getByQuery");
//	}
//
//
//
//	/***************************************************************************
//	 *
//	 * Private API
//	 *
//	 **************************************************************************/
//
//	private FoodItem processRecord(ResultSet rs) throws SQLException, DaoException {
//		FoodItem foodItem = new FoodItem();
//		foodItem.setId(rs.getInt("FOOD_ID"));
//		foodItem.setName(rs.getString("NAME"));
//		foodItem.setBrand(factory.getBrandDao().get(rs.getInt("BRAND_ID")));
//		foodItem.setNutrients(factory.getNutrientDao().getByFood(rs.getInt("FOOD_ID"), rs.getInt("BRAND_ID")));
//
//		int serving_volume_unit_id = rs.getInt("SERVING_VOLUME_UNIT_ID");
//		UnitOfMeasurement volumeServingUnit = null;
//		if (!rs.wasNull()) {
//			volumeServingUnit = factory.getUnitDao().get(serving_volume_unit_id);
//			volumeServingUnit.setScalarValue(rs.getBigDecimal("SERVING_VOLUME_SCALAR"));
//		}
//		foodItem.setServingVolume(volumeServingUnit);
//
//		int serving_weight_unit_id = rs.getInt("SERVING_WEIGHT_UNIT_ID");
//		UnitOfMeasurement weightServingUnit = null;
//		if (!rs.wasNull()) {
//			weightServingUnit = factory.getUnitDao().get(serving_weight_unit_id);
//			weightServingUnit.setScalarValue(rs.getBigDecimal("SERVING_WEIGHT_SCALAR"));
//		}
//		foodItem.setServingWeight(weightServingUnit);
//
//		return foodItem;
//	}
//}
