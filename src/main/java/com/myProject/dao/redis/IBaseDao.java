package com.myProject.dao.redis;

import java.util.List;

import com.myProject.entity.RedisKeyValue;

public interface IBaseDao {  
    
    public String getVal(String key);
    
    public void setVal(String key,Object val);

    public List<RedisKeyValue> getAllKv(final String keyHead);
}  

