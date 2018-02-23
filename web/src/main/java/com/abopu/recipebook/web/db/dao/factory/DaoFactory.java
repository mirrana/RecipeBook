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

package com.abopu.recipebook.web.db.dao.factory;

import com.abopu.recipebook.common.service.BrandDao;
import com.abopu.recipebook.common.service.FoodItemDao;
import com.abopu.recipebook.common.service.NutrientDao;
import com.abopu.recipebook.common.service.RecipeDao;
import com.abopu.recipebook.common.service.UnitDao;

/**
 * @author Sarah Skanes
 * @created February 07, 2017.
 */
public class DaoFactory {
	
	private FoodItemDao foodItemDao;
	private RecipeDao recipeDao;
	private BrandDao brandDao;
	private NutrientDao nutrientDao;
	private UnitDao unitDao;

	public FoodItemDao getFoodItemDao() {
		return foodItemDao;
	}

	public void setFoodItemDao(FoodItemDao foodItemDao) {
		this.foodItemDao = foodItemDao;
	}

	public RecipeDao getRecipeDao() {
		return recipeDao;
	}

	public void setRecipeDao(RecipeDao recipeDao) {
		this.recipeDao = recipeDao;
	}

	public BrandDao getBrandDao() {
		return brandDao;
	}

	public void setBrandDao(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	public NutrientDao getNutrientDao() {
		return nutrientDao;
	}

	public void setNutrientDao(NutrientDao nutrientDao) {
		this.nutrientDao = nutrientDao;
	}

	public UnitDao getUnitDao() {
		return unitDao;
	}

	public void setUnitDao(UnitDao unitDao) {
		this.unitDao = unitDao;
	}
}
