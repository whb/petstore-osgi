package mypetstore.web.action;

import mypetstore.service.OrderService;
import mypetstore.web.form.AccountForm;
import mypetstore.web.form.CartForm;
import mypetstore.web.form.OrderForm;
import mypetstore.web.vo.OrderVo;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * strus action 订单
 * 
 * @author zhou wei
 * @since 2008-07-09
 */
public class OrderAction extends DispatchAction {

	private OrderService orderService;

	/**
	 * 
	 * 查看指定账户的全部订单
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public ActionForward listOrders(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AccountForm accountForm = (AccountForm) request.getSession()
				.getAttribute("accountForm");

		OrderForm orderFrom = (OrderForm) form;

		List orders = new ArrayList();

		orders.addAll(orderService.getOrdersByAccountId(accountForm
				.getAccount().getUsername()));

		orderFrom.setOrderList(orders);

		return mapping.findForward("listOrders");
	}

	/**
	 * 
	 * 订单分页
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	public ActionForward switchOrderPage(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//
//		OrderForm orderFrom = (OrderForm) form;
//		String pageDirection = orderFrom.getPageDirection();
//		PaginatedList orderList = orderFrom.getOrderList();
//
//		if ("next".equals(pageDirection)) {
//			orderList.nextPage();
//			orderFrom.setOrderList(orderList);
//		} else if ("previous".equals(pageDirection)) {
//			orderList.previousPage();
//			orderFrom.setOrderList(orderList);
//		}
//		return mapping.findForward("switchOrderPage");
//	}

	/**
	 * 
	 * 页面跳转以及初始化 创建订单
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newOrderForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AccountForm accountForm = (AccountForm) request.getSession()
				.getAttribute("accountForm");

		CartForm cartForm = (CartForm) request.getSession().getAttribute(
				"cartForm");

		OrderForm orderFrom = (OrderForm) form;

		orderFrom.clear();

		if (accountForm == null || !accountForm.isAuthenticated()) {
			request
					.setAttribute(
							"message",
							"You must sign on before attempting to check out.  Please sign on and try checking out again.");
			return mapping.findForward("signon");
		} else if (cartForm != null) {
			OrderVo order = orderFrom.getOrder();
			order.initOrder(accountForm.getAccount(), cartForm.getCart());
			orderFrom.setOrder(order);
			return mapping.findForward("newOrderForm");
		} else {
			request
					.setAttribute("message",
							"An order could not be created because a cart could not be found.");
			return mapping.findForward("failure");
		}
	}

	/**
	 * 
	 * 查看订单
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward viewOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AccountForm accountForm = (AccountForm) request.getSession()
				.getAttribute("accountForm");
		OrderForm orderFrom = (OrderForm) form;
		OrderVo order = orderService.getOrder(Integer.valueOf(
				orderFrom.getOrderId()).toString());

		if (accountForm.getAccount().getUsername().equals(order.getUsername())) {
			orderFrom.setOrder(order);
			return mapping.findForward("viewOrder");
		} else {
			order = null;
			orderFrom.setOrder(order);

			request.setAttribute("message",
					"You may only view your own orders.");
			return mapping.findForward("failure");
		}
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}
