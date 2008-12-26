package mypetstore.web.form;

import mypetstore.web.vo.CartVo;

import org.apache.struts.action.ActionForm;

/**
 * 
 * strus action form(购物车)
 * 
 * @author zhou wei
 * @since 2008-07-09
 */
@SuppressWarnings("serial")
public class CartForm extends ActionForm {

	private CartVo cart = new CartVo();
	private String workingItemId;
	private String pageDirection;

	public CartVo getCart() {
		return cart;
	}

	public void setCart(CartVo cart) {
		this.cart = cart;
	}

	public String getWorkingItemId() {
		return workingItemId;
	}

	public void setWorkingItemId(String workingItemId) {
		this.workingItemId = workingItemId;
	}

	public String getPageDirection() {
		return pageDirection;
	}

	public void setPageDirection(String pageDirection) {
		this.pageDirection = pageDirection;
	}

	public void clear() {
		cart = new CartVo();
		workingItemId = null;
		pageDirection = null;
	}

}
