package com.myProject.entity;

import java.math.BigInteger;
import java.sql.Date;

public class TMyPvReport {

	private BigInteger id;
	
	private String opKey;
	
	private String opAction;
	
	private String opType;
	
	private BigInteger employeeId;
	
	private Date opDate;
	
	private BigInteger opCount;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getOpKey() {
		return opKey;
	}

	public void setOpKey(String opKey) {
		this.opKey = opKey;
	}

	public String getOpAction() {
		return opAction;
	}

	public void setOpAction(String opAction) {
		this.opAction = opAction;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public BigInteger getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(BigInteger employeeId) {
		this.employeeId = employeeId;
	}

	public Date getOpDate() {
		return opDate;
	}

	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}

	public BigInteger getOpCount() {
		return opCount;
	}

	public void setOpCount(BigInteger opCount) {
		this.opCount = opCount;
	}
		
}
