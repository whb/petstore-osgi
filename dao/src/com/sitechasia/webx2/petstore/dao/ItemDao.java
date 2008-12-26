/**
 * @{#} ItemDao.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao;

import java.util.List;

import com.sitechasia.webx2.petstore.model.Item;

/**
 * Item DAO 接口.
 * <p>
 * 该DAO包含Item相关的数据访问逻辑
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
public interface ItemDao {

	/**
	 * 
	 * 根据Product主键获得Item结果集
	 * 
	 * @param productId
	 *            Product主键
	 * @return List<Item>
	 */
	public List getItemListByProduct(String productId);

	/**
	 * 
	 * 根据Item主键获得Item
	 * 
	 * @param itemId
	 *            Item主键
	 * @return
	 */
	public Item getItem(String itemId);
}
