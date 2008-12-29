package mypetstore.web.dwr.facade;

import java.util.List;

import mypetstore.service.CatalogService;
import mypetstore.web.vo.CategoryVo;

public class CatalogFacade {
	private CatalogService catalogService;

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	/**
	 * 查询所有的Category
	 * 
	 * @return 所有的CategoryVo的List
	 */
	@SuppressWarnings("unchecked")
	public List getCategoryList() {
		return catalogService.getCategoryList();
	}

	/**
	 * 查询指定ID的Category
	 * 
	 * @return 指定ID的CategoryVo
	 */
	public CategoryVo getCategory(String categoryId) {
		return catalogService.getCategory(categoryId);
	}

	/**
	 * 删除指定ID的Category
	 * 
	 * @return 
	 */
	public void deleteCategoryById(String categoryId) {
		catalogService.deleteCategoryById(categoryId);
	}
	
	/**
	 * 保存的新的Category
	 * 
	 * @return 
	 */
	public void saveCategory(CategoryVo categoryVo) {
		catalogService.saveCategory(categoryVo);
	}
}
