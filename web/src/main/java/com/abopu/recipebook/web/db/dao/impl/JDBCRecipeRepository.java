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

import com.abopu.recipebook.common.dto.Recipe;
import com.abopu.recipebook.common.exception.DaoException;
import com.abopu.recipebook.common.exception.NotImplementedException;
import com.abopu.recipebook.common.service.RecipeDao;
import com.abopu.recipebook.web.db.dao.AbstractDao;
import com.abopu.recipebook.web.db.dao.factory.DaoFactory;
import com.abopu.recipebook.web.db.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

/**
 * @author Sarah Skanes
 * @created October 22, 2016.
 */
public class JDBCRecipeDao extends AbstractDao<Recipe> implements RecipeDao {
	
	/***************************************************************************
	 *
	 * Constructors
	 *
	 **************************************************************************/
	

	public JDBCRecipeDao(DaoFactory factory) {
		super(factory);
	}
	
	
	
	/***************************************************************************
	 *
	 * 
	 *
	 **************************************************************************/

	@Override
	public Recipe create(Recipe object) {
		throw new NotImplementedException("create");
	}

	@Override
	public boolean delete(Integer id) {
		throw new NotImplementedException("delete");
	}

	@Override
	public Collection<Recipe> getAll() {
		throw new NotImplementedException("getAll");
	}

	@Override
	public Recipe get(Integer id) {
		throw new NotImplementedException("get");
	}

	@Override
	public boolean update(Recipe object) {
		throw new NotImplementedException("update");
	}

	@Override
	public Collection<Recipe> getByQuery(Map<String, String> query) throws DaoException {
		throw new NotImplementedException("getByQuery");
	}
}