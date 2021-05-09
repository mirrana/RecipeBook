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
//package com.abopu.recipebook.web.controller.rapi;
//
//import com.abopu.recipebook.common.dto.Nutrient;
//import com.abopu.recipebook.common.service.NutrientDao;
//import com.abopu.recipebook.common.exception.DaoException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author Sarah Skanes
// * @created October 30, 2016.
// */
//@Controller
//@RequestMapping(value = "api/nutrients")
//public class NutrientResource extends RESTResource {
//
//	/***************************************************************************
//	 *
//	 * Local Variables
//	 *
//	 **************************************************************************/
//
//	@Autowired private NutrientDao dao;
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
//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity getAll() {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(dao.getAll());
//		} catch (DaoException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//		}
//	}
//
//	@RequestMapping(value = "{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity getById(@PathVariable Integer id) {
//		try {
//			Nutrient nutrient = dao.get(id);
//			if (nutrient != null) {
//				return ResponseEntity.status(HttpStatus.OK).body(nutrient);
//			} else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
//		} catch (DaoException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//		}
//	}
//
//	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
//	@ResponseBody
//	public ResponseEntity update(@PathVariable Integer id, Nutrient nutrient) {
//
//		try {
//			Nutrient n = dao.get(id);
//			if (n == null) {
//				return ResponseEntity.status(HttpStatus.CONFLICT).body("Record with ID " + id + " does not exist.");
//			}
//
//			nutrient.setId(id);
//			dao.update(nutrient);
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//		} catch (DaoException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//		}
//	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity create(Nutrient nutrient) {
//		try {
//			Nutrient record = dao.create(nutrient);
//			return ResponseEntity.status(HttpStatus.CREATED).body(record.getId());
//		} catch (DaoException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//		}
//	}
//}
