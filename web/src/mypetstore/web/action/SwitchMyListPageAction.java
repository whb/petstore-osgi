package mypetstore.web.action;

import java.util.List;

import mypetstore.web.form.AccountForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * strus action 用于账户分页
 * 
 * @author zhou wei
 * @since 2008-07-09
 */
public class SwitchMyListPageAction extends
		com.sitechasia.webx.core.web.struts1.action.BaseStrutsAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AccountForm accountForm = (AccountForm) form;
		String pageDirection = accountForm.getPageDirection();
		List myList = accountForm.getMyList();
		accountForm.setMyList(myList);
		return mapping.findForward("success");
	}
}
