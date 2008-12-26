package mypetstore.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * strus action 注销登录
 * 
 * @author zhou wei
 * @since 2008-07-09
 */
public class SignoffAction extends
		com.sitechasia.webx.core.web.struts1.action.BaseStrutsAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute("signonForm");
		request.getSession().removeAttribute("accountForm");
		return mapping.findForward("success");
	}
}
