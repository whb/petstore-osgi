/**
 * @{#} CatalogDao.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao;

import java.util.List;

import com.sitechasia.webx2.petstore.model.Category;

/**
 * Catalog DAO 接口.
 * <p>
 * 该DAO包含Category相关的数据访问逻辑
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
public interface CatalogDao {

	/**
	 * 
	 * 获得所有Category的结果集
	 * 
	 * @return List<Category>
	 */
	public List getCategoryList();

	/**
	 * 
	 * 根据Category主键获得Category
	 * 
	 * @param categoryId
	 *            Category主键
	 * @return Category
	 */
	public Category getCategory(String categoryId);

	/**
	 * 
	 * 方法说明：向数据库保存一条记录category
	 * 
	 * @author Sunzhenying
	 * @param category
	 * @see
	 */
	public void saveCategory(Category category);

	/**
	 * 从数据库删除一条记录
	 * 
	 * @param categoryId
	 */
	public void deleteCategoryById(String categoryId);

	/**
	 * 向数据库更新一条记录
	 * 
	 * @param category
	 */
	public void doSave(Category category);
}
