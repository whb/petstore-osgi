/**
 * @{#} Signon.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.model;

import com.sitechasia.webx.core.model.IDomainObject;


/**
 * Signon domain object.
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class Signon implements IDomainObject {

	private String username;
	private String password;
	private Account account;

	public Signon() {
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

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account newAccount) {
		this.account = newAccount;
	}

}
