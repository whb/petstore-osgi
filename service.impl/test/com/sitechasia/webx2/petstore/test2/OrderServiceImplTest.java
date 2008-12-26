package com.sitechasia.webx2.petstore.test2;

import junit.framework.TestCase;
import java.util.Date;
import mypetstore.exception.MyPetStoreException;
import mypetstore.service.OrderService;
import mypetstore.web.vo.OrderVo;
import org.springframework.context.ApplicationContext;

/**
 * testing OrderService
 * 
 * 
 * @see OrderServiceImpl
 */
public class OrderServiceImplTest extends
		com.sitechasia.webx.test.AbstractTestCase {

	ApplicationContext beanFactory = null;

	OrderService orderService;

	public void setUp() {
		super.setUp();
		beanFactory = MyPetStoreBeanFactory2.getApplicationContext();
		orderService = (OrderService) beanFactory.getBean("orderService");
	}

	/**
	 * testing saveOrder()
	 * 
	 * @throws MyPetStoreException
	 */

	public void testSaveOrder() throws MyPetStoreException {
		OrderVo order = new OrderVo();
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
		//
		orderService.saveOrder(order);
	}

	/**
	 * testing getOrder()
	 * 
	 * @throws MyPetStoreException
	 */

	public void testGetOrder() throws MyPetStoreException {
		String orderId = "1001";
		OrderVo order = orderService.getOrder(orderId);
		assertEquals(order.getUsername(), "order");
	}
}
