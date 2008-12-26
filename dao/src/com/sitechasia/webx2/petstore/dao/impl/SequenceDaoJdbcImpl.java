/**
 * @{#} SequenceDaoJdbcImpl.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.dao.impl;

import java.util.List;
import com.sitechasia.webx.core.dao.jdbc.BaseJdbcDomainDao;
import com.sitechasia.webx2.petstore.dao.SequenceDao;
import com.sitechasia.webx2.petstore.model.Sequence;

/**
 * Sequence DAO 接口实现类.
 * 
 * @see SequenceDao
 * 
 * @version 1.0
 * @since JDK1.5
 */
public class SequenceDaoJdbcImpl extends BaseJdbcDomainDao<Sequence> implements
		SequenceDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mypetstore.dao.SequenceDao#getSeqNum(java.lang.Object)
	 * @see com.sitechasia.webx.core.dao.jdbc.BaseJdbcDomainDao#listAll(int,int)
	 */
	public int getSeqNum(final Object parameter) {
		Sequence sequence = new Sequence();

		int seqNum = -1;
		// 注意 这块使用到com.sitechasia.webx.core.dao.jdbc.BaseJdbcDomainDao的listAll方法
		List list = listAll(1, 1);
		if (list == null || list.size() < 1) {

		} else {
			sequence = (Sequence) list.get(0);
			sequence.increment();
			seqNum = sequence.getSeqnum();
			saveOrUpdate(sequence);
		}

		return seqNum;
	}
}
