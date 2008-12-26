package mypetstore.service;

import mypetstore.exception.MyPetStoreException;
import mypetstore.web.vo.AccountVo;

/**
 * Customer Service 接口.
 * <p>
 * 该Service包含Account相关的业务服务
 *
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
public interface CustomerService {

	/**
	 * 根据AccountVO主键获得AccountVo
	 *
	 * @param accountId
	 *            Account主键
	 * @return AccountVo
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */

	public AccountVo getAccount(String accountId) throws MyPetStoreException;

	/**
	 *
	 * 用户登录
	 *
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return AccountVo
	 * @throws MyPetStoreException
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public AccountVo signon(String username, String password)
			throws MyPetStoreException;

	/**
	 *
	 * 保存AccountVo
	 *
	 * @param account
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public void saveAccount(AccountVo account) throws MyPetStoreException;

	/**
	 *
	 * 更新AccountVo
	 *
	 * @param account
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */

	public void updateAccount(AccountVo account) throws MyPetStoreException;

	/**
	 *
	 * 删除AccountVo
	 *
	 * @param account
	 *
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public void deleteAccount(AccountVo account) throws MyPetStoreException;
}
