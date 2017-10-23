package com.myProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.constant.Constant;
import com.myProject.dao.redis.IBaseDao;
import com.myProject.entity.RedisKeyValue;
@Service
public class RedisOperationService {
	@Autowired
	private IBaseDao redisDao;
	
	public List<RedisKeyValue> getAllRecordKv(){
		List<RedisKeyValue> result = new ArrayList<RedisKeyValue>();
		result = redisDao.getAllKv(Constant.REDIS_RECORD_KEY_HEAD);
		return result;
	}
}
