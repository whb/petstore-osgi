/**
 * @{#} AccountVo.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package mypetstore.web.vo;

import com.sitechasia.webx.core.model.IViewObject;

/**
 * Account View object.
 * 
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class AccountVo implements IViewObject {

	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String status;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String phone;

	private SignonVo signon;
	private ProfileVo profile;

	public AccountVo() {
		this.signon = new SignonVo();
		this.signon.setAccount(this);
		this.profile = new ProfileVo();
		this.profile.setAccount(this);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String newLastName) {
		this.lastName = newLastName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String newAddress1) {
		this.address1 = newAddress1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String newAddress2) {
		this.address2 = newAddress2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String newCity) {
		this.city = newCity;
	}

	public String getState() {
		return state;
	}

	public void setState(String newState) {
		this.state = newState;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String newZip) {
		this.zip = newZip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String newCountry) {
		this.country = newCountry;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String newPhone) {
		this.phone = newPhone;
	}

	private ProfileVo getProfile() {
		return this.profile;
	}

	private void setProfile(ProfileVo newProfile) {
		this.profile = newProfile;
	}

	public String getFavoriteCategoryId() {
		return this.profile.getFavoriteCategoryId();
	}

	public void setFavoriteCategoryId(String newFavoriteCategoryId) {
		this.profile.setFavoriteCategoryId(newFavoriteCategoryId);
	}

	public String getLanguagePreference() {
		return this.profile.getLanguagePreference();
	}

	public void setLanguagePreference(String newLanguagePreference) {
		this.profile.setLanguagePreference(newLanguagePreference);
	}

	public boolean isListOption() {
		return this.profile.isListOption();
	}

	public void setListOption(boolean newListOption) {
		this.profile.setListOption(newListOption);
	}

	public boolean isBannerOption() {
		return this.profile.isBannerOption();
	}

	public void setBannerOption(boolean newBannerOption) {
		this.profile.setBannerOption(newBannerOption);
	}

	public String getBannerName() {
		return profile.getBannerData().getBannerName();
	}

	private SignonVo getSignon() {
		return this.signon;
	}

	private void setSignon(SignonVo newSignon) {
		this.signon = newSignon;
	}

	public String getPassword() {
		return this.signon.getPassword();
	}

	public void setPassword(String newPassword) {
		this.signon.setPassword(newPassword);
	}
}
