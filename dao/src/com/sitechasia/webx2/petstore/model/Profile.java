/**
 * @{#} Profile.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.model;

import com.sitechasia.webx.core.model.IDomainObject;

/**
 * Profile domain object.
 *
 * 
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class Profile implements IDomainObject {

	private String username;
	private String favoriteCategoryId;
	private String languagePreference;
	private boolean listOption;
	private boolean bannerOption;
	private BannerData bannerData;
	private Account account;

	public Profile() {
		this.bannerData = new BannerData();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	public String getFavoriteCategoryId() {
		return favoriteCategoryId;
	}

	public void setFavoriteCategoryId(String newFavoriteCategoryId) {
		this.favoriteCategoryId = newFavoriteCategoryId;
	}

	public String getLanguagePreference() {
		return languagePreference;
	}

	public void setLanguagePreference(String newLanguagePreference) {
		this.languagePreference = newLanguagePreference;
	}

	public boolean isListOption() {
		return listOption;
	}

	public void setListOption(boolean newListOption) {
		this.listOption = newListOption;
	}

	public boolean isBannerOption() {
		return bannerOption;
	}

	public void setBannerOption(boolean newBannerOption) {
		this.bannerOption = newBannerOption;
	}

	public BannerData getBannerData() {
		return this.bannerData;
	}

	public void setBannerData(BannerData newBannerData) {
		this.bannerData = newBannerData;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account newAccount) {
		this.account = newAccount;
	}
}
