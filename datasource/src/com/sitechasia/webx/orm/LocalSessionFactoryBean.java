/**
 *
 * Copyright (c) 2006- CE, Inc.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * CE Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with CE.
 */
package com.sitechasia.webx.orm;

import org.springframework.core.io.*;
import org.apache.commons.logging.*;
import java.util.*;
/**
 继承并扩展spring的这个类的原因：
 webx.0的hbm资源是分布与多个bundle,
 由一个专门的Activator负责寻找每个bundle里面的hbm,
 并将
 @author 耿韶光
 @since 2.0
 @see LocalSessionFactoryBean
*/
public class LocalSessionFactoryBean extends
		org.springframework.orm.hibernate3.LocalSessionFactoryBean {
	private static Resource[] res;
	private Log log = LogFactory.getLog(this.getClass());
	/**
	该方法由查找hbm的那个Activator调用
	@param r 找到的hbm资源列表
	*/
	public static void setMap(List<Resource> r) {

		if (res != null) {

			updated = true;
		}
		res = r.toArray(new Resource[r.size()]);

	}

	private static boolean updated = false;
	/**
	该方法本来是spring.hibernate内部的方法,hibernate在这里完成加载hbm文件,
	重载后,在运行hibernate的加载方法前,先指定hbm资源的位置
	@throws Exception 该异常由父类规定
	@see LocalSessionFactoryBean
	*/
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("====afterPropertiesSet====");
		super.setMappingLocations(res);
		super.afterPropertiesSet();
	}
	/**
	该方法是改变了spring.hibernate的行为：
	在hbm资源发生变化时，再次调用初始化方法
	@return Object 由父类规定，返回该FactoryBean"创造"的Bean
	@see LocalSessionFactoryBean
	*/
	@Override
	public Object getObject() {
		if (updated)
			try {
				log.info("!!!!reload map......");
				afterPropertiesSet();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return super.getObject();
	}
}
