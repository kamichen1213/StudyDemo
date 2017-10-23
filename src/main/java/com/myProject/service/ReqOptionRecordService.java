package com.myProject.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.constant.Constant;
import com.myProject.dao.redis.IBaseDao;
import com.myProject.entity.DolOperationEntity;
import com.myProject.entity.RecordPO;
/**
 * redis operation.
 * @author Administrator
 *
 */
@Service
public class ReqOptionRecordService {

	@Autowired
	private IBaseDao redisDao;
	
	public static final Logger logger = Logger.getLogger(ReqOptionRecordService.class);
	
	public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final String SEPERATOR = "_";
	
	public DolOperationEntity record(RecordPO record){
		//define
		Date sysdate = new Date();
		Long opCount = 0L;
		String key = new String();
		DolOperationEntity options = record.getEntity();
		options.setOpDate(sysdate);
		//operation
		key = keyGenerator(options);
		opCount = opCountProcess(key);
		record.setKey(key);
		record.setVal(String.valueOf(opCount));
		logger.info("Record:key["+key+"],val["+opCount+"]");
		//result
		options.setOpDate(sysdate);
		options.setOpCount(opCount);
		return options;
	}
	
	/**
	 * Redis record Key generator
	 * 
	 * @param options
	 * @return
	 */
	public String keyGenerator(DolOperationEntity options){
		StringBuilder keyB = new StringBuilder();
		String key = new String();
		//ActionId
		if(options.getActionId() != null	&&	!"".equals(keyB)){
			keyB.append(Constant.REDIS_RECORD_KEY_HEAD);
			keyB.append(options.getActionId());
			keyB.append(SEPERATOR);
			//
			if(options.getOpType() != null	&&	!"".equals(keyB)){
				keyB.append(options.getOpType());
			}else{
				keyB.append("undefined");
			}
			keyB.append(SEPERATOR);
			//
			if(options.getEmployeeId() != null	&&	!"".equals(keyB)){
				keyB.append(options.getEmployeeId());
			}else{
				keyB.append("unknown");
			}
			keyB.append(SEPERATOR);
			keyB.append(df.format(options.getOpDate()));
			key = keyB.toString();		
			logger.info("keyGenerator complete : " + key);
		}else{
			logger.info("keyGenerator throw : actionId is null!");
			return null;
		}
		return key;
	}
	
	/**
	 * 
	 * 
	 * @param key
	 * @return
	 */
	public Long opCountProcess(String key){
		Long opCount = 1L;
		if(key != null){
			String val = redisDao.getVal(key);
			if(val == null || "null".equals(val)){
				redisDao.setVal(key, String.valueOf(1L));
				logger.info("Operation["+key+"] first be used!");
				return 1L;
			}else{
				opCount = Long.valueOf(redisDao.getVal(key));
				opCount = opCount + 1;
				redisDao.setVal(key, String.valueOf(opCount));
			}
		}else{
			logger.warn("opCountProcess occurs to Null Key!");
		}
		return opCount;
	}
}
