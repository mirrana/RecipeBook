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

package com.abopu.recipebook.desktop.manager.presenter;

import com.abopu.recipebook.desktop.presenter.Presenter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Sarah Skanes
 * @created January 14, 2017.
 */
public class UnitTypesFormPresenter extends Presenter {

	/***************************************************************************
	 *
	 * UI Elements
	 *
	 **************************************************************************/	

	@FXML private Label idLabel;
	@FXML private TextField nameTextField;



	/***************************************************************************
	 *
	 * Implementation: Initializable
	 *
	 **************************************************************************/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
