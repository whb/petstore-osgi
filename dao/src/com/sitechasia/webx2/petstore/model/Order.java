/**
 * @{#} Order.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import com.sitechasia.webx.core.model.IDomainObject;

/**
 * Order domain object.
 *
 * 
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class Order implements IDomainObject{

	private int orderId;
	private String username;
	private Date orderDate;
	private String shipAddress1;
	private String shipAddress2;
	private String shipCity;
	private String shipState;
	private String shipZip;
	private String shipCountry;
	private String billAddress1;
	private String billAddress2;
	private String billCity;
	private String billState;
	private String billZip;
	private String billCountry;
	private String courier;
	private double totalPrice;
	private String billToFirstName;
	private String billToLastName;
	private String billEmail;
	private String billPhone;
	private String shipToFirstName;
	private String shipToLastName;
	private String shipEmail;
	private String shipPhone;
	private String creditCard;
	private String expiryDate;
	private String cardType;
	private String locale;
	private String status;
	private Set lineItems;

	public Order() {
		this.lineItems = new HashSet();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int newOrderId) {
		this.orderId = newOrderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String newUsername) {
		this.username = newUsername;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date newOrderDate) {
		this.orderDate = newOrderDate;
	}

	public String getShipAddress1() {
		return shipAddress1;
	}

	public void setShipAddress1(String newShipAddress1) {
		this.shipAddress1 = newShipAddress1;
	}

	public String getShipAddress2() {
		return shipAddress2;
	}

	public void setShipAddress2(String shipAddress2) {
		this.shipAddress2 = shipAddress2;
	}

	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String newShipCity) {
		this.shipCity = newShipCity;
	}

	public String getShipState() {
		return shipState;
	}

	public void setShipState(String newShipState) {
		this.shipState = newShipState;
	}

	public String getShipZip() {
		return shipZip;
	}

	public void setShipZip(String newShipZip) {
		this.shipZip = newShipZip;
	}

	public String getShipCountry() {
		return shipCountry;
	}

	public void setShipCountry(String newShipCountry) {
		this.shipCountry = newShipCountry;
	}

	public String getBillAddress1() {
		return billAddress1;
	}

	public void setBillAddress1(String newBillAddress1) {
		this.billAddress1 = newBillAddress1;
	}

	public String getBillAddress2() {
		return billAddress2;
	}

	public void setBillAddress2(String newBillAddress2) {
		this.billAddress2 = newBillAddress2;
	}

	public String getBillCity() {
		return billCity;
	}

	public void setBillCity(String newBillCity) {
		this.billCity = newBillCity;
	}

	public String getBillState() {
		return billState;
	}

	public void setBillState(String newBillState) {
		this.billState = newBillState;
	}

	public String getBillZip() {
		return billZip;
	}

	public void setBillZip(String newBillZip) {
		this.billZip = newBillZip;
	}

	public String getBillCountry() {
		return billCountry;
	}

	public void setBillCountry(String newBillCountry) {
		this.billCountry = newBillCountry;
	}

	public String getCourier() {
		return courier;
	}

	public void setCourier(String newCourier) {
		this.courier = newCourier;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double newTotalPrice) {
		this.totalPrice = newTotalPrice;
	}

	public String getBillToFirstName() {
		return billToFirstName;
	}

	public void setBillToFirstName(String newBillToFirstName) {
		this.billToFirstName = newBillToFirstName;
	}

	public String getBillToLastName() {
		return billToLastName;
	}

	public void setBillToLastName(String newBillToLastName) {
		this.billToLastName = newBillToLastName;
	}

	public String getBillEmail() {
		return this.billEmail;
	}

	public void setBillEmail(String newBillEmail) {
		this.billEmail = newBillEmail;
	}

	public String getBillPhone() {
		return this.billPhone;
	}

	public void setBillPhone(String newBillPhone) {
		this.billPhone = newBillPhone;
	}

	public String getShipToFirstName() {
		return shipToFirstName;
	}

	public void setShipToFirstName(String newShipToFirstName) {
		this.shipToFirstName = newShipToFirstName;
	}

	public String getShipToLastName() {
		return shipToLastName;
	}

	public void setShipToLastName(String newShipToLastName) {
		this.shipToLastName = newShipToLastName;
	}

	public String getShipEmail() {
		return this.shipEmail;
	}

	public void setShipEmail(String newShipEmail) {
		this.shipEmail = newShipEmail;
	}

	public String getShipPhone() {
		return this.shipPhone;
	}

	public void setShipPhone(String newShipPhone) {
		this.shipPhone = newShipPhone;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String newCreditCard) {
		this.creditCard = newCreditCard;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String newExpiryDate) {
		this.expiryDate = newExpiryDate;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String newCardType) {
		this.cardType = newCardType;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String newLocale) {
		this.locale = newLocale;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	public void setLineItems(Set newLineItems) {
		this.lineItems = newLineItems;
	}

	public Set getLineItems() {
		return lineItems;
	}

	@SuppressWarnings("unchecked")
	public void addLineItem(LineItem lineItem) {
		this.lineItems.add(lineItem);
	}

	/**
	 * Initialize the current order object based on the information inside an
	 * account and a shopping cart.
	 *
	 * @param account
	 *            the user account
	 * @param cart
	 *            the shopping cart
	 */
	public void init(Account account, Cart cart) {
		this.username = account.getUsername();
		this.orderDate = new Date();
		this.shipAddress1 = account.getAddress1();
		this.shipAddress2 = account.getAddress2();
		this.shipCity = account.getCity();
		this.shipState = account.getState();
		this.shipZip = account.getZip();
		this.shipCountry = account.getCountry();
		this.billAddress1 = account.getAddress1();
		this.billAddress2 = account.getAddress2();
		this.billCity = account.getCity();
		this.billState = account.getState();
		this.billZip = account.getZip();
		this.billCountry = account.getCountry();
		// hardcode for now
		this.courier = "FEDEX";
		this.billToFirstName = account.getFirstName();
		this.billToLastName = account.getLastName();
		this.billEmail = account.getEmail();
		this.billPhone = account.getPhone();
		this.shipToFirstName = account.getFirstName();
		this.shipToLastName = account.getLastName();
		this.shipEmail = account.getEmail();
		this.shipPhone = account.getPhone();
//		this.creditCard = account.getCreditCard();
//		this.expiryDate = account.getExpiryDate();
//		this.cardType = account.getCardType();
		this.locale = account.getLanguagePreference();
		// hardcode for now
		this.status = "new";

		this.totalPrice = cart.getSubTotal().doubleValue();
		Iterator ite = cart.getCartItemList().iterator();
		int i = 1;
		while (ite.hasNext()) {
			CartItem ci = (CartItem) ite.next();
			LineItem item = new LineItem();
			item.setLineNumber(i);
			item.setItemId(ci.getItem().getItemId());
			item.setQuantity(ci.getQuantity());
			item.setUnitPrice(ci.getItem().getUnitCost());
			this.addLineItem(item);
			i++;
		}
	}

	public void initOrder(Account account, Cart cart) {

		username = account.getUsername();
		orderDate = new Date();

		shipToFirstName = account.getFirstName();
		shipToLastName = account.getLastName();
		shipAddress1 = account.getAddress1();
		shipAddress2 = account.getAddress2();
		shipCity = account.getCity();
		shipState = account.getState();
		shipZip = account.getZip();
		shipCountry = account.getCountry();

		billToFirstName = account.getFirstName();
		billToLastName = account.getLastName();
		billAddress1 = account.getAddress1();
		billAddress2 = account.getAddress2();
		billCity = account.getCity();
		billState = account.getState();
		billZip = account.getZip();
		billCountry = account.getCountry();

		totalPrice = cart.getSubTotal().doubleValue();

		creditCard = "999 9999 9999 9999";
		expiryDate = "12/03";
		cardType = "Visa";
		courier = "UPS";
		locale = "CA";
		status = "P";

		Iterator i = cart.getAllCartItems();
		while (i.hasNext()) {
			CartItem cartItem = (CartItem) i.next();
			addLineItem(cartItem);
		}

	}

	public void addLineItem(CartItem cartItem) {
		LineItem lineItem = new LineItem(lineItems.size() + 1, cartItem);
		addLineItem(lineItem);
	}
}
