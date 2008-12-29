package mypetstore.web;

import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.directwebremoting.servlet.DwrServlet;
import org.directwebremoting.spring.SpringCreator;
import org.eclipse.equinox.http.helper.BundleEntryHttpContext;
import org.eclipse.equinox.http.helper.ContextPathServletAdaptor;
import org.eclipse.equinox.http.helper.FilterServletAdaptor;
import org.eclipse.equinox.http.helper.ResourceServlet;
import org.eclipse.equinox.jsp.jasper.JspServlet;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * OSGi下替代web.xml注册Web环境；适用于DwrServlet来初始化DWR。
 * <ul>
 * 注册Web环境步骤：
 * <li>首先由Spring容器注入必须的属性；</li>
 * <li>在Spring DM完成ApplicationContext创建后，回调setApplicationContext来执行初始化。</li>
 * </ul>
 * 
 * @author wuhaibo
 * 
 */
public class InitWebDWRServlet implements ApplicationContextAware {
	private static final String WEB_RESOURCES_PATH = "/web";

	private BundleContext bundleContext;
	private HttpService httpService;
	private String url;
	private ApplicationContext dwrDependencyApplicationContext;

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private ApplicationContext getDwrDependencyApplicationContext() {
		return dwrDependencyApplicationContext;
	}

	private void setDwrDependencyApplicationContext(
			ApplicationContext springApplicationContext) {
		this.dwrDependencyApplicationContext = springApplicationContext;
	}

	/**
	 * Spring ApplicationContext创建完成后回调此方法，程序此时来初始化Web配置。
	 */
	@Override
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		setDwrDependencyApplicationContext(ac);

		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() throws ServletException, NamespaceException,
			InvalidSyntaxException {
		HttpContext httpContext = new BundleEntryHttpContext(bundleContext
				.getBundle(), WEB_RESOURCES_PATH);

		Filter extableFilter = new org.extremecomponents.filter.ExtremeTableFilter();

		/**
		 * 注册静态资源
		 */
		ResourceServlet resourceServlets = new ResourceServlet("/");
		httpService.registerServlet(url, resourceServlets, null, httpContext);

		/**
		 * 注册jsp
		 */
		JspServlet jspServlet = new JspServlet(bundleContext.getBundle(),
				WEB_RESOURCES_PATH);
		Servlet adaptedJspServlet = new ContextPathServletAdaptor(jspServlet,
				url);
		FilterServletAdaptor extableAdapter = new FilterServletAdaptor(
				extableFilter, null, adaptedJspServlet);
		httpService.registerServlet(url + "/*.jsp", extableAdapter, null,
				httpContext);

		/**
		 * 注册.do
		 */
		Servlet adaptedActionServlet = new ContextPathServletAdaptor(
				new ActionServlet(), url);
		FilterServletAdaptor extableAdaptor = new FilterServletAdaptor(
				extableFilter, null, adaptedActionServlet);
		httpService.registerServlet(url + "/*.shtml", extableAdaptor, null,
				httpContext);

		initDWRServlet(httpContext);
	}

	/**
	 * 使用DwrServlet初始化DWR，使用DWR配置文件dwr.xml
	 */
	private void initDWRServlet(HttpContext httpContext)
			throws ServletException, NamespaceException {
		// 显式指定SpringCreator使用的Spring ApplicationContext
		SpringCreator
				.setOverrideBeanFactory(getDwrDependencyApplicationContext());

		// 设置DwrServlet的配置参数
		Properties initParams = new Properties();
		initParams.put("debug", "true");
		initParams.put("activeReverseAjaxEnabled", "true");
		// 如果不使用默认的dwr.xml配置文件，可以用以下方式指定一个或多个配置文件
		// initParams.put("config-1", "/WEB-INF/dwr-conf/dwr1.xml");
		// initParams.put("config-2", "/WEB-INF/dwr-conf/dwr2.xml");
		// initParams.put("config-3", "/WEB-INF/dwr-conf/dwr3.xml");

		Servlet dwrActionServlet = new ContextPathServletAdaptor(
				new DwrServlet(), url);
		httpService.registerServlet(url + "/dwr", dwrActionServlet, initParams,
				httpContext);
	}
}
