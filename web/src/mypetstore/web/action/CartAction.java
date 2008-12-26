package mypetstore.web.action;

import mypetstore.service.CatalogService;
import mypetstore.web.form.CartForm;
import mypetstore.web.vo.CartItemVo;
import mypetstore.web.vo.CartVo;
import mypetstore.web.vo.ItemVo;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * strus action (购物车)
 * 
 * @author zhou wei
 * @since 2008-07-09
 */
public class CartAction extends DispatchAction {

	// private CatalogService catalogService = (CatalogService)
	// MyPetStoreBeanFactory
	// .getApplicationContext().getBean("catalogService");
	private CatalogService catalogService;

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	/**
	 * 
	 * 添加商品到购物车
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addItemToCart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CartForm cartForm = (CartForm) form;

		CartVo cart = cartForm.getCart();

		String workingItemId = cartForm.getWorkingItemId();

		if (cart.containsItemId(workingItemId)) {
			cart.incrementQuantityByItemId(workingItemId);
			cartForm.setCart(cart);
		} else {

			boolean isInStock = true;
			ItemVo item = catalogService.getItem(workingItemId);
			cart.addItem(item, isInStock);

			cartForm.setCart(cart);
		}
		return mapping.findForward("cartPage");
	}

	/**
	 * 
	 * 从购物车中去掉选购的商品
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward removeItemFromCart(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CartForm cartForm = (CartForm) form;

		CartVo cart = cartForm.getCart();

		String workingItemId = cartForm.getWorkingItemId();

		ItemVo item = cart.removeItemById(workingItemId);

		cartForm.setCart(cart);

		if (item == null) {
			request.setAttribute("message",
					"Attempted to remove null CartItem from Cart.");
			return mapping.findForward("failure");
		} else {
			return mapping.findForward("cartPage");
		}
	}

	/**
	 * 
	 * 更新计算购物车商品单价×数量的总钱数
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward updateCartQuantities(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CartForm cartForm = (CartForm) form;

		CartVo cart = cartForm.getCart();

		Iterator cartItems = cart.getAllCartItems();
		while (cartItems.hasNext()) {
			CartItemVo cartItem = (CartItemVo) cartItems.next();
			String itemId = cartItem.getItem().getItemId();

			int quantity = Integer.parseInt((String) request
					.getParameter(itemId));
			cart.setQuantityByItemId(itemId, quantity);
			if (quantity < 1) {
				cartItems.remove();
			}
		}
		cartForm.setCart(cart);
		return mapping.findForward("cartPage");
	}

	/**
	 * 
	 * 购物车分页处理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// public ActionForward switchCartPage(ActionMapping mapping, ActionForm
	// form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	//
	// CartForm cartForm = (CartForm) form;
	//
	// CartVo cart = cartForm.getCart();
	//
	// String pageDirection = cartForm.getPageDirection();
	//
	// if ("next".equals(pageDirection)) {
	// cart.getCartItemList().nextPage();
	// cartForm.setCart(cart);
	// } else if ("previous".equals(pageDirection)) {
	// cart.getCartItemList().previousPage();
	// cartForm.setCart(cart);
	// }
	// return mapping.findForward("cartPage");
	// }
}
