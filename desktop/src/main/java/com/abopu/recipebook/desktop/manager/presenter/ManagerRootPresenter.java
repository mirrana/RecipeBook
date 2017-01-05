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

package com.abopu.recipebook.desktop.manager.presenter;

import com.abopu.recipebook.desktop.presenter.Presenter;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Sarah Skanes
 * @created October 30, 2016.
 */
public class ManagerRootPresenter extends Presenter {
	
	/***************************************************************************
	 *
	 * UI Elements
	 *
	 **************************************************************************/

	@FXML private TabPane tabPane;
	@FXML private Tab     foodItemsTab;
	@FXML private Tab     brandsTab;
	@FXML private Tab     nutrientsTab;
	@FXML private Tab     unitTypesTab;
	@FXML private Tab     unitsTab;
	private       Node    foodItemsForm;
	private       Node    brandsForm;
	private       Node    nutrientsForm;
	private       Node    unitTypesForm;
	private       Node    unitsForm;
	
	
	
	/***************************************************************************
	 *
	 * Fields
	 *
	 **************************************************************************/
	
	private Presenter visibleFormPresenter;



	/***************************************************************************
	 *
	 * Implementation: Initializable
	 *
	 **************************************************************************/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabPane.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			visibleFormPresenter = (Presenter) getView(nv.getContent()).getPresenter();
		});
		
		foodItemsForm = createNode("FoodItemsForm");
		brandsForm = createNode("BrandsForm");
		nutrientsForm = createNode("NutrientsForm");
		unitTypesForm = createNode("UnitTypesForm");
		unitsForm = createNode("UnitsForm");
		
		foodItemsTab.setContent(foodItemsForm);
		brandsTab.setContent(brandsForm);
		nutrientsTab.setContent(nutrientsForm);
		unitTypesTab.setContent(unitTypesForm);
		unitsTab.setContent(unitsForm);
	}
}
