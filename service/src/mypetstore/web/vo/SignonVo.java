/**
 * @{#} SignonVo.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package mypetstore.web.vo;

import com.sitechasia.webx.core.model.IViewObject;

/**
 * Signon view object.
 * 
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class SignonVo implements IViewObject {

	private String username;
	private String password;
	private AccountVo account;

	public SignonVo() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	public AccountVo getAccount() {
		return this.account;
	}

	public void setAccount(AccountVo newAccount) {
		this.account = newAccount;
	}
}
