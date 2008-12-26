package mypetstore.web.action;

import mypetstore.service.OrderService;
import mypetstore.web.form.CartForm;
import mypetstore.web.form.OrderForm;
import mypetstore.web.vo.OrderVo;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * strus action 创建订单
 * 
 * @author zhou wei
 * @since 2008-07-09
 */
public class NewOrderAction extends
		com.sitechasia.webx.core.web.struts1.action.BaseStrutsAction {

	private OrderService orderService;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderForm orderFrom = (OrderForm) form;

		OrderVo order = orderFrom.getOrder();

		boolean shippingAddressRequired = orderFrom.isShippingAddressRequired();

		boolean isConfirmed = orderFrom.isConfirmed();

		if (shippingAddressRequired) {
			shippingAddressRequired = false;
			orderFrom.setShippingAddressRequired(shippingAddressRequired);
			return mapping.findForward("shipping");
		} else if (!isConfirmed) {
			return mapping.findForward("confirm");
		} else if (order != null) {
			orderService.saveOrder(order);
			CartForm cartForm = (CartForm) request.getSession().getAttribute(
					"cartForm");
			cartForm.clear();
			request.setAttribute("message",
					"Thank you, your order has been submitted.");
			return mapping.findForward("success");
		} else {
			request
					.setAttribute("message",
							"An error occurred processing your order (order was null).");
			return mapping.findForward("failure");
		}
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
}
