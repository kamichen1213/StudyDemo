package com.myProject.dao.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.entity.TMyPvReport;
import com.myProject.entity.mapper.TMyPvReportMapper;

@Repository(value = "TMyPvReportDao")
public class TMyPvReportDao implements ITMyPvReportDao{
	
	public static final Logger logger = Logger.getLogger(TMyPvReportDao.class);
	
	@Autowired
	private TMyPvReportMapper mapper;
	
	@Override
	public List<TMyPvReport> find(){
		List<TMyPvReport> rs = new ArrayList<TMyPvReport>();
		String sql = "select * from t_my_pv_report";
		rs = mapper.operateReturnBeans(sql);
		return rs;
	}
	
	@Transactional
	@Override
	public void add(TMyPvReport obj) {
		mapper.insertObj(obj);
		logger.info("Insert success!");
	};
	
	@Override
	public Integer getCountByCondition(String opKey){
		if(StringUtils.isEmpty(opKey)){
			logger.info("count error! opKey is null!");
			return null;
		}
		Integer count = mapper.getCountByCondition(opKey);
		logger.info("select count success!");
		return count;
	}
	
	@Transactional
	@Override
	public void update(TMyPvReport po) {
		if(po == null){
			logger.info("update error! Po is null!");
			return ;
		}
		mapper.updateObj(po);
		logger.info("select update success!");
	};

}
