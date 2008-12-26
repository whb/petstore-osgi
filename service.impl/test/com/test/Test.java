package com.test;

import java.util.List;

import com.sitechasia.webx2.petstore.dao.CatalogDao;
import com.sitechasia.webx2.petstore.dao.CustomerDao;
import com.sitechasia.webx2.petstore.dao.ItemDao;
import com.sitechasia.webx2.petstore.dao.OrderDao;
import com.sitechasia.webx2.petstore.dao.ProductDao;
import com.sitechasia.webx2.petstore.dao.SequenceDao;
import com.sitechasia.webx2.petstore.model.Account;
import com.sitechasia.webx2.petstore.model.Category;
import com.sitechasia.webx2.petstore.model.Item;

public class Test {

	CatalogDao catalogDao;

	CustomerDao customerDao;

	ItemDao itemDao;

	OrderDao orderDao;

	ProductDao productDao;

	SequenceDao sequenceDao;

	public void start() {

		System.out
				.println("CatalogDao start:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		List list = catalogDao.getCategoryList();
		for (int i = 0; i < list.size(); i++) {
			Category category = (Category) list.get(i);
			System.out.println("#############::" + category.getCategoryId());
		}

		System.out
				.println("CatalogDao stop:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		System.out
				.println("customerDao start:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		String accountId = "j2ee";
		Account account = customerDao.getAccount(accountId);
		System.out.println("#############::" + account.getEmail());

		System.out
				.println("customerDao stop:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		System.out
				.println("itemDao start:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		String itemId = "EST-6";
		Item item = itemDao.getItem(itemId);

		System.out.println("#############::" + item.getItemId());

		System.out
				.println("itemDao stop:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		System.out
				.println("orderDao start:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		System.out.println("#############::" + orderDao.toString());

		System.out
				.println("orderDao stop:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		System.out
				.println("productDao start:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		String categoryId = "DOGS";
		System.out.println("#############::" + productDao.toString());
		List productLis = productDao.getProductListByCategory(categoryId);

		System.out.println("#############::" + productLis.size());

		System.out
				.println("productDao stop:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		System.out
				.println("sequenceDao start:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		System.out.println("#############::" + sequenceDao.getSeqNum(null));

		System.out
				.println("sequenceDao stop:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

	public void stop() {

		System.out
				.println("@@@@@@@@@@@ stop:@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

	}

	public void setCatalogDao(CatalogDao catalogDao) {
		this.catalogDao = catalogDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

}
