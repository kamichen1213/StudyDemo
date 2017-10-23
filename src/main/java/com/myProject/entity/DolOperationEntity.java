package com.myProject.entity;

import java.io.Serializable;
import java.util.Date;

public class DolOperationEntity implements Serializable{

	/**
	 * serialVersionID
	 */
	private static final long serialVersionUID = 1L;

	private String actionId;
	
	private String opType;
	
	private Date opDate;
	
	private String employeeId;
	
	private Long opCount;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public Date getOpDate() {
		return opDate;
	}

	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Long getOpCount() {
		return opCount;
	}

	public void setOpCount(Long opCount) {
		this.opCount = opCount;
	}
	
	
}
