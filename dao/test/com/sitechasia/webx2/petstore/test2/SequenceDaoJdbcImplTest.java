/**
 * @{#} SequenceDaoJdbcImplTest.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.test2;

import junit.framework.TestCase;
import com.sitechasia.webx2.petstore.dao.SequenceDao;
import org.springframework.context.ApplicationContext;

/**
 * testing SequenceDaoJdbcImpl
 * 
 * @author zhou wei
 * 
 * @see SequenceDaoJdbcImpl
 */
public class SequenceDaoJdbcImplTest extends
		com.sitechasia.webx.test.AbstractTestCase {
	ApplicationContext beanFactory = null;

	SequenceDao sequenceDao = null;

	public void setUp() {
		super.setUp();
		beanFactory = MyPetStoreBeanFactory2.getApplicationContext();
		sequenceDao = (SequenceDao) beanFactory.getBean("sequenceDao");
	}

	/**
	 * 测试获得一个生成的主键的序号 ：
	 * 
	 */
	public void testGetSeqNum() {
		int i = sequenceDao.getSeqNum("orderseq");
		assertEquals(new Integer(i) instanceof Integer, true);
	}

}
