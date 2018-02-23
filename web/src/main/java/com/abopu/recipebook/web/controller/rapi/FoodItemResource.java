/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Sarah Skanes
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

package com.abopu.recipebook.web.controller.rapi;

import com.abopu.recipebook.common.dto.FoodItem;
import com.abopu.recipebook.common.exception.DaoException;
import com.abopu.recipebook.common.service.FoodItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sarah Skanes
 * @created February 07, 2017.
 */
@Controller
@RequestMapping(value = "api/food")
public class FoodItemResource extends RESTResource {
	
	/***************************************************************************
	 *
	 * Local Variables
	 *
	 **************************************************************************/
	
	@Autowired FoodItemDao dao;
	
	
	
	/***************************************************************************
	 *
	 * 
	 *
	 **************************************************************************/
	

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(dao.getAll());
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getById(@PathVariable Integer id) {
		try {
			FoodItem foodItem = dao.get(id);
			if (foodItem != null) {
				return ResponseEntity.status(HttpStatus.OK).body(foodItem);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}

	@RequestMapping(value = "search")
	@ResponseBody
	public ResponseEntity search(@RequestParam("filter") String filter) {
		try {
			Map<String, String> query = new HashMap<>();
			query.put("name", filter);

			return ResponseEntity.status(HttpStatus.OK).body(dao.getByQuery(query));
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity update(@PathVariable Integer id, FoodItem foodItem) {
		try {
			FoodItem f = dao.get(id);
			if (f == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Record with ID " + id + " does not exist.");
			}

			foodItem.setId(id);
			dao.update(foodItem);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity create(FoodItem brand) {
		try {
			FoodItem foodItem = dao.create(brand);
			return ResponseEntity.status(HttpStatus.CREATED).body(foodItem.getId());
		} catch (DaoException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	
}
