package mypetstore.web;

import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.directwebremoting.spring.DwrSpringServlet;
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
 * 注册Web环境
 * 
 * @author Administrator
 * 
 */
public class InitWebDWRSpringServlet implements ApplicationContextAware {
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

		initDWRSpringServlet(httpContext);
	}

	/**
	 * 使用DwrSpringServlet初始化DWR，使用DWR配置文件dwr.xml
	 */
	private void initDWRSpringServlet(HttpContext httpContext)
			throws ServletException, NamespaceException {
		DwrSpringServlet dwrSpringServlet = new DwrSpringServlet();
		// 显式指定DwrSpringServlet使用的Spring ApplicationContext
		dwrSpringServlet
				.setApplicationContext(getDwrDependencyApplicationContext());

		Properties initParams = new Properties();
		initParams.put("debug", "true");
		Servlet dwrSpringActionServlet = new ContextPathServletAdaptor(
				dwrSpringServlet, url);
		httpService.registerServlet(url + "/dwr", dwrSpringActionServlet,
				initParams, httpContext);
	}
}
