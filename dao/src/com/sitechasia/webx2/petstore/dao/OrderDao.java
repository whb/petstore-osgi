/**
 * @{#} OrderDao.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao;

import java.util.List;

import com.sitechasia.webx2.petstore.model.Order;

/**
 * Order DAO 接口.
 * <p>
 * 该DAO包含Order相关的数据访问逻辑
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
public interface OrderDao {
	/**
	 * 
	 * 保存Order
	 * 
	 * @param order
	 */
	public void saveOrder(Order order);

	/**
	 * 根据Order主键获得Order
	 * 
	 * @param orderId
	 *            Order主键
	 * @return Order
	 */
	public Order getOrder(String orderId);

	/**
	 * 
	 * 根据人员查Order 根据Account主键获得Order结果集
	 * 
	 * 
	 * @param accountId
	 *            Account主键
	 * @return
	 */
	public List getOrdersByAccountId(String accountId);
}
