package mypetstore.web.action;

import java.util.List;
import mypetstore.exception.MyPetStoreException;
import mypetstore.service.CatalogService;
import mypetstore.utils.StringFormat;
import mypetstore.web.vo.CategoryVo;

import com.sitechasia.webx.core.annotation.Read;
import com.sitechasia.webx.core.web.struts1.action.BaseCRUDAction;

public class CategoryCRUDAction extends BaseCRUDAction<CategoryVo, Integer> {
	private CatalogService catalogService;

	/**
	 * 保存一个CategoryVo的对象
	 * 
	 * @param categoryVo
	 * @return 一个字符串
	 */
	public String save(@Read
	CategoryVo categoryVo) throws MyPetStoreException {
		catalogService.saveCategory(categoryVo);
		return "list";
	}

	/**
	 * 查询CategoryVo记录
	 * 
	 * @return一个字符串
	 */
	public String list() throws MyPetStoreException {
		List list = catalogService.getCategoryList();
		this.getRequest().setAttribute("list", list);
		return "save";
	}

	/**
	 * 删除一个CategoryVo对象
	 * 
	 * @param categoryId
	 * @return一个字符串
	 * @throws Exception
	 */
	public String delete(@Read(key = "categoryId")
	String categoryId) throws MyPetStoreException {
		catalogService.deleteCategoryById(categoryId);
		return "list";
	}

	/**
	 * 更新一个CategoryVo对象
	 * 
	 * @return一个字符串
	 */
	public String update(@Read(key = "categoryId")
	String categoryId) throws MyPetStoreException {
		CategoryVo categoryVo = catalogService.getCategory(categoryId);
		categoryVo.setDescription(StringFormat.toHTMLString(categoryVo
				.getDescription()));
		this.getRequest().setAttribute("currentEntity", categoryVo);
		return "update";
	}

	/**
	 * 修改一条记录
	 * 
	 * @param categoryVo
	 * @return
	 * @throws MyPetStoreException
	 */
	public String doEdit(@Read
	CategoryVo categoryVo) throws MyPetStoreException {
		catalogService.doSave(categoryVo);
		saveMessage("entity.saved", null);
		return "list";
	}

	public CatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
}
