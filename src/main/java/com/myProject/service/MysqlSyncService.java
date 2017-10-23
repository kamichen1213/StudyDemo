package com.myProject.service;

import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.constant.Constant;
import com.myProject.dao.mybatis.ITMyPvReportDao;
import com.myProject.entity.RedisKeyValue;
import com.myProject.entity.TMyPvReport;

@Service
public class MysqlSyncService {

	@Autowired
	private ITMyPvReportDao myPvReportDao;
	
	@Autowired
	private RedisOperationService redisOperationService;
	
	public static final Logger logger = Logger.getLogger(MysqlSyncService.class);
	
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	
	/**
	 * redis -> Mysql
	 * 
	 */
	public void syncService(){
		List<RedisKeyValue> result = new ArrayList<RedisKeyValue>();
		result = redisOperationService.getAllRecordKv();
		Iterator<RedisKeyValue> it = result.iterator();
		while(it.hasNext()){
			//init
			RedisKeyValue current = it.next();
			redisToMySqlRecordSync(current);
		}		
	}
	
	/**
	 * redis -> Mysql
	 * 
	 */
	public void redisToMySqlRecordSync(RedisKeyValue redisKeyVal){
		//init
		BigInteger opCount = new BigInteger("0");
		BigInteger employeeId = new BigInteger("0");
		String opAction = new String();
		java.util.Date sysdate = null;
		Date opDate = null;
		String opType = new String();
		//current val
		RedisKeyValue current = redisKeyVal;
		String redisKey = current.getKey();
		if(current.getValue() != null){
			opCount = new BigInteger(current.getValue());
		}else{
			opCount = new BigInteger("0");
		}
		//mapping
		String[] keySplit = redisKey.split(Constant.SEPERATOR);
		if(keySplit.length < 5){
			logger.info("keySplit["+(keySplit==null?"null":keySplit)+"]'s length["+keySplit.length+"] error!");
			return;
		}
		opAction = keySplit[1];
		opType = keySplit[2];
		try{
			employeeId = new BigInteger(keySplit[3]);
		}catch(NumberFormatException e){
			logger.info("keySplit["+(keySplit==null?"null":keySplit)+"]'s employeeId["+keySplit[3]+"] error!");
			return;
		}
		try{
			sysdate = format.parse(keySplit[4]);
		}catch(ParseException e){
			logger.info("keySplit["+(keySplit==null?"null":keySplit)+"]'s date["+keySplit[4]+"] error!");
			return;
		}
		opDate =  new Date(sysdate.getTime());
		//set value
		TMyPvReport addObj = new TMyPvReport();
		addObj.setEmployeeId(employeeId);
		addObj.setOpAction(opAction);
		addObj.setOpCount(opCount);
		addObj.setOpDate(opDate);
		addObj.setOpKey(redisKey);
		addObj.setOpType(opType);
		logger.info("myPvReport["+redisKey+"] addObj set completed!");
		Integer objExistsCount = myPvReportDao.getCountByCondition(addObj.getOpKey());
		logger.info("myPvReport["+redisKey+"] count:"+objExistsCount);
		if(objExistsCount > 0){
			myPvReportDao.update(addObj);
		}else{
			myPvReportDao.add(addObj);
		}
		logger.info("myPvReport["+redisKey+"] addObj process completed!");
	}
	
	
}
