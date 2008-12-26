/**
 * @{#} InventoryVo.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package mypetstore.web.vo;

import com.sitechasia.webx.core.model.IValueObject;
import com.sitechasia.webx.core.model.IViewObject;

/**
 * Inventory view object.
 * 
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class InventoryVo implements IViewObject {

	private String itemId;
	private int quantity;
	private ItemVo item;

	public InventoryVo() {
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

	public ItemVo getItem() {
		return this.item;
	}

	public void setItem(ItemVo newItem) {
		this.item = newItem;
	}
}
