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
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
//import org.springframework.beans.factory.NoSuchBeanDefinitionException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//public abstract class BasicSpringContextLocator implements ContextLocatorInterface {
//
//	private       ApplicationContext firstContext = null;
//	private       ApplicationContext dummyContext = null;
//	private final Object             mutex        = new Object();
//
//	public BasicSpringContextLocator() {
//		super();
//	}
//
//	/**
//	 * If this method is invoked before a create...Context() or setPrimaryContext()
//	 * method is called, then give back a dummy "minimal" context.  As soon as a
//	 * create method is called or a specific context has been assigned through
//	 * setPrimaryContext(), this method should return that one.  If this method
//	 * is called a number of times without a real context created, it keeps returning
//	 * the same dummy one.
//	 */
//	public ApplicationContext getPrimaryContext() {
//		synchronized (mutex) {
//			if (firstContext != null) {
//				return firstContext;
//			}
//
//			if (dummyContext == null) {
//				dummyContext = new ClassPathXmlApplicationContext("appContext-minimal.xml");
//			}
//			return dummyContext;
//		}
//	}
//
//	public AbstractApplicationContext createClassPathXmlApplicationContext(String... paths) {
//		synchronized (mutex) {
//			AbstractApplicationContext newContext = new ClassPathXmlApplicationContext(paths);
//			firstContext = newContext;
//			return newContext;
//		}
//	}
//
//	public void setPrimaryContext(ApplicationContext appContext) {
//		synchronized (mutex) {
//			firstContext = appContext;
//		}
//	}
//
//	/**
//	 * Even if Spring didn't instantiate the bean, it can be convinced to autowire it...
//	 */
//	public <T> T autowireBean(T beanInstance) {
//		getPrimaryContext().getAutowireCapableBeanFactory().autowireBean(beanInstance);
//		return beanInstance;
//	}
//
//	/**
//	 * Get the singleton bean with the given class from the primary Spring context.
//	 *
//	 * @throws NoSuchBeanDefinitionException
//	 * 		if there is not exactly one matching bean found
//	 */
//	public <T> T getBean(Class<T> beanClazz) {
//
//		return getPrimaryContext().getBean(beanClazz);
//	}
//
//	/**
//	 * Get the bean with the given ID from the primary Spring context.
//	 *
//	 * @throws NoSuchBeanDefinitionException
//	 * 		if there is not exactly one matching bean found
//	 * @throws BeanNotOfRequiredTypeException
//	 * 		if the bean exists but isn't of the given class
//	 * @see ApplicationContext.getBean(String,Class<?>)
//	 */
//	public <T> T getBean(String beanId, Class<T> beanClazz) {
//		return getPrimaryContext().getBean(beanId, beanClazz);
//	}
//
//	/**
//	 * If you know the name and type (supertype) of a "prototype" bean defined
//	 * in a Spring context, and its constructor requires arguments, then this
//	 * is the method to call to instantiate it.
//	 *
//	 * @param beanClass
//	 * 		- a class, normally an interface, that the bean implements
//	 * @param beanName
//	 * 		- the name as defined in the "id" or "name" attribute of the bean
//	 * @param args
//	 * 		- any arguments needed by the constructor
//	 * @return null if the bean could not be instantiated
//	 */
//	public <T> T getBeanOfType(Class<T> beanClass, String beanName, Object... args) {
//		return getBeanOfType(getPrimaryContext(), beanClass, beanName, args);
//	}
//
//	@SuppressWarnings("unchecked")
//	public <T> T getBeanOfType(ApplicationContext context, Class<T> beanClass, String beanName, Object... args) {
//		try {
//			return (T) getPrimaryContext().getBean(beanName, args);
//		} catch (NoSuchBeanDefinitionException e) {
//			getLogger().error("Expected " + beanClass.getSimpleName() + " object not found in the application context", e);
//		} catch (BeanNotOfRequiredTypeException e) {
//			getLogger().error("Object " + beanName + " in the application context must implement " + beanClass.getSimpleName(), e);
//		} catch (Exception e) {
//			getLogger().error("Failed to instantiate object named '" + beanName + "'", e);
//		}
//
//		return null;
//	}
//
//	/**
//	 * Get the bean with the given ID from the primary Spring context.  If it
//	 * isn't of the given class an exception is thrown.  Set the given properties
//	 * on the new object.
//	 *
//	 * @see ApplicationContext.getBean(String,Class<?>)
//	 */
//	public <T> T getBean(String beanId, Class<T> beanClazz, Map<String, Object> properties) {
//		T object = getBean(beanId, beanClazz);
//		for (Entry<String, Object> entry : properties.entrySet()) {
//			try {
//				BeanUtils.setProperty(object, entry.getKey(), entry.getUnit());
//			} catch (Exception e) {
//				/*
//				 * In the Spring manner, wrap any checked exceptions thrown by
//				 * trying to set the property into a runtime exception.  There
//				 * is unlikely to be any useful cleanup activity anyway.
//				 */
//				throw new IllegalArgumentException(e);
//			}
//		}
//		return object;
//	}
//
//	@Override
//	public <T> Collection<T> getBeansOfType(Class<T> beanClass) {
//		Map<String, T> beans = getPrimaryContext().getBeansOfType(beanClass);
//		if (beans == null || beans.isEmpty()) {
//			return null;
//		}
//
//		return beans.values();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public <T> Collection<Class<T>> getBeanClassesOfType(Class<T> beanClass) {
//		String[] beanNames = getPrimaryContext().getBeanNamesForType(beanClass);
//		if (beanNames == null || beanNames.length == 0) {
//			return null;
//		}
//
//		List<Class<T>> classes = new ArrayList<Class<T>>();
//
//		for (String beanName : beanNames) {
//			classes.add((Class<T>) getPrimaryContext().getType(beanName));
//		}
//
//		return classes;
//	}
//
//	/**
//	 * It's tempting to declare a private static final Logger instance and initialize
//	 * it at the top of this class, but that would introduce a circular dependency.
//	 */
//	private static Logger getLogger() {
//		return LoggerFactory.getLogger(SpringContextLocator.class.getName());
//	}
//}