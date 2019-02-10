///*
// * The MIT License (MIT)
// *
// * Copyright (c) 2016 Sarah Skanes
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
//package com.abopu.recipebook.desktop.presenter;
//
//import com.abopu.recipebook.desktop.manager.FoodManager;
//import com.airhacks.afterburner.views.FXMLView;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
///**
// * @author Sarah Skanes
// * @created October 30, 2016.
// */
//public abstract class Presenter implements Initializable {
//	
//	/***************************************************************************
//	 *
//	 * Constants
//	 *
//	 **************************************************************************/
//	
//	private static final Map<Node, FXMLView> views = new HashMap<>();
//	private static final Map<String, ? extends Class<? extends FXMLView>> viewClasses;
//	static {
//		viewClasses = FoodManager.reflections.getSubTypesOf(FXMLView.class)
//																				 .stream()
//																				 .collect(
//																	 		Collectors.toMap(
//																	 				c -> c.getSimpleName().substring(0, c.getSimpleName().lastIndexOf("View")), 
//																					Function.identity()));
//	}
//
//	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
//
//	/***************************************************************************
//	 *
//	 * Shared API
//	 *
//	 **************************************************************************/
//
//	protected Node createNode(String name) {
//		Class<? extends FXMLView> viewClass = viewClasses.get(name);
//		if (viewClass != null) {
//			FXMLView view;
//			Node     node;
//			try {
//				view = viewClass.newInstance();
//				node = view.getView();
//				views.put(node, view);
//			} catch (InstantiationException | IllegalAccessException e) {
//				LOG.error("Error creating new instance of view: " + name);
//				throw new RuntimeException("Error creating new instance of view: " + name, e);
//			}
//			
//			return node;
//		}
//		
//		LOG.error("Unknown view: " + name);
//		throw new IllegalArgumentException("Unknown view: " + name);
//	}
//	
//	protected FXMLView getView(Node node) {
//		return views.get(node);
//	}
//}
