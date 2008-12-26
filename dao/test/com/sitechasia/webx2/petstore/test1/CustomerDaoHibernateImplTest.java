/**
 * @{#} CustomerDaoHibernateImplTest.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.test1;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;
import com.sitechasia.webx2.petstore.dao.CustomerDao;
import com.sitechasia.webx2.petstore.dao.impl.CustomerDaoHibernateImpl;
import com.sitechasia.webx2.petstore.model.Account;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.context.ApplicationContext;

/**
 * testing CustomerDaoHibernateImpl
 * 
 * @author zhou wei
 * @see CustomerDaoHibernateImpl
 */
public class CustomerDaoHibernateImplTest extends TestCase {

	ApplicationContext beanFactory = null;

	CustomerDao customerDao;

	static String accountIdForSave = "j2ee " + System.currentTimeMillis();

	public void setUp() throws Exception {

		beanFactory = MyPetStoreBeanFactory.getApplicationContext();
		customerDao = (CustomerDao) beanFactory.getBean("customerDao");
	}

	/**
	 * 测试根据主键获得一个Account对象
	 * 
	 * 
	 * @see
	 */

	public void testGetAccount() {
		String accountId = "j2ee";
		Account account = customerDao.getAccount(accountId);
		assertEquals(account.getEmail(), "yourname@yourdomain.com");
	}

	/**
	 * 测试保存一个Account对象
	 * 
	 * 
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @see
	 */

	public void testSaveAccount() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String accountId = "j2ee";

		Account account = customerDao.getAccount(accountId);

		Account accountnew = new Account();

		PropertyUtils.copyProperties(accountnew, account);

		accountnew.setUsername(accountIdForSave);

		customerDao.saveAccount(accountnew);

		account = customerDao.getAccount(accountIdForSave);

		assertEquals(accountnew.getEmail(), "yourname@yourdomain.com");

	}

	/**
	 * 测试更新一个Account对象
	 * 
	 * 
	 * @see
	 */

	public void testUpdateAccount() {

		String accountId = "j2ee";

		String emailUpdateAfter = "newyourname@yourdomain.com";

		Account account = customerDao.getAccount(accountId);

		account.setEmail(emailUpdateAfter);

		customerDao.updateAccount(account);

		account = customerDao.getAccount(accountId);

		assertEquals(account.getEmail(), emailUpdateAfter);

	}

	/**
	 * 测试删除一个Account对象
	 * 
	 * 
	 * @see
	 */

	public void testDeleteAccount() {
		Account account = customerDao.getAccount(accountIdForSave);
		customerDao.deleteAccount(account);
		account = customerDao.getAccount(accountIdForSave);
		assertEquals(account, null);
	}

}
