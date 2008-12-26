/**
 * @{#} BannerDataVo.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package mypetstore.web.vo;

import com.sitechasia.webx.core.model.IViewObject;

/**
 * BannerData view object.
 *
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class BannerDataVo implements IViewObject{

	private String favouriteCategoryId;
	private String bannerName;

	public BannerDataVo() {
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
