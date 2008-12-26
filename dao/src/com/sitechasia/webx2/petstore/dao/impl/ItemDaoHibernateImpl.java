/**
 * @{#} ItemDaoHibernateImpl.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao.impl;

import java.util.List;

import com.sitechasia.webx2.petstore.dao.ItemDao;
import com.sitechasia.webx2.petstore.model.Item;
import com.sitechasia.webx.core.dao.hibernate3.BaseHibernateDomainDao;

/**
 * Item DAO 接口实现类.
 * 
 * @see ItemDao
 * 
 * @version 1.0
 * @since JDK1.5
 */
public class ItemDaoHibernateImpl extends BaseHibernateDomainDao<Item>
		implements ItemDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.ItemDao#getItemListByProduct(java.lang.String)
	 */
	public List getItemListByProduct(final String productId) {
		return listByField("product.productId", productId, 0, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.ItemDao#getItem(java.lang.String)
	 */
	public Item getItem(String itemId) {
		return get(itemId);
	}
}
