/**
 * @{#} CatalogDaoHibernateImplTest.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.test1;

import java.util.List;
import junit.framework.TestCase;
import com.sitechasia.webx2.petstore.dao.CatalogDao;
import com.sitechasia.webx2.petstore.dao.impl.CatalogDaoHibernateImpl;
import com.sitechasia.webx2.petstore.model.Category;
import org.springframework.context.ApplicationContext;

/**
 * testing CatalogDaoHibernateImpl
 * 
 * @author zhou wei
 * @see CatalogDaoHibernateImpl
 */
public class CatalogDaoHibernateImplTest extends TestCase {

	ApplicationContext beanFactory = null;

	CatalogDao catalogDao = null;

	public void setUp() throws Exception {

		beanFactory = MyPetStoreBeanFactory.getApplicationContext();
		catalogDao = (CatalogDao) beanFactory.getBean("catalogDao");
	}

	/**
	 * 测试获得所有Catagory的结果集
	 * 
	 * 
	 */

	public void testGetCategoryList() {
		List list = catalogDao.getCategoryList();
		for (int i = 0; i < list.size(); i++) {
			Category category = (Category) list.get(i);
			assertEquals(category.getCategoryId().toLowerCase(), category
					.getName().toLowerCase());
		}
	}

	/**
	 * 测试根据主键获得Category对象
	 * 
	 * 
	 */

	public void testGetCategory() {
		String categoryId = "DOGS";
		Category category = catalogDao.getCategory(categoryId);
		assertEquals(category.getName(), "Dogs");
	}

	/**
	 * 测试save Category对象
	 * 
	 * 
	 */

	public void testSaveCategory() {
		String categoryId = "DOGS";
		Category category = catalogDao.getCategory(categoryId);

		String categoryId_new = categoryId + "01";
		category.setCategoryId(categoryId_new);
		catalogDao.saveCategory(category);
		category = catalogDao.getCategory(categoryId_new);
		assertEquals(category.getCategoryId(), categoryId_new);
	}

	/**
	 * 测试delete Category对象
	 * 
	 * 
	 */

	public void testDeleteCategoryById() {
		String categoryId = "DOGS" + "01";
		catalogDao.deleteCategoryById(categoryId);
		Category category = catalogDao.getCategory(categoryId);
		assertEquals(category, null);
	}

}
