package com.sitechasia.webx2.com.sitechasia.webx.demo.struts.action.processor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DelegatingRequestProcessor extends RequestProcessor implements
		ApplicationContextAware {
	private static ApplicationContext ac;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		ac = arg0;
	}

	/**
	 * TODO 方法说明：创建action
	 * 
	 * @author mashaojing
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ActionMapping
	 * @return 返回创建的Action
	 * @throws IOException
	 */
	protected Action processActionCreate(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException {

		String prefix = mapping.getModuleConfig().getPrefix();
		String path = mapping.getPath();
		String beanName = prefix + path;
		return (Action) ac.getBean(beanName);
	}

}
