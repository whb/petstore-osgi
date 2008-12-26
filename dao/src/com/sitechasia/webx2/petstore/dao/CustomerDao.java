/**
 * @{#} CustomerDao.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao;

import com.sitechasia.webx2.petstore.model.Account;

/**
 * Customer DAO 接口.
 * <p>
 * 该DAO包含Account相关的数据访问逻辑
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
public interface CustomerDao {
	/**
	 * 根据Account主键获得Account
	 * 
	 * @param accountId
	 *            Account主键
	 * @return Account
	 */
	public Account getAccount(String accountId);

	/**
	 * 
	 * 保存Account
	 * 
	 * @param account
	 */
	public void saveAccount(Account account);

	/**
	 * 
	 * 更新Account
	 * 
	 * @param account
	 */
	public void updateAccount(Account account);

	/**
	 * 
	 * 删除Account
	 * 
	 * @param account
	 */
	public void deleteAccount(Account account);
}
