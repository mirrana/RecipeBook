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

package com.abopu.recipebook.common.service;

import java.util.Collection;

/**
 * @author Sarah Skanes
 * @created October 22, 2016.
 */
public interface DAO<T> {
	
	/**
	 * <p>Persist a new object to the backing store.</p>
	 * <p>Any id in the given object will be ignored,
	 * and a new one will be generated during the persist operation.</p>
	 *
	 * @param object  
	 * 		record to persist
	 * @return copy of object saved with generated values included 	
	 */
	T create(T object) throws DaoException;

	/**
	 * <p>Remove a record from the backing store.</p>
	 * @param id id of record to remove
	 * @return true if a record was deleted, false otherwise.
	 */
	boolean delete(Integer id) throws DaoException;
	
	Collection<T> getAll() throws DaoException;

	/**
	 * <p>Get data from the backing store.</p>
	 * <p>Returns null if no record is found with the given id.</p>
	 *
	 * @param id
	 * 		primary key of record to retrieve
	 * @return a DAO representing the object requested, or null if the requested record does not exist.
	 */
	T get(Integer id) throws DaoException;

	/**
	 * <p>Persist an updated version of a record to the backing store.</p>
	 * <p>The id stored in the object will be used to locate the existing
	 * record to update. If no id is given, or if the id cannot be found, 
	 * then nothing happens.</p>
	 *
	 * @param object
	 * 		record to persist
	 * @return true if an update was performed, false otherwise.
	 */
	boolean update(T object) throws DaoException;
}
