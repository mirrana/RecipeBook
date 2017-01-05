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
import com.abopu.recipebook.common.jdbc.SQLStates;
import com.abopu.recipebook.common.service.DaoException;
import com.abopu.recipebook.common.service.NutrientDao;
import com.abopu.recipebook.web.db.dao.AbstractDao;
import com.abopu.recipebook.web.db.factory.ConnectionFactory;
import com.abopu.recipebook.web.db.tools.SQLHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author Sarah Skanes
 * @created October 30, 2016.
 */
public class JDBCNutrientDao extends AbstractDao<Nutrient> implements NutrientDao {

	/***************************************************************************
	 *
	 * Implementation: NutrientDao
	 *
	 **************************************************************************/

	@Override
	public Nutrient create(Nutrient object) throws DaoException {
		try (Connection conn = ConnectionFactory.getWebConnection();
				 PreparedStatement ps = conn.prepareStatement("INSERT INTO nutrients (id, name, unit) VALUES (?, ?, ?)")) {
			Integer id = SQLHelper.getNextSequenceValue(conn, "nutrient_id_seq");
			
			ps.setInt(1, id);
			ps.setString(2, object.getName());
			ps.setInt(3, object.getUnitId());
			
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
		return false;
	}

	@Override
	public Collection<Nutrient> getAll() throws DaoException {
		try (Connection conn = ConnectionFactory.getWebConnection();
				 PreparedStatement ps = conn.prepareStatement("SELECT * FROM nutrients");
				 ResultSet rs = ps.executeQuery()) {
			return processResult(rs);
		} catch (SQLException e) {
			throw new DaoException("Error retrieving nutrients.", e);
		}
	}

	@Override
	public Nutrient get(Integer id) throws DaoException {
		try (Connection conn = ConnectionFactory.getWebConnection();
				 PreparedStatement ps = conn.prepareStatement("SELECT * FROM nutrients WHERE ID = ?")) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return getFirstRecordOrNull(processResult(rs));
			}
		} catch (SQLException e) {
			throw new DaoException("Error retrieving nutrient (" + id + ").", e);
		}
	}

	@Override
	public boolean update(Nutrient nutrient) throws DaoException {
		try (Connection conn = ConnectionFactory.getWebConnection();
				 PreparedStatement ps = conn.prepareStatement("UPDATE nutrients SET name = ?, unit = ? WHERE id = ?")) {
			ps.setString(1, nutrient.getName());
			ps.setInt(2, nutrient.getUnitId());
			ps.setInt(3, nutrient.getId());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DaoException("Error updating nutrient (" + nutrient.getId() + ").", e);
		}
	}



	/***************************************************************************
	 *
	 * Implementation: AbstractDao
	 *
	 **************************************************************************/

	@Override
	protected Nutrient createRecord(ResultSet rs) throws SQLException {
		Nutrient nutrient = new Nutrient();
		nutrient.setId(rs.getInt("ID"));
		nutrient.setName(rs.getString("NAME"));
		nutrient.setUnitId(rs.getInt("UNIT"));

		return nutrient;
	}
}
