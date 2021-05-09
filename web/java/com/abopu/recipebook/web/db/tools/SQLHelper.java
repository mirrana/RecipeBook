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
//package com.abopu.recipebook.web.db.tools;
//
//import com.abopu.lang.NotImplementedException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.Collection;
//import java.util.LinkedList;
//
//import static com.abopu.recipebook.web.db.tools.SQLHelper.Dialect.SQLITE;
//
///**
// * @author Sarah Skanes
// * @created October 22, 2016.
// */
//public class SQLHelper {
//
//	private static final Logger LOG = LoggerFactory.getLogger(SQLHelper.class);
//
//	public static Dialect getDialect(Connection conn) throws SQLException {
//		DatabaseMetaData metadata = conn.getMetaData();
//		if (metadata != null) {
//			String productName = metadata.getDatabaseProductName();
//			if (Dialect.POSTGRESQL.getDatabaseProductName().equals(productName)) {
//				return Dialect.POSTGRESQL;
//			} else if (SQLITE.getDatabaseProductName().equals(productName)) {
//				return SQLITE;
//			} else {
//				LOG.error("Found unknown dialect: " + productName + ";" + metadata.getDatabaseProductVersion());
//			}
//		}
//
//		return null;
//	}
//
//	public static Integer getNextSequenceValue(Connection conn, String sequenceName) throws SQLException {
//		return getNextSequenceValues(conn, sequenceName, 1).iterator().next();
//	}
//
//	/**
//	 * Ask the database for one or more sequence numbers given by the supplied sequence name.
//	 *
//	 * @param conn
//	 * @param sequenceName
//	 * @param quantity
//	 * @return
//	 * @throws SQLException
//	 */
//	public static Collection<Integer> getNextSequenceValues(Connection conn, String sequenceName, Integer quantity) throws SQLException {
//		if (conn == null)
//			throw new NullPointerException("conn");
//		if (sequenceName == null)
//			throw new NullPointerException("sequenceName");
//		if (quantity < 1)
//			throw new IllegalArgumentException("quantity must be greater than or equal to 1.");
//
//		Collection<Integer> values = new LinkedList<>();
//
//		Dialect dialect = getDialect(conn);
//		if (dialect != null) {
//			try (PreparedStatement ps = conn.prepareStatement(getSequenceStatement(dialect))) {
//				bindVariables(ps, dialect, sequenceName, quantity);
//				try (ResultSet rs = ps.executeQuery()) {
//					while (rs.next()) {
//						values.add(rs.getInt(1));
//					}
//				}
//			}
//		}
//
//		return values;
//	}
//
//	private static String getSequenceStatement(Dialect dialect) {
//		switch (dialect) {
//			case POSTGRESQL:
//				return "SELECT nextval(?) from generate_series(1, ?)";
//			case SQLITE:
//			default:
//				throw new NotImplementedException("Dialect: " + dialect.name());
//		}
//	}
//
//	private static void bindVariables(PreparedStatement ps, Dialect dialect, String sequenceName, Integer quantity) throws SQLException {
//		switch (dialect) {
//			case POSTGRESQL:
//				ps.setString(1, sequenceName);
//				ps.setInt(2, quantity);
//				break;
//			case SQLITE:
//			default:
//				throw new NotImplementedException("Dialect: " + dialect.name());
//		}
//	}
//
//
//
//	/***************************************************************************
//	 *
//	 * Classes
//	 *
//	 **************************************************************************/
//
//	public enum Dialect {
//		POSTGRESQL("PostgreSQL", "PostgreSQL"),
//		SQLITE("SQLite", "SQLite");
//
//		private final String readableName;
//		private final String metadataName;
//
//		Dialect(String readableName, String metadataName) {
//			this.readableName = readableName;
//			this.metadataName = metadataName;
//		}
//
//		public String getReadableName() {
//			return readableName;
//		}
//
//		public String getDatabaseProductName() {
//			return metadataName;
//		}
//
//		public static Dialect fromString(String readableName) {
//			if (POSTGRESQL.readableName.equals(readableName)) { return POSTGRESQL; }
//			if (SQLITE.readableName.equals(readableName)) { return SQLITE; }
//
//			throw new IllegalArgumentException("Unsupported database dialect '" + readableName + "'");
//		}
//	}
//}
