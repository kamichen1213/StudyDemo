package com.myProject.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public abstract class AbstractBaseRedisDao<K, V> {  
    
    @Autowired  
    protected RedisTemplate<K, V> redisTemplate;  
  
    /** 
     * redisTemplate 
     * @param redisTemplate the redisTemplate to set 
     */  
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
      
    /** 
     * RedisSerializer 
     * <br>------------------------------<br> 
     */  
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }   
    
}  
