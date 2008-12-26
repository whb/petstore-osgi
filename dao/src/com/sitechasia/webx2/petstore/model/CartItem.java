/**
 * @{#} CartItem.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.model;

import java.math.BigDecimal;

import com.sitechasia.webx.core.model.IDomainObject;

/**
 * CartItem domain object.
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
public class CartItem  implements IDomainObject {

	private Item item;
	private int quantity;
	private boolean inStock;
	private BigDecimal total;

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
		calculateTotal();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		calculateTotal();
	}

	public void incrementQuantity() {
		quantity++;
		calculateTotal();
	}

	private void calculateTotal() {
		if (item != null && BigDecimal.valueOf(item.getListPrice()) != null) {
			total = BigDecimal.valueOf(item.getListPrice()).multiply(
					new BigDecimal(quantity));
		} else {
			total = null;
		}
	}

}
