/**
 * @{#} LineItemVo.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package mypetstore.web.vo;

import com.sitechasia.webx.core.model.IViewObject;

/**
 * LineItem view object.
 * 
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class LineItemVo implements IViewObject {

	private int orderId;
	private int lineNumber;
	private int quantity;
	private String itemId;
	private double unitPrice;

	public LineItemVo() {
	}

	public LineItemVo(int lineNumber, CartItemVo cartItem) {
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
			if (obj instanceof LineItemVo) {
				return this.orderId == ((LineItemVo) obj).orderId
						&& this.lineNumber == ((LineItemVo) obj).lineNumber;
			}
		}

		return false;
	}

	public int hashCode() {
		return this.orderId + this.lineNumber;
	}
}
