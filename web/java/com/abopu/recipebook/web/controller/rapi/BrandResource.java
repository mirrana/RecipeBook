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
//import com.abopu.recipebook.common.dto.Brand;
//import com.abopu.recipebook.common.service.BrandDao;
//import com.abopu.recipebook.common.exception.DaoException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Sarah Skanes
// * @created February 07, 2017.
// */
//@Controller
//@RequestMapping(value = "api/brands")
//public class BrandResource extends RESTResource {
//
//	@Autowired private BrandDao dao;
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
//			Brand brand = dao.get(id);
//			if (brand != null) {
//				return ResponseEntity.status(HttpStatus.OK).body(brand);
//			} else {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
//		} catch (DaoException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//		}
//	}
//
//	@RequestMapping(value = "search")
//	@ResponseBody
//	public ResponseEntity search(@RequestParam("filter") String filter) {
//		try {
//			Map<String, String> query = new HashMap<>();
//			query.put("name", filter);
//
//			return ResponseEntity.status(HttpStatus.OK).body(dao.getByQuery(query));
//		} catch (DaoException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//		}
//	}
//
//	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
//	@ResponseBody
//	public ResponseEntity update(@PathVariable Integer id, Brand brand) {
//
//		try {
//			Brand b = dao.get(id);
//			if (b == null) {
//				return ResponseEntity.status(HttpStatus.CONFLICT).body("Record with ID " + id + " does not exist.");
//			}
//
//			brand.setId(id);
//			dao.update(brand);
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//		} catch (DaoException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//		}
//	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity create(Brand brand) {
//		try {
//			Brand record = dao.create(brand);
//			return ResponseEntity.status(HttpStatus.CREATED).body(record.getId());
//		} catch (DaoException e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
//		}
//	}
//}
