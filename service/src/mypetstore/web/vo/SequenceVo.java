/**
 * @{#} SequenceVo.java Create on 2008-6-4 下午03:21:31
 *
 * Copyright (c) 2006- by CE.
 */
package mypetstore.web.vo;

import com.sitechasia.webx.core.model.IViewObject;

/**
 * Profile view object.
 *
 * @author zhou wei
 * @version 1.0
 * @since JDK1.5
 */
@SuppressWarnings("serial")
public class SequenceVo implements IViewObject{

	private String id;
	private int seqnum;

	public SequenceVo() {
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

	public boolean validate() {
		return false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
