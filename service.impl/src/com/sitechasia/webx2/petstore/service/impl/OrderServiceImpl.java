package com.sitechasia.webx2.petstore.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sitechasia.webx.core.cache.annotation.WebXCache;
import com.sitechasia.webx2.petstore.model.Order;
import mypetstore.web.vo.OrderVo;
import com.sitechasia.webx2.petstore.dao.OrderDao;
import com.sitechasia.webx2.petstore.utils.DozerConvertUtil;
import mypetstore.exception.MyPetStoreException;
import mypetstore.service.OrderService;

/**
 * Order Service 接口实现类.
 * 
 * @see OrderService
 * 
 * @version 1.0
 * @since JDK1.5
 */
public class OrderServiceImpl implements OrderService {
	// the logger for this class
	private Log logger = LogFactory.getLog(this.getClass());

	// the CatalogDao used
	private OrderDao orderDao;

	private DozerConvertUtil dozerConvertUtil;

	// 注册OrderDao
	public void setOrderDao(OrderDao newOrderDao) {
		this.orderDao = newOrderDao;
	}

	// 注册dozer DO VO 转换器
	public void setDozerConvertUtil(DozerConvertUtil dozerConvertUtil) {
		this.dozerConvertUtil = dozerConvertUtil;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.OrderService#saveOrder(mypetstore.model.Order)
	 */
	public void saveOrder(OrderVo order) throws MyPetStoreException {
		try {
			Order DO = new Order();
			this.dozerConvertUtil.viewObjectToDomainObject(order, DO);
			this.orderDao.saveOrder(DO);
		} catch (Exception e) {
			String msg = "Could not save order " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.OrderService#getOrder(java.lang.String)
	 */
	public OrderVo getOrder(String orderId) throws MyPetStoreException {
		try {
			OrderVo vo = new OrderVo();
			this.dozerConvertUtil.domainObjectToViewObject(this.orderDao
					.getOrder(orderId), vo);
			return vo;
		} catch (Exception e) {
			String msg = "Could not get order " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	@WebXCache(classes = Order.class)
	public List getOrdersByAccountId(String accountId)
			throws MyPetStoreException {
		try {
			List orders = this.dozerConvertUtil.domainObjectsToViewObjects(
					this.orderDao.getOrdersByAccountId(accountId),
					OrderVo.class);

			return orders;
		} catch (Exception e) {
			String msg = "Could not get order " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

}
