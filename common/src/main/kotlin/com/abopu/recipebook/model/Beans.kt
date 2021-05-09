/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021 Sarah Skanes
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

package com.abopu.recipebook.model

/**
 * The brand by which a product is marketed and sold under.
 * @param name the unique name for this brand as represented in the data model
 * @param title the human readable title for this brand
 */
data class Brand(val name: String, val title: String)

data class Nutrient(val name: String, val title: String)

data class FoodItem(val name: String, val title: String, val brand: Brand, val nutrients: Collection<Nutrient>)

data class Ingredient(val name: String, val title: String, val brand: Brand, val nutrients: Collection<Nutrient>)

data class Recipe(val name: String, val title: String, val ingredients: Collection<Ingredient>)

data class NutrientType(val name: String)