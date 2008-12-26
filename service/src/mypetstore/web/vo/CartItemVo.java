/**
 * @{#} CartItemVo.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package mypetstore.web.vo;

import java.math.BigDecimal;

import com.sitechasia.webx.core.model.IViewObject;

/**
 * CartItem view object.
 *
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
public class CartItemVo implements IViewObject {

	private ItemVo item;
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

	public ItemVo getItem() {
		return item;
	}

	public void setItem(ItemVo item) {
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
