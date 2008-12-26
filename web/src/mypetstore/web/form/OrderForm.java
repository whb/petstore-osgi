package mypetstore.web.form;

import mypetstore.web.vo.OrderVo;

import org.apache.struts.validator.ValidatorForm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * strus action form(订单车)
 * 
 * @author zhou wei
 * @since 2008-07-09
 */
@SuppressWarnings( { "serial", "unchecked" })
public class OrderForm extends ValidatorForm {

	private static final List CARD_TYPE_LIST;
	private OrderVo order = new OrderVo();
	private int orderId;
	private boolean shippingAddressRequired;
	private boolean confirmed;
	private List orderList;
	private String pageDirection;

	static {
		List cardList = new ArrayList();
		cardList.add("Visa");
		cardList.add("MasterCard");
		cardList.add("American Express");
		CARD_TYPE_LIST = Collections.unmodifiableList(cardList);
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public OrderVo getOrder() {
		return order;
	}

	public void setOrder(OrderVo order) {
		this.order = order;
	}

	public boolean isShippingAddressRequired() {
		return shippingAddressRequired;
	}

	public void setShippingAddressRequired(boolean shippingAddressRequired) {
		this.shippingAddressRequired = shippingAddressRequired;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public List getCreditCardTypes() {
		return CARD_TYPE_LIST;
	}

	public String getPageDirection() {
		return pageDirection;
	}

	public void setPageDirection(String pageDirection) {
		this.pageDirection = pageDirection;
	}

	public List getOrderList() {
		return orderList;
	}

	public void reset() {
		shippingAddressRequired = false;
	}

	public void clear() {
		order = new OrderVo();
		orderId = 0;
		shippingAddressRequired = false;
		confirmed = false;
		orderList = null;
		pageDirection = null;
	}

	public static List getCARD_TYPE_LIST() {
		return CARD_TYPE_LIST;
	}

	public void setOrderList(List orderList) {
		this.orderList = orderList;
	}

}
