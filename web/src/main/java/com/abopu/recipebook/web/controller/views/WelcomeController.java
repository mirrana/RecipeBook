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

package com.abopu.recipebook.web.controller.views;

import com.abopu.recipebook.common.dto.FoodItem;
import com.abopu.recipebook.common.exception.DaoException;
import com.abopu.recipebook.web.db.dao.factory.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Collections;

@Controller
@RequestMapping(value = "/")
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@Autowired DaoFactory daoFactory;
	
	public WelcomeController() {}

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index() is executed!");
		
		model.addAttribute("recipient", "World");
		model.addAttribute("bar", "Title");
		
		return "index";
	}

//	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
//	public ModelAndView hello(@PathVariable("name") String name) {
//
//		logger.debug("hello() is executed - $name {}", name);
//
//		ModelAndView model = new ModelAndView();
//		model.setViewName("index");
//
//		model.addObject("title", helloWorldService.getTitle(name));
//		model.addObject("msg", helloWorldService.getDesc());
//
//		return model;
//
//	}
	
	@RequestMapping(value = "food/all", method = RequestMethod.GET)
	public String food() {
		return "food";
	}
	
	@RequestMapping(value = "food/{id}", method = RequestMethod.GET)
	public String foodItem() {
		return "foodItem";
	}

	@RequestMapping(value = "food/new", method = RequestMethod.GET)
	public String createFood() {
		return "food";
	}
	
	@ModelAttribute("foodItems")
	public Collection<FoodItem> foodItems() {
		try {
			return daoFactory.getFoodItemDao().getAll();
		} catch (DaoException e) {
			return Collections.emptyList();
		}
	}
}