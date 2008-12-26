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
 * strus action 用户登陆
 * 
 * @author zhou wei
 * @since 2008-07-09
 */
public class SignonAction extends
		com.sitechasia.webx.core.web.struts1.action.BaseStrutsAction {

	private CustomerService customerService;
	private CatalogService catalogService;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AccountForm accountForm = (AccountForm) form;
		AccountVo account = accountForm.getAccount();

		account = customerService.signon(account.getUsername(), account
				.getPassword());

		if (account == null || account == null) {

			request.setAttribute("message",
					"Invalid username or password.  Signon failed.");

			accountForm.setAccount(account);
			accountForm.setRepeatedPassword(null);
			accountForm.setPageDirection(null);
			accountForm.setMyList(null);
			accountForm.setAuthenticated(false);
			return mapping.findForward("failure");
		} else {
			account.setPassword(null);

			List myList = catalogService.getProductListByCategory(account
					.getFavoriteCategoryId());

			accountForm.setMyList(myList);
			accountForm.setAccount(account);

			boolean authenticated = true;
			accountForm.setAuthenticated(authenticated);
			return mapping.findForward("success");
		}
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

}
