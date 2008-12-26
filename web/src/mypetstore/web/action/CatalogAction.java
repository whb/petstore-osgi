/**
 * Create time Jul 10, 2008 9:44:49 AM
 */
package mypetstore.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypetstore.service.CatalogService;
import mypetstore.web.form.CatalogForm;
import mypetstore.web.vo.CategoryVo;
import mypetstore.web.vo.ItemVo;
import mypetstore.web.vo.ProductVo;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * <p>
 * Title: CatalogAction
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author WangYonghui
 * @version 1.0
 */
public class CatalogAction extends DispatchAction {
	private CatalogService catalogService;

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("success");
	}

	/**
	 * 方法说明：得到返回符合条件的查询结果的动物,用extrme标签来展示这些记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchProducts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CatalogForm catalogForm = (CatalogForm) form;
		String keyword = catalogForm.getKeyword();
		if (keyword == null || keyword.length() < 1) {
			request
					.setAttribute("message",
							"Please enter a keyword to search for, then press the search button.");
			return mapping.findForward("failure");
		} else {
			List keywords = new ArrayList<String>();
			keywords.add(keyword.toLowerCase());
			// 得到所有的List
			List allProductList = catalogService.searchProductList(keywords);
			// catalogForm.setAllProductList(allProductList);
			// request.setAttribute("allProductList", allProductList);
			// System.out.println(allProductList);
			catalogForm.setProductList(allProductList);
			return mapping.findForward("success");
		}
	}

	/**
	 * 方法说明：得到返回属于某个种类的动物,用extrme标签来展示属于这个类的动物
	 * 
	 * @return
	 * @throws MyPetStoreException
	 */
	public ActionForward viewCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CatalogForm catalogForm = (CatalogForm) form;
		String categoryId = catalogForm.getCategoryId();
		if (categoryId != null) {
			List list = catalogService.getProductListByCategory(categoryId);
			CategoryVo category = catalogService.getCategory(categoryId);
			catalogForm.setProductList(list);
			catalogForm.setCategory(category);
		}
		return mapping.findForward("success");
	}

	public ActionForward viewProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CatalogForm catalogForm = (CatalogForm) form;
		String productId = catalogForm.getProductId();
		if (productId != null) {
			List list = catalogService.getItemListByProduct(productId);
			ProductVo product = catalogService.getProduct(productId);
			catalogForm.setItemList(list);
			catalogForm.setProduct(product);
		}
		return mapping.findForward("success");
	}

	public ActionForward viewItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CatalogForm catalogForm = (CatalogForm) form;
		String itemId = catalogForm.getItemId();
		if (itemId != null) {
			ItemVo item = catalogService.getItem(itemId);
			ProductVo product = item.getProduct();
			catalogForm.setItem(item);
			catalogForm.setProduct(product);
		}
		return mapping.findForward("success");
	}
}
