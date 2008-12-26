/**
 * @{#} Sequence.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package com.sitechasia.webx2.petstore.model;

import com.sitechasia.webx.core.model.IDomainObject;

/**
 * Profile domain object.
 * 
 * 
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class Sequence implements IDomainObject {

	private String id;
	private int seqnum;

	public Sequence() {
	}

	public int getSeqnum() {
		return this.seqnum;
	}

	public void setSeqnum(int newSeqnum) {
		this.seqnum = newSeqnum;
	}

	// 更新序列号
	public void increment() {
		this.seqnum++;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
