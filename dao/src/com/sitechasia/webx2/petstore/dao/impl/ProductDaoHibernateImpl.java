/**
 * @{#} ProductDaoHibernateImpl.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao.impl;

import java.util.ArrayList;
import java.util.List;
import com.sitechasia.webx.core.dao.hibernate3.BaseHibernateDomainDao;
import com.sitechasia.webx.core.dao.hibernate3.CommonHibernateDao;
import com.sitechasia.webx.core.dao.jdbc.CommonJdbcDao;
import com.sitechasia.webx2.petstore.dao.ProductDao;
import com.sitechasia.webx2.petstore.model.Product;

/**
 * Product DAO 接口实现类.
 * 
 * @see ProductDao
 * 
 * @version 1.0
 * @since JDK1.5
 */
public class ProductDaoHibernateImpl extends BaseHibernateDomainDao<Product>
		implements ProductDao {

	CommonHibernateDao commonHibernateDao;
	CommonJdbcDao commonJdbcDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.ProductDao#getProductListByCategory(java.lang.String)
	 */
	public List getProductListByCategory(final String categoryId) {
		/**
		 * 本身我们使用的是return listByField("categoryId", categoryId, 0,
		 * 0);为了对开发者提供commonHibernateDao的范例，我们做了相应的调整。
		 */
		// 调整前的代码： return listByField("categoryId", categoryId, 0, 0);
		//
		// 调整后的代码：
		//
		String hql = "from Product where categoryId=?";
		return commonHibernateDao.listByHql(hql, 0, 0, categoryId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.ProductDao#getProduct(java.lang.String)
	 */
	public Product getProduct(String productId) {
		return get(productId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.ProductDao#searchProductList(java.util.List)
	 */
	public List searchProductList(final List keywords) {
		List list = new ArrayList();

		/**
		 * 本身我们使用的是hibernate查询;为了对开发者提供commonJdbcDao的jdbc查询范例，我们做了相应的调整。
		 */

		// 调整前的代码： hibernate查询
		// if (keywords != null && !keywords.isEmpty()) {
		//
		// StringBuffer sb = new StringBuffer(100);
		// sb.append("select distinct product ");
		// sb.append("from Product product ");
		// sb.append("where ");
		//
		// for (int i = 0; i < keywords.size(); i++) {
		// String keyword = (String) keywords.get(i);
		// sb.append("product.name like ");
		// sb.append("'%").append(keyword).append("%' ");
		// sb.append("OR ");
		// }
		//
		// for (int i = 0; i < keywords.size(); i++) {
		// String keyword = (String) keywords.get(i);
		// sb.append("product.categoryId like ");
		// sb.append("'%").append(keyword).append("%' ");
		// sb.append("OR ");
		// }
		//
		// for (int i = 0; i < keywords.size(); i++) {
		// String keyword = (String) keywords.get(i);
		// sb.append("product.descriptionWithImage like ");
		// sb.append("'%").append(keyword).append("%' ");
		// sb.append("OR ");
		// //sb.append((i < keywords.size() - 1)?"sb.append(\"OR \")":"");
		// }
		// sb.delete(sb.lastIndexOf("OR "), sb.length());
		// sb.append(" order by product.productId");
		// list = listByQL(sb.toString(), 0, 0);
		//
		//
		// 调整后的代码：Jdbc查询
		if (keywords != null && !keywords.isEmpty()) {

			StringBuffer sb = new StringBuffer(100);
			sb.append("select distinct * ");
			sb.append("from Product product ");
			sb.append("where ");

			for (int i = 0; i < keywords.size(); i++) {
				String keyword = (String) keywords.get(i);
				sb.append("product.name like ");
				sb.append("'%").append(keyword).append("%' ");
				sb.append("OR ");
			}

			for (int i = 0; i < keywords.size(); i++) {
				String keyword = (String) keywords.get(i);
				sb.append("product.CATEGORY like ");
				sb.append("'%").append(keyword).append("%' ");
				sb.append("OR ");
			}

			for (int i = 0; i < keywords.size(); i++) {
				String keyword = (String) keywords.get(i);
				sb.append("product.DESCN like ");
				sb.append("'%").append(keyword).append("%' ");
				sb.append("OR ");
				// sb.append((i < keywords.size() - 1)?"sb.append(\"OR \")":"");
			}
			sb.delete(sb.lastIndexOf("OR "), sb.length());
			sb.append(" order by product.productId");
			list = commonJdbcDao.listBySql(sb.toString());

		}

		return list;
	}

	/**
	 * 
	 * 注册 CommonHibernateDao
	 * 
	 * @param commonHibernateDao
	 */
	public void setCommonHibernateDao(CommonHibernateDao commonHibernateDao) {
		this.commonHibernateDao = commonHibernateDao;
	}

	/**
	 * 
	 * 注册 CommonJdbcDao
	 * 
	 * @param commonJdbcDao
	 */
	public void setCommonJdbcDao(CommonJdbcDao commonJdbcDao) {
		this.commonJdbcDao = commonJdbcDao;
	}

}
