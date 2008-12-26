/**
 * @{#} ProductDaoHibernateImplTest.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.test2;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import com.sitechasia.webx2.petstore.dao.ProductDao;
import com.sitechasia.webx2.petstore.model.Product;
import org.springframework.context.ApplicationContext;

/**
 * testing ProductDaoHibernateImpl
 * 
 * @author zhou wei
 * 
 * @see ProductDaoHibernateImpl
 */
public class ProductDaoHibernateImplTest extends
		com.sitechasia.webx.test.AbstractTestCase {

	ApplicationContext beanFactory = null;

	ProductDao productDao = null;

	public void setUp() {
		super.setUp();
		beanFactory = MyPetStoreBeanFactory2.getApplicationContext();
		productDao = (ProductDao) beanFactory.getBean("productDao");
	}

	/**
	 * 测试根据Category主键获得Product结果集 ：
	 * 
	 * 
	 */

	public void testGetProductListByCategory() {
		String categoryId = "DOGS";
		List list = productDao.getProductListByCategory(categoryId);
		assertEquals(list.size(), 6);
	}

	/**
	 * 测试根据主键获得一个Product对象 ：
	 * 
	 * 
	 */
	public void testGetProduct() {
		String productId = "K9-BD-01";
		Product product = productDao.getProduct(productId);
		assertEquals(product.getName(), "Bulldog");
	}

	/**
	 * 测试根据关键字获得Product结果集 ：
	 * 
	 * 
	 */
	public void testSearchProductList() {
		List<String> keywords = new ArrayList<String>();
		keywords.add("DOGS");
		List list = productDao.searchProductList(keywords);
		assertEquals(list.size(), 6);
	}

}
