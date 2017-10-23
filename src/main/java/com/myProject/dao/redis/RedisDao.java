package com.myProject.dao.redis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.myProject.entity.RedisKeyValue;

@Service("redisDao")
public class RedisDao extends AbstractBaseRedisDao<String, Object> implements IBaseDao {  
    /**
     * set value
     * 
     */
	public void setVal(String key,Object val){
		ValueOperations<String,Object> vop = redisTemplate.opsForValue();
		vop.set(key, val);
	}
	
    /**  
     * 
     * <br>------------------------------<br> 
     * @param keyId 
     * @return 
     */  
    public String getVal(final String key) {  
    	String result = redisTemplate.execute(new RedisCallback<String>() {  
            public String doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] serKey = serializer.serialize(key);  
                byte[] value = connection.get(serKey);  
                if (value == null) {  
                    return null;  
                }  
                String redisV = serializer.deserialize(value);  
                return redisV;  
            }  
        });  
        return result;  
    }  
    
    /**
     * keyVal
     * @return
     */
    public List<RedisKeyValue> getAllKv(final String keyHead){
    	List<RedisKeyValue> result = new ArrayList<RedisKeyValue>(); 
    	result = redisTemplate.execute(new RedisCallback<List<RedisKeyValue>>() {  
            public List<RedisKeyValue> doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
            	List<RedisKeyValue> result = new ArrayList<RedisKeyValue>();
            	RedisSerializer<String> serializer = getRedisSerializer();  
            	Set<byte[]> allKeys = connection.keys((keyHead+"*").getBytes());
            	Iterator<byte[]> it = allKeys.iterator();
            	while(it.hasNext()){
            		RedisKeyValue temp = new RedisKeyValue();
            		byte[] serKey = it.next();
            		byte[] serVal = connection.get(serKey);
            		String currentVal = new String();
            		//Key
            		String currentKey = serializer.deserialize(serKey);
            		temp.setKey(currentKey);
            		//Value
            		if(serVal == null){
            			currentVal = new String("");
            		}else{
            			currentVal = serializer.deserialize(serVal);
            		}
            		temp.setValue(currentVal);
            		//List add
            		result.add(temp);
            	}
                return result;  
            }  
        });  
        return result;  
    }
}  
