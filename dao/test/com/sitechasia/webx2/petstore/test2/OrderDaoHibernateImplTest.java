/**
 * @{#} OrderDaoHibernateImplTest.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.test2;

import java.util.Date;

import junit.framework.TestCase;
import com.sitechasia.webx2.petstore.dao.OrderDao;
import com.sitechasia.webx2.petstore.model.Order;
import org.springframework.context.ApplicationContext;

/**
 * testing OrderDaoHibernateImpl
 * 
 * @author zhou wei
 * @see OrderDaoHibernateImpl
 */
public class OrderDaoHibernateImplTest extends
		com.sitechasia.webx.test.AbstractTestCase {

	ApplicationContext beanFactory = null;

	OrderDao orderDao = null;

	public void setUp() {
		super.setUp();
		beanFactory = MyPetStoreBeanFactory2.getApplicationContext();
		orderDao = (OrderDao) beanFactory.getBean("orderDao");
	}

	/**
	 * 测试保存一个Order对象 ：
	 * 
	 * 
	 */

	public void testSaveOrder() {
		Order order = new Order();

		order.setStatus("status");
		order.setTotalPrice(3.33);
		order.setUsername("order");
		order.setBillAddress1("a");
		order.setBillAddress2("b");
		order.setBillCity("bj");
		order.setBillCountry("zh");
		order.setBillEmail("ss@shou.com");
		order.setBillPhone("22222222");
		order.setBillState("good");
		order.setBillToFirstName("bill");
		order.setBillToLastName("last");
		order.setBillZip("bill.zip");
		order.setCardType("china");
		order.setCourier("courier");
		order.setCreditCard("cred");
		order.setExpiryDate("date");
		order.setLocale("local");
		order.setOrderDate(new Date());
		order.setShipAddress1("ss");
		order.setShipAddress2("aa");
		order.setShipCity("city");
		order.setShipCountry("scourtry");
		order.setShipEmail("semail");
		order.setShipPhone("shipphone");
		order.setShipState("shipstate");
		order.setShipToFirstName("shipfirstname");
		order.setShipToLastName("shiplastname");
		order.setShipZip("aa");

		orderDao.saveOrder(order);
	}

	/**
	 * 测试根据主键获得一个Order对象 ：
	 * 
	 * 
	 */

	public void testGetOrder() {
		String orderId = "1001";
		Order order = orderDao.getOrder(orderId);
		assertEquals(order.getUsername(), "order");
	}

	public void testGetOrdersByAccountId() {
		String orderId = "1001";
		Order order = orderDao.getOrder(orderId);
		assertEquals(order.getUsername(), "order");
	}
}
