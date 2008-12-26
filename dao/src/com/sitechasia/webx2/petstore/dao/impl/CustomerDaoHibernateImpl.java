/**
 * @{#} CustomerDaoHibernateImpl.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao.impl;

import com.sitechasia.webx.core.dao.hibernate3.BaseHibernateDomainDao;
import com.sitechasia.webx2.petstore.dao.CustomerDao;
import com.sitechasia.webx2.petstore.model.Account;

/**
 * Customer DAO 接口实现类.
 * 
 * @see CustomerDao
 * 
 * @version 1.0
 * @since JDK1.5
 */
public class CustomerDaoHibernateImpl extends BaseHibernateDomainDao<Account>
		implements CustomerDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.CustomerDao#getAccount(java.lang.String)
	 */
	public Account getAccount(String accountId) {
		return get(accountId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.CustomerDao#saveAccount(mypetstore.model.Account)
	 */
	public void saveAccount(Account account) {
		save(account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.CustomerDao#updateAccount(mypetstore.model.Account)
	 */
	public void updateAccount(Account account) {
		update(account);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.CustomerDao#deleteAccount(mypetstore.model.Account)
	 */
	public void deleteAccount(Account account) {
		delete(account);
	}
}
