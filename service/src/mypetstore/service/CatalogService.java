package mypetstore.service;

import java.util.List;



import mypetstore.exception.MyPetStoreException;
import mypetstore.web.vo.CategoryVo;
import mypetstore.web.vo.ItemVo;
import mypetstore.web.vo.ProductVo;

/**
 * Catalog Service 接口.
 * <p>
 * 该Service包含catalog目录相关的业务服务
 *
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
public interface CatalogService {

	/**
	 *
	 * 获得CategoryVo结果集
	 *
	 * @return List<CategoryVo>
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public List getCategoryList() throws MyPetStoreException;

	/**
	 *
	 * 根据CategoryVo主键获得CategoryVo
	 *
	 * @param categoryId
	 *            CategoryVo主键
	 * @return CategoryVo
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public CategoryVo getCategory(String categoryId) throws MyPetStoreException;

	/**
	 * 根据CategoryVo主键获得ProductVo结果集
	 *
	 * @param categoryId
	 * @return List<ProductVo>
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public List getProductListByCategory(String categoryId)
			throws MyPetStoreException;

	/**
	 *
	 * 根据ProductVo主键获得ProductVo
	 *
	 * @param productId
	 * @return Product
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public ProductVo getProduct(String productId) throws MyPetStoreException;

	/**
	 *
	 * 根据ProductVo主键获得ItemVo结果集
	 *
	 * @param productId
	 * @return List<ItemVo>
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public List getItemListByProduct(String productId)
			throws MyPetStoreException;

	/**
	 *
	 * 根据ItemVo主键获得ItemVo
	 *
	 * @param itemId
	 * @return ItemVo
	 * @throws MyPetStoreException
	 * @see MyPetStoreException
	 */
	public ItemVo getItem(String itemId) throws MyPetStoreException;

	/**
	 * 根据关键字获得ProductVo结果集
	 *
	 * @param keywords
	 *            查询关键字
	 * @return List<ProductVo>
	 * @throws MyPetStoreException
	 */
	public List searchProductList(List keywords) throws MyPetStoreException;
	/**
	 *
	 * 方法说明：向数据库保存一条记录Category
	 * @author SunZhenying
	 * @param categoryVo
	 * @see
	 */
	public void saveCategory(CategoryVo categoryVo);
	
	/**
	 * 从数据库删除一条记录
	 * @param categoryId
	 * @throws MyPetStoreException
	 */
	public void deleteCategoryById(String categoryId)throws MyPetStoreException;
	/**
	 * 向数据库更新一条记录
	 * @param categoryVo
	 * @throws MyPetStoreException
	 */
	public void doSave(CategoryVo categoryVo)throws MyPetStoreException;
}
