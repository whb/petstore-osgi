/**
 * @{#} ProfileVo.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package mypetstore.web.vo;

import com.sitechasia.webx.core.model.IViewObject;

/**
 * Profile view object.
 *
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class ProfileVo implements IViewObject {

	private String username;
	private String favoriteCategoryId;
	private String languagePreference;
	private boolean listOption;
	private boolean bannerOption;
	private BannerDataVo bannerData;
	private AccountVo account;

	public ProfileVo() {
		this.bannerData = new BannerDataVo();
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

	public BannerDataVo getBannerData() {
		return this.bannerData;
	}

	public void setBannerData(BannerDataVo newBannerData) {
		this.bannerData = newBannerData;
	}

	public AccountVo getAccount() {
		return this.account;
	}

	public void setAccount(AccountVo newAccount) {
		this.account = newAccount;
	}
}
