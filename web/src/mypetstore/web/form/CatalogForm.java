/**
 * Create time Jul 9, 2008 3:08:37 PM
 */
package mypetstore.web.form;

import java.util.List;

import mypetstore.web.vo.CategoryVo;
import mypetstore.web.vo.ItemVo;
import mypetstore.web.vo.ProductVo;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: CatalogForm</p>
 * <p>Description: </p>
 *
 * @author WangYonghui
 * @version 1.0
 */
public class CatalogForm extends ActionForm{
	private String keyword;
	private String pageDirection;

	private String categoryId;
	private String name;
	private String description;
	private CategoryVo category = new CategoryVo();
	private List categoryList;

	private String productId;
	private ProductVo product = new ProductVo();
	private List productList;

	private String itemId;
	private ItemVo item = new ItemVo();
	private List itemList;
	private List allProductList;//当前页的所有记录
	/**
	 * @return Returns the keyword.
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword The keyword to set.
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return Returns the pageDirection.
	 */
	public String getPageDirection() {
		return pageDirection;
	}
	/**
	 * @param pageDirection The pageDirection to set.
	 */
	public void setPageDirection(String pageDirection) {
		this.pageDirection = pageDirection;
	}
	/**
	 * @return Returns the categoryId.
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId The categoryId to set.
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the category.
	 */
	public CategoryVo getCategory() {
		return category;
	}
	/**
	 * @param category The category to set.
	 */
	public void setCategory(CategoryVo category) {
		this.category = category;
	}
	/**
	 * @return Returns the categoryList.
	 */
	public List getCategoryList() {
		return categoryList;
	}
	/**
	 * @param categoryList The categoryList to set.
	 */
	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}
	/**
	 * @return Returns the productId.
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return Returns the product.
	 */
	public ProductVo getProduct() {
		return product;
	}
	/**
	 * @param product The product to set.
	 */
	public void setProduct(ProductVo product) {
		this.product = product;
	}
	/**
	 * @return Returns the productList.
	 */
	public List getProductList() {
		return productList;
	}
	/**
	 * @param productList The productList to set.
	 */
	public void setProductList(List productList) {
		this.productList = productList;
	}
	/**
	 * @return Returns the itemId.
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId The itemId to set.
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return Returns the item.
	 */
	public ItemVo getItem() {
		return item;
	}
	/**
	 * @param item The item to set.
	 */
	public void setItem(ItemVo item) {
		this.item = item;
	}
	/**
	 * @return Returns the itemList.
	 */
	public List getItemList() {
		return itemList;
	}
	/**
	 * @param itemList The itemList to set.
	 */
	public void setItemList(List itemList) {
		this.itemList = itemList;
	}
	/**
	 * @return Returns the allProductList.
	 */
	public List getAllProductList() {
		return allProductList;
	}
	/**
	 * @param allProductList The allProductList to set.
	 */
	public void setAllProductList(List allProductList) {
		this.allProductList = allProductList;
	}
	
	public void clear() {
		keyword = null;
		pageDirection = null;

		categoryId = null;
		category = null;
		categoryList = null;

		productId = null;
		product = null;
		productList = null;

		itemId = null;
		item = null;
		itemList = null;
	}
}
