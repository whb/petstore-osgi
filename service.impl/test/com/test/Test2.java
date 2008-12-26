package com.test;

import java.util.List;

import mypetstore.exception.MyPetStoreException;
import mypetstore.service.*;


public class Test2 {

	CatalogService catalogService;

	CustomerService customerService;

	OrderService orderService;


	public void start() {
		
		try {
			
			System.out.println("com.sitechasia.webx2.petstore.service.impl:start:@@@@@@@@@@@@@@@@@"+catalogService.getProduct("K9-BD-01").getName());
			System.out.println("com.sitechasia.webx2.petstore.service.impl:start:@@@@@@@@@@@@@@@@@"+customerService.getAccount("j2ee").getEmail());
			System.out.println("com.sitechasia.webx2.petstore.service.impl:start:@@@@@@@@@@@@@@@@@"+orderService.getOrder("1001").getOrderId());
			
		} catch (MyPetStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
	}

	public void stop() {

		System.out
				.println("com.sitechasia.webx2.petstore.service.impl:stop @@@@@@@@@@@@@@@@@@@@@@@@@@@@");

	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	
}
