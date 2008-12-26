/**
 * @{#} BannerData.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.model;

import com.sitechasia.webx.core.model.IDomainObject;

/**
 * BannerData domain object.
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class BannerData implements IDomainObject {

	private String favouriteCategoryId;
	private String bannerName;

	public BannerData() {
	}

	public String getFavouriteCategoryId() {
		return favouriteCategoryId;
	}

	public void setFavouriteCategoryId(String favouriteCategoryId) {
		this.favouriteCategoryId = favouriteCategoryId;
	}

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
}
