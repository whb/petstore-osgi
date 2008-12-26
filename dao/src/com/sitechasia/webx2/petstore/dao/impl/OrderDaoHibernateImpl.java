/**
 * @{#} OrderDaoHibernateImpl.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao.impl;

import java.util.List;

import com.sitechasia.webx2.petstore.dao.OrderDao;
import com.sitechasia.webx2.petstore.dao.SequenceDao;
import com.sitechasia.webx2.petstore.model.Order;
import com.sitechasia.webx.core.dao.hibernate3.BaseHibernateDomainDao;

/**
 * Order DAO 接口实现类.
 * 
 * @see OrderDao
 * 
 * @version 1.0
 * @since JDK1.5
 */
public class OrderDaoHibernateImpl extends BaseHibernateDomainDao<Order>
		implements OrderDao {
	private static final String ORDER_SEQUENCE_NAME = "orderSeq";

	// 注册SequenceDao
	private SequenceDao sequenceDao;

	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.OrderDao#saveOrder(mypetstore.model.Order)
	 */
	public void saveOrder(Order order) {

		// 获得order主键
		int orderId = sequenceDao.getSeqNum(ORDER_SEQUENCE_NAME);
		order.setOrderId(orderId);
		save(order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.OrderDao#getOrder(java.lang.String)
	 */
	public Order getOrder(String orderId) {
		return get(Integer.parseInt(orderId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.OrderDao#getOrdersByAccountId(java.lang.String)
	 */
	public List getOrdersByAccountId(String accountId) {
		return listByField("username", accountId, 0, 0);
	}
}
