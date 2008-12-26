package mypetstore.service;

import java.util.List;


import mypetstore.exception.MyPetStoreException;
import mypetstore.web.vo.OrderVo;

/**
 * Order Service 接口.
 * <p>
 * 该Service包含Order相关的业务服务
 * 
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
public interface OrderService {

	/**
	 * 
	 * 保存Order
	 * 
	 * @param order
	 * 
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public void saveOrder(OrderVo order) throws MyPetStoreException;

	/**
	 * 根据OrderVo主键获得OrderVo
	 * 
	 * @param orderId
	 *            Order主键
	 * @return OrderVo
	 * 
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public OrderVo getOrder(String orderId) throws MyPetStoreException;

	/**
	 * 
	 * 根据人员查OrderVo; 根据AccountVo主键获得OrderVo结果集
	 * 
	 * 
	 * @param accountId
	 *            Account主键
	 * @return List<OrderVo>
	 */
	public List getOrdersByAccountId(String accountId)
			throws MyPetStoreException;
}
