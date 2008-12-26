/**
 * @{#} ProductDao.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao;

import java.util.List;

import com.sitechasia.webx2.petstore.model.Product;

/**
 * Product DAO 接口.
 * <p>
 * 该DAO包含Product相关的数据访问逻辑
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
public interface ProductDao {

	/**
	 * 根据Product主键获得Product
	 * 
	 * @param productId
	 *            Product主键
	 * @return Product
	 */
	public Product getProduct(String productId);

	/**
	 * 根据关键字获得Product结果集
	 * 
	 * @param keywords
	 *            查询关键字
	 * @return List<Product>
	 */
	public List searchProductList(List keywords);

	/**
	 * 根据Category主键获得Product结果集
	 * 
	 * @param categoryId
	 *            Category主键
	 * @return List<Product>
	 */
	public List getProductListByCategory(String categoryId);
}
