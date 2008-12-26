/**
 * @{#} SequenceDao.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao;

/**
 * Sequence DAO 接口.
 * <p>
 * 该DAO包含Sequence相关的数据访问逻辑
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
public interface SequenceDao {
	/**
	 * 
	 * 根据查询参数获得Sequence序列号
	 * 
	 * @param parameter
	 *            查询参数
	 * @return int整数
	 */
	int getSeqNum(final Object parameter);
}
