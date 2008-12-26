/**
 * @{#} ItemDaoHibernateImplTest.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.test2;

import java.util.List;

import junit.framework.TestCase;
import com.sitechasia.webx2.petstore.dao.ItemDao;
import com.sitechasia.webx2.petstore.dao.impl.ItemDaoHibernateImpl;
import com.sitechasia.webx2.petstore.model.Item;
import org.springframework.context.ApplicationContext;

/**
 * testing ItemDaoHibernateImpl
 * 
 * @author zhou wei
 * @see ItemDaoHibernateImpl
 */
public class ItemDaoHibernateImplTest extends
		com.sitechasia.webx.test.AbstractTestCase {

	ApplicationContext beanFactory = null;

	ItemDao itemDao = null;

	public void setUp() {
		super.setUp();
		beanFactory = MyPetStoreBeanFactory2.getApplicationContext();
		itemDao = (ItemDao) beanFactory.getBean("itemDao");
	}

	/**
	 * 测试根据Product主键获得Item结果集 ：
	 * 
	 */
	public void testGetItemListByProduct() {
		String productId = "K9-BD-01";

		List list = itemDao.getItemListByProduct(productId);

		for (int i = 0; i < list.size(); i++) {
			Item item = (Item) list.get(i);

			if (item.getItemId().equalsIgnoreCase("EST-6")) {
				assertEquals(item.getListPrice(), 18.5d, 18.5d);
			}
		}
	}

	/**
	 * 测试根据主键获得Item对象 ：
	 * 
	 */

	public void testGetItem() {
		String itemId = "EST-6";
		Item item = itemDao.getItem(itemId);
		assertEquals(item.getListPrice(), 18.5d, 18.5d);
	}

}
