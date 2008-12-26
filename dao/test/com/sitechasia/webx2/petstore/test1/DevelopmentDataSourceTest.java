/**
 * @{#} DevelopmentDataSourceTest.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.test1;

import java.util.List;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import com.sitechasia.webx.core.utils.datasource.DevelopmentConnection;
import com.sitechasia.webx.core.utils.datasource.DevelopmentDataSource;

/**
 * testing DevelopmentDataSource
 * 
 * @author zhou wei
 * @see DevelopmentDataSource
 */
public class DevelopmentDataSourceTest extends TestCase {
	ApplicationContext beanFactory = null;
	static volatile DevelopmentDataSource dataSource = null;

	public void setUp() throws Exception {
		beanFactory = MyPetStoreBeanFactory.getApplicationContext();

		dataSource = (DevelopmentDataSource) beanFactory.getBean("dataSource");
	}

	/**
	 * 测试监控数据库连接
	 * 
	 * 
	 * @see
	 */
	public void testListenerConnection() throws Exception {
		// developmentDateSourceWait.getCategoryList();
		DevelopmentConnection conn = (DevelopmentConnection) dataSource
				.getConnection();
		printStackTrace();
		conn.close();
	}

	public void printStackTrace() {
		if (dataSource != null)
			this.printStacks();
	}

	/**
	 * 打印堆栈的信息
	 * 
	 * 
	 * @param stackTraceElenments
	 *            堆栈跟踪中的元素
	 * @see
	 */

	public void printStacks() {
		List<DevelopmentConnection> conn = dataSource.getConnections();
		if (conn != null) {
			System.err.println("当前的数据源为: " + dataSource.hashCode()
					+ " 连接数为*******************************" + conn.size());
			for (int j = 0; j < conn.size(); ++j) {
				DevelopmentConnection devconn = (DevelopmentConnection) conn
						.get(j);
				StackTraceElement[] st = devconn.getStackTraceElenments();
				if (st != null)
					for (int i = 0; i < st.length; ++i) {
						StackTraceElement element = st[i];
						if (element.toString().startsWith("mypetstore")
								|| element.toString().indexOf(
										"com.sitechasia.webx") != -1)
							System.out.println("当前的数据源为: "
									+ dataSource.hashCode() + " .当前的连接为: "
									+ devconn.hashCode() + " 调用了" + element);
					}
			}
		}
	}

}
