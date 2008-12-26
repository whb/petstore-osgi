package com.sitechasia.webx2.petstore.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sitechasia.webx2.petstore.model.Account;
import mypetstore.web.vo.AccountVo;
import com.sitechasia.webx2.petstore.dao.CustomerDao;
import com.sitechasia.webx2.petstore.utils.DozerConvertUtil;
import mypetstore.exception.MyPetStoreException;
import mypetstore.service.CustomerService;

/**
 * Customer Service 接口实现类.
 * 
 * @see CustomerService
 * 
 * @version 1.0
 * @since JDK1.5
 */
public class CustomerServiceImpl implements CustomerService {
	// the logger for this class
	private Log logger = LogFactory.getLog(this.getClass());

	private CustomerDao customerDao;

	private DozerConvertUtil dozerConvertUtil;

	// 注册CustomerDao
	public void setCustomerDao(CustomerDao newCustomerDao) {
		this.customerDao = newCustomerDao;
	}

	// 注册dozer DO VO 转换器
	public void setDozerConvertUtil(DozerConvertUtil dozerConvertUtil) {
		this.dozerConvertUtil = dozerConvertUtil;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CustomerService#getAccount(java.lang.String)
	 */
	public AccountVo getAccount(String accountId) throws MyPetStoreException {
		try {
			AccountVo accountVo = new AccountVo();
			dozerConvertUtil.domainObjectToViewObject(this.customerDao
					.getAccount(accountId), accountVo);
			return accountVo;
		} catch (Exception e) {
			String msg = "Could not get account " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CustomerService#signon(java.lang.String,
	 *      java.lang.String)
	 */
	public AccountVo signon(String username, String password)
			throws MyPetStoreException {
		AccountVo account = this.getAccount(username);

		if (account != null) {
			if (password.equals(account.getPassword())) {
				return account;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CustomerService#saveAccount(mypetstore.model.Account)
	 */
	public void saveAccount(AccountVo account) throws MyPetStoreException {
		try {
			Account DO = new Account();
			this.dozerConvertUtil.viewObjectToDomainObject(account, DO);
			this.customerDao.saveAccount(DO);
		} catch (Exception e) {
			String msg = "Could not save account " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CustomerService#updateAccount(mypetstore.model.Account)
	 */
	public void updateAccount(AccountVo account) throws MyPetStoreException {
		try {
			Account DO = this.customerDao.getAccount(account.getUsername());
			// this.dozerConvertUtil.viewObjectToDomainObject(account, DO);
			this.customerDao.updateAccount(DO);
		} catch (Exception e) {
			String msg = "Could not update account " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CustomerService#deleteAccount(mypetstore.model.Account)
	 */
	public void deleteAccount(AccountVo account) throws MyPetStoreException {
		try {
			Account DO = this.customerDao.getAccount(account.getUsername());
			// this.dozerConvertUtil.viewObjectToDomainObject(account, DO);
			this.customerDao.deleteAccount(DO);
		} catch (Exception e) {
			String msg = "Could not update account " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}
}
