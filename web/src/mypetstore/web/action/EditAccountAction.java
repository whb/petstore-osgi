package mypetstore.web.action;

import java.util.List;

import mypetstore.service.CatalogService;
import mypetstore.service.CustomerService;

import mypetstore.web.form.AccountForm;
import mypetstore.web.vo.AccountVo;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * strus action 修改账户
 * 
 * @author zhou wei
 * @since 2008-07-09
 */
public class EditAccountAction extends
		com.sitechasia.webx.core.web.struts1.action.BaseStrutsAction {

	// private CustomerService accountService = (CustomerService)
	// MyPetStoreBeanFactory
	// .getApplicationContext().getBean("customerService");
	// private CatalogService catalogService = (CatalogService)
	// MyPetStoreBeanFactory
	// .getApplicationContext().getBean("catalogService");
	private CustomerService customerService;
	private CatalogService catalogService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AccountForm accountForm = (AccountForm) form;

		AccountVo account = accountForm.getAccount();
		// 更新操作
		customerService.updateAccount(account);

		account = customerService.getAccount(account.getUsername());

		List myList = catalogService.getProductListByCategory(account
				.getFavoriteCategoryId());

		accountForm.setMyList(myList);
		accountForm.setAccount(account);

		return mapping.findForward("success");

	}

}
