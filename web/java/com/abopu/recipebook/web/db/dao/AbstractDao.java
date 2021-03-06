///*
// * The MIT License (MIT)
// *
// * Copyright (c) 2016 Sarah Skanes
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
//package com.abopu.recipebook.web.db.dao;
//
//import com.abopu.recipebook.common.exception.DaoException;
//import com.abopu.recipebook.common.jdbc.ResultSetExtractor;
//import com.abopu.recipebook.web.db.dao.factory.DaoFactory;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Collection;
//
///**
// * @author Sarah Skanes
// * @created October 30, 2016.
// */
//public abstract class AbstractDao<T> {
//
//	protected DaoFactory factory;
//
//	public AbstractDao(DaoFactory factory) {
//		this.factory = factory;
//	}
//
//	protected final Collection<T> extract(ResultSet rs, ResultSetExtractor<T> extractor) throws SQLException, DaoException {
//		Collection<T> records = new ArrayList<>();
//		while (rs.next()) {
//			T record = extractor.extract(rs);
//			if (record != null) {
//				records.add(record);
//			}
//		}
//
//		return records;
//	}
//}
