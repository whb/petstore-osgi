/**
 * @{#} LineItem.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.model;

import com.sitechasia.webx.core.model.IDomainObject;

/**
 * LineItem domain object.
 *
 * 
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class LineItem implements IDomainObject{

	private int orderId;
	private int lineNumber;
	private int quantity;
	private String itemId;
	private double unitPrice;

	public LineItem() {
	}

	public LineItem(int lineNumber, CartItem cartItem) {
		this.lineNumber = lineNumber;
		this.quantity = cartItem.getQuantity();
		this.itemId = cartItem.getItem().getItemId();
		this.unitPrice = cartItem.getItem().getListPrice();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int newOrderId) {
		this.orderId = newOrderId;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int newLineNumber) {
		this.lineNumber = newLineNumber;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String newItemId) {
		this.itemId = newItemId;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double newUnitprice) {
		this.unitPrice = newUnitprice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}

	public double getTotalPrice() {
		return this.unitPrice * this.quantity;
	}

	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof LineItem) {
				return this.orderId == ((LineItem) obj).orderId
						&& this.lineNumber == ((LineItem) obj).lineNumber;
			}
		}

		return false;
	}

	public int hashCode() {
		return this.orderId + this.lineNumber;
	}
}
