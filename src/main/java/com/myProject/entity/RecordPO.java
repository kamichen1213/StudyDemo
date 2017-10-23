package com.myProject.entity;

import java.io.Serializable;

public class RecordPO implements Serializable{

	/**
	 * serialId
	 */
	private static final long serialVersionUID = 1L;

	private String key;
	
	private String val;
	
	private String errMsg;
	
	private DolOperationEntity entity;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public DolOperationEntity getEntity() {
		return entity;
	}

	public void setEntity(DolOperationEntity entity) {
		this.entity = entity;
	}
	
	
}
