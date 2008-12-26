/**
 * @{#} CartVo.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package mypetstore.web.vo;

import com.sitechasia.webx.core.model.IViewObject;

import java.math.BigDecimal;
import java.util.*;

/**
 * Cart view object.
 * 
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
public class CartVo implements IViewObject {

	private final Map itemMap = Collections.synchronizedMap(new HashMap());
	private final List itemList = new ArrayList();

	public Iterator getCartItems() {
		return itemList.iterator();
	}

	public List getCartItemList() {
		return itemList;
	}

	public int getNumberOfItems() {
		return itemList.size();
	}

	public Iterator getAllCartItems() {
		return itemList.iterator();
	}

	public boolean containsItemId(String itemId) {
		return itemMap.containsKey(itemId);
	}

	public void addItem(ItemVo item, boolean isInStock) {
		CartItemVo cartItem = (CartItemVo) itemMap.get(item.getItemId());
		if (cartItem == null) {
			cartItem = new CartItemVo();
			cartItem.setItem(item);
			cartItem.setQuantity(0);
			cartItem.setInStock(isInStock);
			itemMap.put(item.getItemId(), cartItem);
			itemList.add(cartItem);
		}
		cartItem.incrementQuantity();
	}

	public ItemVo removeItemById(String itemId) {
		CartItemVo cartItem = (CartItemVo) itemMap.remove(itemId);
		if (cartItem == null) {
			return null;
		} else {
			itemList.remove(cartItem);
			return cartItem.getItem();
		}
	}

	public void incrementQuantityByItemId(String itemId) {
		CartItemVo cartItem = (CartItemVo) itemMap.get(itemId);
		cartItem.incrementQuantity();
	}

	public void setQuantityByItemId(String itemId, int quantity) {
		CartItemVo cartItem = (CartItemVo) itemMap.get(itemId);
		cartItem.setQuantity(quantity);
	}

	public BigDecimal getSubTotal() {
		BigDecimal subTotal = new BigDecimal("0");
		Iterator items = getAllCartItems();
		while (items.hasNext()) {
			CartItemVo cartItem = (CartItemVo) items.next();
			ItemVo item = cartItem.getItem();
			BigDecimal listPrice = BigDecimal.valueOf(item.getListPrice());
			BigDecimal quantity = new BigDecimal(String.valueOf(cartItem
					.getQuantity()));
			subTotal = subTotal.add(listPrice.multiply(quantity));
		}
		return subTotal;
	}

}
