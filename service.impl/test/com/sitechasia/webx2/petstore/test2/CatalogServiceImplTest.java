package com.sitechasia.webx2.petstore.test2;

import java.util.ArrayList;
import java.util.List;
import mypetstore.exception.MyPetStoreException;
import mypetstore.service.CatalogService;
import mypetstore.web.vo.CategoryVo;
import mypetstore.web.vo.ItemVo;
import mypetstore.web.vo.ProductVo;
import org.springframework.context.ApplicationContext;

/**
 * testing CatalogService
 * 
 * 
 * @see CatalogServiceImpl
 */
public class CatalogServiceImplTest extends
		com.sitechasia.webx.test.AbstractTestCase {

	ApplicationContext beanFactory = null;

	CatalogService catalogService;

	public void setUp() {
		super.setUp();
		beanFactory = MyPetStoreBeanFactory2.getApplicationContext();
		catalogService = (CatalogService) beanFactory.getBean("catalogService");
	}

	/**
	 * testing getCategoryList()
	 * 
	 * @throws MyPetStoreException
	 */

	public void testGetCategoryList() throws MyPetStoreException {
		List list = catalogService.getCategoryList();
		for (int i = 0; i < list.size(); i++) {
			CategoryVo category = (CategoryVo) list.get(i);
			assertEquals(category.getCategoryId().toLowerCase(), category
					.getName().toLowerCase());
		}
	}

	/**
	 * testing getCategory()
	 * 
	 * @throws MyPetStoreException
	 */

	public void testGetCategory() throws MyPetStoreException {
		String categoryId = "DOGS";
		CategoryVo category = catalogService.getCategory(categoryId);
		assertEquals(category.getName(), "Dogs");
	}

	/**
	 * testing getProductListByCategory()
	 * 
	 * @throws MyPetStoreException
	 */

	public void testGetProductListByCategory() throws MyPetStoreException {
		String categoryId = "DOGS";
		List list = catalogService.getProductListByCategory(categoryId);
		assertEquals(list.size(), 6);
	}

	/**
	 * testing getProduct()
	 * 
	 * @throws MyPetStoreException
	 */

	public void testGetProduct() throws MyPetStoreException {
		String productId = "K9-BD-01";
		ProductVo product = catalogService.getProduct(productId);
		assertEquals(product.getName(), "Bulldog");
	}

	/**
	 * testing getItemListByProduct()
	 * 
	 * @throws MyPetStoreException
	 */

	public void testGetItemListByProduct() throws MyPetStoreException {
		String productId = "K9-BD-01";
		List list = catalogService.getItemListByProduct(productId);
		for (int i = 0; i < list.size(); i++) {
			ItemVo item = (ItemVo) list.get(i);

			if (item.getItemId().equalsIgnoreCase("EST-6")) {
				assertEquals(item.getListPrice(), 18.5d, 18.5d);
			}
		}
	}

	/**
	 * testing getItem()
	 * 
	 * @throws MyPetStoreException
	 */

	public void testGetItem() throws MyPetStoreException {
		String itemId = "EST-6";
		ItemVo item = catalogService.getItem(itemId);
		assertEquals(item.getListPrice(), 18.5d, 18.5d);
	}

	/**
	 * testing searchProductList()
	 * 
	 * @throws MyPetStoreException
	 */
	public void testSearchProductList() throws MyPetStoreException {
		List<String> keywords = new ArrayList<String>();
		keywords.add("DOGS");
		List list = catalogService.searchProductList(keywords);
		assertEquals(list.size(), 6);
	}

	/**
	 * testing saveCategory()
	 * 
	 * @throws MyPetStoreException
	 */

	public void testSaveCategory() throws MyPetStoreException {
		String categoryId = "DOGS";
		CategoryVo category = catalogService.getCategory(categoryId);
		String categoryId_new = categoryId + "01";
		category.setCategoryId(categoryId_new);
		catalogService.saveCategory(category);
		category = catalogService.getCategory(categoryId_new);
		assertEquals(category.getCategoryId(), categoryId_new);
	}

	/**
	 * testing deleteCategoryById()
	 * 
	 * @throws MyPetStoreException
	 */

	public void testDeleteCategoryById() throws MyPetStoreException {
		String categoryId = "DOGS" + "01";
		catalogService.deleteCategoryById(categoryId);
		// 删除之后进行查询将会诱发MyPetStoreException
		try {
			CategoryVo category = catalogService.getCategory(categoryId);
		} catch (Exception e) {
			assertEquals(e.getClass().getName(),
					"mypetstore.exception.MyPetStoreException");
		}

	}
}
