package com.sitechasia.webx2.petstore.test1;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyPetStoreBeanFactory {

	private static final String[] resource = { "com/sitechasia/webx2/petstore/test1/applicationContext.xml" };

	private static ApplicationContext ctx = null;

	static {
		try {
			ctx = new ClassPathXmlApplicationContext(resource);
			
			Properties props = Resources
					.getResourceAsProperties("resource/config/jdbc.properties");
			String url = props.getProperty("url");
			String driver = props.getProperty("driver");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			String schemaScript = props.getProperty("schemaScript");
			String dataloadScript = props.getProperty("dataloadScript");
			//
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url, username,
					password);
			try {
				ScriptRunner runner = new ScriptRunner(conn, false, false);
				runner.setErrorLogWriter(null);
				runner.setLogWriter(null);
				runner.runScript(Resources.getResourceAsReader(schemaScript));
				runner.runScript(Resources.getResourceAsReader(dataloadScript));
			} finally {
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Description.  Cause: " + e, e);
		}

	}

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}
	
//	public static void main(String[] str){
//		getApplicationContext();
//	}

}
