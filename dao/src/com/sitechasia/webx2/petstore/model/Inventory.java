/**
 * @{#} Inventory.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.model;

import com.sitechasia.webx.core.model.IDomainObject;

/**
 * Inventory domain object.
 *
 * 
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class Inventory implements IDomainObject{

	private String itemId;
	private int quantity;
	private Item item;

	public Inventory() {
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String newItemId) {
		this.itemId = newItemId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item newItem) {
		this.item = newItem;
	}

}
