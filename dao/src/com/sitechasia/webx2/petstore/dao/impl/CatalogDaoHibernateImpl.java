/**
 * @{#} CatalogDaoHibernateImpl.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao.impl;

import java.util.List;

import com.sitechasia.webx.core.dao.hibernate3.BaseHibernateDomainDao;
import com.sitechasia.webx2.petstore.dao.CatalogDao;
import com.sitechasia.webx2.petstore.model.Category;

/**
 * Catalog DAO 接口实现类.
 * 
 * @see CatalogDao
 * 
 * @version 1.0
 * @since JDK1.5
 */
public class CatalogDaoHibernateImpl extends BaseHibernateDomainDao<Category>
		implements CatalogDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.CatalogDao#getCategoryList()
	 */
	public List getCategoryList() {
		return listAll(0, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.CatalogDao#getCategory(java.lang.String)
	 */
	public Category getCategory(String categoryId) {

		return get(categoryId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.CatalogDao#saveCategory(java.lang.String)
	 */
	public void saveCategory(Category category) {
		this.save(category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.CatalogDao#deleteCategoryById(java.lang.String)
	 */
	public void deleteCategoryById(String categoryId) {
		this.deleteById(categoryId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.CatalogDao#doSave(Category category)
	 */
	public void doSave(Category category) {
		getHibernateTemplate().saveOrUpdate(category);
	}
}
