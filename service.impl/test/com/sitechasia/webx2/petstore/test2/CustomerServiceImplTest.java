package com.sitechasia.webx2.petstore.test2;

import mypetstore.exception.MyPetStoreException;
import mypetstore.service.CustomerService;
import mypetstore.web.vo.AccountVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.context.ApplicationContext;

/**
 * testing CustomerService
 * 
 * 
 * @see CustomerServiceImpl
 */
public class CustomerServiceImplTest extends
		com.sitechasia.webx.test.AbstractTestCase {

	ApplicationContext beanFactory = null;

	CustomerService customerService;

	static String accountIdForSave = "j2ee " + System.currentTimeMillis();

	public void setUp() {
		super.setUp();
		beanFactory = MyPetStoreBeanFactory2.getApplicationContext();
		customerService = (CustomerService) beanFactory
				.getBean("customerService");

	}

	/**
	 * testing getAccount()
	 * 
	 * @throws MyPetStoreException
	 */
	public void testGetAccount() throws MyPetStoreException {
		String accountId = "j2ee";
		AccountVo account = customerService.getAccount(accountId);
		assertEquals(account.getEmail(), "yourname@yourdomain.com");
	}

	/**
	 * testing signon()
	 * 
	 * @throws MyPetStoreException
	 */
	public void testSignon() throws MyPetStoreException {
		String username = "j2ee";
		String password = "j2ee";
		AccountVo account = customerService.signon(username, password);
		assertEquals(account.getEmail(), "yourname@yourdomain.com");
	}

	/**
	 * testing saveAccount()
	 * 
	 * @throws MyPetStoreException
	 */
	public void testSaveAccount() throws Exception {
		String accountId = "j2ee";

		AccountVo account = customerService.getAccount(accountId);

		AccountVo accountnew = new AccountVo();

		PropertyUtils.copyProperties(accountnew, account);

		accountnew.setUsername(accountIdForSave);

		customerService.saveAccount(accountnew);

		account = customerService.getAccount(accountIdForSave);

		assertEquals(accountnew.getEmail(), "yourname@yourdomain.com");
	}

	/**
	 * testing updateAccount()
	 * 
	 * @throws MyPetStoreException
	 */
	public void testUpdateAccount() throws MyPetStoreException {
		String accountId = "j2ee";

		String emailUpdateAfter = "newyourname@yourdomain.com";

		AccountVo account = customerService.getAccount(accountId);

		account.setEmail(emailUpdateAfter);

		customerService.updateAccount(account);

		account = customerService.getAccount(accountId);

		assertEquals(account.getEmail(), emailUpdateAfter);
	}

	/**
	 * testing deleteAccount()
	 * 
	 * @throws MyPetStoreException
	 */
	public void testDeleteAccount() throws MyPetStoreException {
		AccountVo account = customerService.getAccount(accountIdForSave);
		customerService.deleteAccount(account);
		// 删除之后进行查询将会诱发MyPetStoreException
		try {
			account = customerService.getAccount(accountIdForSave);
		} catch (Exception e) {
			assertEquals(e.getClass().getName(),
					"mypetstore.exception.MyPetStoreException");
		}
	}

}
