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
//import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
//import org.springframework.beans.factory.NoSuchBeanDefinitionException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.AbstractApplicationContext;
//
//import java.util.Collection;
//import java.util.Map;
//
//public interface ContextLocatorInterface {
//
//	/**
//	 * If this method is invoked before a create...Context() method is called,
//	 * then give back a dummy "minimal" context.  As soon as a create method is
//	 * called or a specific context has been assigned through setPrimaryContext(),
//	 * this method should return that one.  If this method is called a number of
//	 * times without a real context created, it keeps returning the same dummy
//	 * one.
//	 */
//	ApplicationContext getPrimaryContext();
//
//	/*
//	 * Meant to be called by standalone apps.
//	 */
//	AbstractApplicationContext createClassPathXmlApplicationContext(String... paths);
//
//	/*
//	 * Meant to be called by webapps.
//	 */
//	void setPrimaryContext(ApplicationContext appContext);
//
//	/**
//	 * Even if Spring didn't instantiate the bean, it can be convinced to autowire it...
//	 */
//	<T> T autowireBean(T beanInstance);
//
//	/**
//	 * Get the singleton bean with the given class from the primary Spring context.
//	 *
//	 * @throws NoSuchBeanDefinitionException
//	 * 		if there is not exactly one matching bean found
//	 */
//	<T> T getBean(Class<T> beanClazz);
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
//	<T> T getBean(String beanId, Class<T> beanClazz);
//
//	/**
//	 * Get the bean with the given ID from the primary Spring context.  If it
//	 * isn't of the given class an exception is thrown.  Set the given properties
//	 * on the new object.
//	 *
//	 * @see ApplicationContext.getBean(String,Class<?>)
//	 */
//	<T> T getBean(String beanId, Class<T> beanClazz, Map<String, Object> properties);
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
//	<T> T getBeanOfType(Class<T> beanClass, String beanName, Object... args);
//
//	/**
//	 * Returns the bean instances that match the given class.
//	 *
//	 * @param beanClass
//	 * 		the class or interface to match
//	 * @return null if no matching beans are found
//	 * @see ListableBeanFactory.getBeansOfType(Class<?>)
//	 */
//	<T> Collection<T> getBeansOfType(Class<T> beanClass);
//
//	/**
//	 * Returns a collection of bean classes that match the given class
//	 *
//	 * @param beanClass
//	 * 		the class or interface to match
//	 * @return null if no matching beans are found
//	 */
//	<T> Collection<Class<T>> getBeanClassesOfType(Class<T> beanClass);
//
//}