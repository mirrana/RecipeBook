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
//import com.abopu.recipebook.common.dto.Brand;
//import com.abopu.recipebook.common.exception.NotImplementedException;
//import com.abopu.recipebook.common.service.BrandDao;
//import com.abopu.recipebook.common.exception.DaoException;
//import com.abopu.recipebook.web.db.dao.AbstractDao;
//import com.abopu.recipebook.web.db.dao.factory.DaoFactory;
//import com.abopu.recipebook.web.db.factory.ConnectionFactory;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Map;
//
///**
// * @author Sarah Skanes
// * @created February 06, 2017.
// */
//public class JDBCBrandDao extends AbstractDao<Brand> implements BrandDao {
//
//	/***************************************************************************
//	 *
//	 * SQL Statements
//	 *
//	 **************************************************************************/
//
//
//
//	/***************************************************************************
//	 *
//	 * Constructors
//	 *
//	 **************************************************************************/
//
//	public JDBCBrandDao(DaoFactory factory) {
//		super(factory);
//	}
//
//
//
//	/***************************************************************************
//	 *
//	 * Implementation: BrandDao
//	 *
//	 **************************************************************************/
//
//	@Override
//	public Brand create(Brand object) throws DaoException {
//		throw new NotImplementedException("create");
//	}
//
//	@Override
//	public boolean delete(Integer id) throws DaoException {
//		throw new NotImplementedException("delete");
//	}
//
//	@Override
//	public Collection<Brand> getAll() throws DaoException {
//		try (Connection conn = ConnectionFactory.getWebConnection();
//				 PreparedStatement ps = conn.prepareStatement("SELECT * FROM brand_names");
//				 ResultSet rs = ps.executeQuery()) {
//			return extract(rs, this::processRecord);
//		} catch (SQLException e) {
//			return Collections.emptyList();
//		}
//	}
//
//	@Override
//	public Brand get(Integer id) throws DaoException {
//		try (Connection conn = ConnectionFactory.getWebConnection();
//		PreparedStatement ps = conn.prepareStatement("SELECT * FROM brand_names WHERE id = ?")) {
//			ps.setInt(1, id);
//			try (ResultSet rs = ps.executeQuery()) {
//				return extract(rs, this::processRecord).stream().findFirst().orElse(null);
//			}
//		} catch (SQLException e) {
//			return null;
//		}
//	}
//
//	@Override
//	public boolean update(Brand object) throws DaoException {
//		throw new NotImplementedException("update");
//	}
//
//	@Override
//	public Collection<Brand> getByQuery(Map<String, String> query) throws DaoException {
//		try (Connection conn = ConnectionFactory.getWebConnection();
//		PreparedStatement ps = conn.prepareStatement("SELECT * FROM brand_names WHERE name like '%' || ? || '%'")) {
//			ps.setString(1, query.get("name"));
//			try (ResultSet rs = ps.executeQuery()) {
//				return extract(rs, this::processRecord);
//			}
//		} catch (SQLException e) {
//			return Collections.emptyList();
//		}
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
//	private Brand processRecord(ResultSet rs) throws SQLException {
//		Brand brand = new Brand();
//		brand.setId(rs.getInt("ID"));
//		brand.setName(rs.getString("NAME"));
//
//		return brand;
//	}
//}
