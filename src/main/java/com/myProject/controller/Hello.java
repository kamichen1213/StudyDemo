package com.myProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myProject.dao.redis.IBaseDao;
  
@Controller  
public class Hello {  
	
	@Autowired
	private IBaseDao redisDao;
	
    public void setRedisDao(IBaseDao redisDao) {
		this.redisDao = redisDao;
	}

	@RequestMapping(value="/Hello")  
    public String HelloWorld(Model model){
		String value = redisDao.getVal("foo");
        model.addAttribute("message",value);  
        return "HelloWorld";  
    }  
    
    
}  