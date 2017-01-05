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
//package com.abopu.recipebook.web.spring;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Enumeration;
//
//public class SpringContextLocator extends BasicSpringContextLocator implements ContextLocatorInterface {
//	private static final String IMPL_SELECTION_PROPERTY = "SpringContextLocator";
//	private static final String DEFAULT_RESOURCE_FILE   = "context-configuration-default.properties";
//	private static final String STANDALONE_RESOURCE_FILE = "context-configuration-standalone.properties";
//	private static final String CUSTOM_RESOURCE_FILE    = "context-configuration-overrides.properties";
//
//	private static final ContextLocatorInterface instance = chooseImplementation();
//
//	private static ContextLocatorInterface chooseImplementation() {
//		String alternateImpl = System.getProperty(IMPL_SELECTION_PROPERTY);
//		if (alternateImpl != null && !alternateImpl.isEmpty()) {
//			try {
//				Class<? extends ContextLocatorInterface> clazz = Class.forName(alternateImpl).asSubclass(ContextLocatorInterface.class);
//				return clazz.newInstance();
//			} catch (Exception e) {
//				System.err.println("Instantiating alternate ContextLocatorInterface: " + e);
//				throw new RuntimeException(e);
//			}
//		}
//		
//		return new SpringContextLocator();
//	}
//
//	public synchronized static ContextLocatorInterface getInstance() {
//		return instance;
//	}
//
//	private SpringContextLocator() {
//		super();
//		loadContextProperties();
//	}
//
//	/**
//	 * Read the set of predefined resource files to pick up properties that
//	 * should be loaded into the system property set.  These are then available
//	 * to Spring context files using the ${} placeholder notation, and to SpEL
//	 * (Spring Expression Language) statements as
//	 *
//	 * #{ systemProperties['property_name'] }
//	 */
//	private void loadContextProperties() {
//		/*
//		 * Set properties defined in context-configuration-*.properties into the
//		 * System properties set, so that they become available to all the ${}
//		 * placeholders in the Spring configurations we load later.
//		 * 
//		 * First bring in context-configuration-defaults.properties, then the
//		 * environment-specific file, and finally the -overrides.properties file.
//		 */
//
//		java.util.Properties contextProps = new java.util.Properties();
//		loadProps(contextProps, DEFAULT_RESOURCE_FILE, false);
//		loadProps(contextProps, STANDALONE_RESOURCE_FILE, false);
//		loadProps(contextProps, CUSTOM_RESOURCE_FILE, true);    // true means to ignore its absence
//
//		/*
//		 * Copy all the loaded properties onto the System properties in order
//		 * to make them available to Spring context files.
//		 */
//		Enumeration<?> propNames = contextProps.propertyNames();
//		while (propNames.hasMoreElements()) {
//			String propName = (String) propNames.nextElement();
//
//			if (System.getProperty(propName) == null) {
//				System.setProperty(propName, contextProps.getProperty(propName));
//			}
//		}
//	}
//
//	private static void loadProps(java.util.Properties targetProps, String resourceName, boolean quiet) {
//		InputStream is = SpringContextLocator.class.getClassLoader().getResourceAsStream(resourceName);
//		if (is != null) {
//			try {
//				// Property loading is additive, so just layer each successive
//				// file into the same set.
//				targetProps.load(is);
//			} catch (IOException e) {
//				if (!quiet) {
//					System.err.println("WARNING: Failed to load resource " + resourceName);
//				}
//			}
//		}
//	}
//}
