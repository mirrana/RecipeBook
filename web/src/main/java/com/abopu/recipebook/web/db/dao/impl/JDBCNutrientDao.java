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

package com.abopu.recipebook.web.db.dao.impl;

import com.abopu.recipebook.common.dto.Nutrient;
import com.abopu.recipebook.common.exception.NotImplementedException;
import com.abopu.recipebook.common.jdbc.SQLStates;
import com.abopu.recipebook.common.exception.DaoException;
import com.abopu.recipebook.common.service.NutrientDao;
import com.abopu.recipebook.web.db.dao.AbstractDao;
import com.abopu.recipebook.web.db.dao.factory.DaoFactory;
import com.abopu.recipebook.web.db.factory.ConnectionFactory;
import com.abopu.recipebook.web.db.tools.SQLHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Sarah Skanes
 * @created October 30, 2016.
 */
public class JDBCNutrientDao extends AbstractDao<Nutrient> implements NutrientDao {

	/***************************************************************************
	 *
	 * SQL Statements
	 *
	 **************************************************************************/

	private static final String SQL_ADD_NUTRIENT       = "INSERT INTO nutrients (id, name, unit) VALUES (?, ?, ?)";
	private static final String SQL_GET_ALL_NUTRIENTS  = "SELECT * FROM nutrients";
	private static final String SQL_GET_NUTRIENT_BY_ID = "SELECT * FROM nutrients WHERE ID = ?";
	private static final String SQL_UPDATE_NUTRIENT    = "UPDATE nutrients SET name = ?, unit = ? WHERE id = ?";
	private static final String SQL_GET_NUTRIENTS_FOR_FOOD = "" + 
			"SELECT\n" +
			"  n.id,\n" +
			"  n.name,\n" +
			"  fn.scalar,\n" +
			"  n.unit\n" +
			"FROM foods_brands fb\n" +
			"  LEFT OUTER JOIN foods_nutrients fn ON (fb.id = fn.foods_brands_id)\n" +
			"  LEFT OUTER JOIN nutrients n ON (fn.nutrient_id = n.id)\n" +
			"WHERE fb.food_id = ? AND fb.brand_id = ?";
	
	
	
	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/
	
	public JDBCNutrientDao(DaoFactory factory) {
		super(factory);
	}



	/***************************************************************************
	 *
	 * Implementation: NutrientDao
	 *
	 **************************************************************************/

	@Override
	public Nutrient create(Nutrient object) throws DaoException {
		try (Connection conn = ConnectionFactory.getWebConnection();
				 PreparedStatement ps = conn.prepareStatement(SQL_ADD_NUTRIENT)) {
			Integer id = SQLHelper.getNextSequenceValue(conn, "nutrient_id_seq");
			
			ps.setInt(1, id);
			ps.setString(2, object.getName());
//			ps.setInt(3, object.getUnitId());
			
			ps.executeUpdate();

			return get(id);
		} catch (SQLException e) {
			if (e.getSQLState().equals(SQLStates.INTEGRITY_CONSTRAINT_VIOLATION)) {
				throw new DaoException(e.getMessage(), DaoException.ErrorCode.INTEGRITY_VIOLATION);
			}
			
			throw new DaoException("Error creating new nutrient.", e);
		}
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		throw new NotImplementedException("delete");
	}

	@Override
	public Collection<Nutrient> getAll() throws DaoException {
		try (Connection conn = ConnectionFactory.getWebConnection();
				 PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL_NUTRIENTS);
				 ResultSet rs = ps.executeQuery()) {
			return extract(rs, this::processNutrientRecord);
		} catch (SQLException e) {
			throw new DaoException("Error retrieving nutrients.", e);
		}
	}

	@Override
	public Nutrient get(Integer id) throws DaoException {
		try (Connection conn = ConnectionFactory.getWebConnection();
				 PreparedStatement ps = conn.prepareStatement(SQL_GET_NUTRIENT_BY_ID)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return extract(rs, this::processNutrientRecord).stream().findFirst().orElse(null);
			}
		} catch (SQLException e) {
			throw new DaoException("Error retrieving nutrient (" + id + ").", e);
		}
	}

	@Override
	public boolean update(Nutrient nutrient) throws DaoException {
		throw new NotImplementedException("update");
	}

	@Override
	public Collection<Nutrient> getByQuery(Map<String, String> query) throws DaoException {
		throw new NotImplementedException("getByQuery");
	}

	@Override
	public Map<String, Nutrient> getByFood(Integer foodId, Integer brandId) throws DaoException {
		try (Connection conn = ConnectionFactory.getWebConnection();
				 PreparedStatement ps = conn.prepareStatement(SQL_GET_NUTRIENTS_FOR_FOOD)) {
			ps.setInt(1, foodId);
			ps.setInt(2, brandId);
			try (ResultSet rs = ps.executeQuery()) {
				return extract(rs, r -> {
					Nutrient nutrient = processNutrientRecord(r);
					nutrient.getUnit().setScalarValue(r.getBigDecimal("SCALAR"));
					
					return nutrient;
				}).stream().collect(Collectors.toMap(Nutrient::getName, Function.identity()));
			}
		} catch (SQLException e) {
			throw new DaoException("Error getting Nutrients for food item.", e);
		}
	}



	/***************************************************************************
	 *
	 * Private API
	 *
	 **************************************************************************/

	private Nutrient processNutrientRecord(ResultSet rs) throws SQLException, DaoException {
		Nutrient nutrient = new Nutrient();
		nutrient.setId(rs.getInt("ID"));
		nutrient.setName(rs.getString("NAME"));
		nutrient.setUnit(factory.getUnitDao().get(rs.getInt("UNIT")));

		return nutrient;
	}
}
