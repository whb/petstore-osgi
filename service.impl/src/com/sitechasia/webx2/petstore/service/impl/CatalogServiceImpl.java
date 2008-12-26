package com.sitechasia.webx2.petstore.service.impl;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sitechasia.webx.core.cache.CacheToken;
import com.sitechasia.webx.core.cache.annotation.WebXCache;
import com.sitechasia.webx2.petstore.model.Product;
import com.sitechasia.webx2.petstore.model.Category;
import mypetstore.web.vo.CategoryVo;
import mypetstore.web.vo.ItemVo;
import mypetstore.web.vo.ProductVo;
import com.sitechasia.webx2.petstore.dao.CatalogDao;
import com.sitechasia.webx2.petstore.dao.ItemDao;
import com.sitechasia.webx2.petstore.dao.ProductDao;
import com.sitechasia.webx2.petstore.utils.DozerConvertUtil;
import mypetstore.exception.MyPetStoreException;
import mypetstore.service.CatalogService;

/**
 * Catalog Service 接口实现类.
 * 
 * @see CatalogService
 * 
 * @version 1.0
 * @since JDK1.5
 */
public class CatalogServiceImpl implements CatalogService {
	// the logger for this class
	private Log logger = LogFactory.getLog(this.getClass());

	private CatalogDao catalogDao;

	private ItemDao itemDao;

	private ProductDao productDao;

	private DozerConvertUtil dozerConvertUtil;

	// 注册ItemDao
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	// 注册ProductDao
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 注册CatalogDao
	public void setCatalogDao(CatalogDao newCatalogDao) {
		this.catalogDao = newCatalogDao;
	}

	// 注册dozer DO VO 转换器
	public void setDozerConvertUtil(DozerConvertUtil dozerConvertUtil) {
		this.dozerConvertUtil = dozerConvertUtil;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CatalogService#getCategoryList()
	 */
	public List getCategoryList() throws MyPetStoreException {
		try {
			return dozerConvertUtil.domainObjectsToViewObjects(this.catalogDao
					.getCategoryList(), CategoryVo.class);
		} catch (Exception e) {
			String msg = "Could not get category list " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CatalogService#getCategory(java.lang.String)
	 */
	public CategoryVo getCategory(String categoryId) throws MyPetStoreException {
		try {
			CategoryVo categoryVo = new CategoryVo();
			dozerConvertUtil.domainObjectToViewObject(this.catalogDao
					.getCategory(categoryId), categoryVo);
			return categoryVo;
		} catch (Exception e) {
			String msg = "Could not get category " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CatalogService#getProductListByCategory(java.lang.String)
	 */
	@WebXCache(classes = Product.class, operators = CacheToken.EQUALS, properties = "categoryId", values = "#1")
	public List getProductListByCategory(String categoryId)
			throws MyPetStoreException {
		try {
			return dozerConvertUtil.domainObjectsToViewObjects(this.productDao
					.getProductListByCategory(categoryId), ProductVo.class);
		} catch (Exception e) {
			String msg = "Could not get product list " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CatalogService#getProduct(java.lang.String)
	 */
	public ProductVo getProduct(String productId) throws MyPetStoreException {
		try {
			ProductVo productVo = new ProductVo();
			dozerConvertUtil.domainObjectToViewObject(this.productDao
					.getProduct(productId), productVo);
			return productVo;
		} catch (Exception e) {
			String msg = "Could not get product " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CatalogService#getItemListByProduct(java.lang.String)
	 */
	public List getItemListByProduct(String productId)
			throws MyPetStoreException {
		try {
			return dozerConvertUtil.domainObjectsToViewObjects(this.itemDao
					.getItemListByProduct(productId), ItemVo.class);
		} catch (Exception e) {
			String msg = "Could not get item list by product id" + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CatalogService#getItem(java.lang.String)
	 */
	public ItemVo getItem(String itemId) throws MyPetStoreException {
		try {
			ItemVo itemVo = new ItemVo();
			dozerConvertUtil.domainObjectToViewObject(this.itemDao
					.getItem(itemId), itemVo);
			return itemVo;
		} catch (Exception e) {
			String msg = "Could not get item " + e.toString();
			this.logger.error(msg, e);
			throw new MyPetStoreException(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CatalogService#searchProductList(java.util.List)
	 */
	public List searchProductList(List keywords) throws MyPetStoreException {
		if (keywords != null && !keywords.isEmpty()) {
			return dozerConvertUtil.domainObjectsToViewObjects(productDao
					.searchProductList(keywords), ProductVo.class);
		} else {
			return new ArrayList();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CatalogService#saveCategory(mypetstore.web.viewobject.CategoryVo)
	 */
	public void saveCategory(CategoryVo categoryVo) {
		Category category = new Category();
		dozerConvertUtil.viewObjectToDomainObject(categoryVo, category);
		catalogDao.saveCategory(category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CatalogService#deleteCategoryById(String
	 *      categoryId)
	 */
	public void deleteCategoryById(String categoryId)
			throws MyPetStoreException {
		catalogDao.deleteCategoryById(categoryId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.service.CatalogService#doSave(CategoryVo categoryVo)
	 */
	public void doSave(CategoryVo categoryVo) throws MyPetStoreException {
		Category category = new Category();
		dozerConvertUtil.viewObjectToDomainObject(categoryVo, category);
		catalogDao.doSave(category);
	}

}
