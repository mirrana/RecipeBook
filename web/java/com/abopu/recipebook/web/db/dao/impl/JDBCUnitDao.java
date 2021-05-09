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
//import com.abopu.recipebook.common.exception.DaoException;
//import com.abopu.recipebook.common.exception.NotImplementedException;
//import com.abopu.recipebook.common.service.UnitDao;
//import com.abopu.recipebook.common.units.UnitFactory;
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
// * @created February 09, 2017.
// */
//public class JDBCUnitDao extends AbstractDao<UnitOfMeasurement> implements UnitDao {
//
//	/***************************************************************************
//	 *
//	 * SQL Statements
//	 *
//	 **************************************************************************/
//
//	private static final String SQL_GET_ALL_UNIT_TYPES = "SELECT * FROM units_of_measurement";
//	private static final String SQL_GET_UNIT_TYPE = "SELECT * FROM units_of_measurement WHERE id = ?";
//
//
//
//	/***************************************************************************
//	 *
//	 * Constructors
//	 *
//	 **************************************************************************/
//
//	public JDBCUnitDao(DaoFactory factory) {
//		super(factory);
//	}
//
//
//
//	/***************************************************************************
//	 *
//	 *
//	 *
//	 **************************************************************************/
//
//
//	@Override
//	public UnitOfMeasurement create(UnitOfMeasurement object) throws DaoException {
//		throw new NotImplementedException("create");
//	}
//
//	@Override
//	public boolean delete(Integer id) throws DaoException {
//		throw new NotImplementedException("delete");
//	}
//
//	@Override
//	public Collection<UnitOfMeasurement> getAll() throws DaoException {
//		try (Connection conn = ConnectionFactory.getWebConnection();
//				 PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL_UNIT_TYPES);
//				 ResultSet rs = ps.executeQuery()) {
//			return extract(rs, this::processRecord);
//		} catch (SQLException e) {
//			throw new DaoException("Error getting units.", e);
//		}
//	}
//
//	@Override
//	public UnitOfMeasurement get(Integer id) throws DaoException {
//		try (Connection conn = ConnectionFactory.getWebConnection();
//				 PreparedStatement ps = conn.prepareStatement(SQL_GET_UNIT_TYPE)) {
//			ps.setInt(1, id);
//			try (ResultSet rs = ps.executeQuery()) {
//				return extract(rs, this::processRecord).stream().findFirst().orElse(null);
//			}
//		} catch (SQLException e) {
//			throw new DaoException(String.format("Error getting unit for id %s.", id), e);
//		}
//	}
//
//	@Override
//	public boolean update(UnitOfMeasurement object) throws DaoException {
//		throw new NotImplementedException("update");
//	}
//
//	@Override
//	public Collection<UnitOfMeasurement> getByQuery(Map<String, String> query) throws DaoException {
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
//	private UnitOfMeasurement processRecord(ResultSet rs) throws SQLException {
//		char typeCode = rs.getString("TYPE").charAt(0);
//		char classCode = rs.getString("CLASS").charAt(0);
//
//		UnitOfMeasurement uom = UnitFactory.create(typeCode, classCode, rs.getBigDecimal("AS_METRIC"));
//		uom.setId(rs.getInt("ID"));
//		uom.setName(rs.getString("NAME"));
//		uom.setAbbreviation(rs.getString("ABBREVIATION"));
//
//		return uom;
//	}
//}
