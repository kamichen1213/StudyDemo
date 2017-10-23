package com.myProject.dao.mybatis;

import java.util.List;

import com.myProject.entity.TMyPvReport;

public interface ITMyPvReportDao {

	public List<TMyPvReport> find();
	
	public void add(TMyPvReport obj);
	
	public Integer getCountByCondition(String opKey);

	public void update(TMyPvReport po);
}
