package com.myProject.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myProject.entity.RedisKeyValue;
import com.myProject.service.MysqlSyncService;
import com.myProject.service.RedisOperationService;

@Controller
public class RedisController {
	
	public static final Logger logger = Logger.getLogger(RedisController.class); 
	
	@Autowired
	private RedisOperationService redisOperationService;
	
	@Autowired
	private MysqlSyncService mysqlSyncService;

	@RequestMapping("/redisAllKv")
	public String redisAllKvController(Model model){
		List<RedisKeyValue> result = new ArrayList<RedisKeyValue>();
		result = redisOperationService.getAllRecordKv();
		model.addAttribute("rs",result);
		return "RedisAllKeyVal"; 
	}
	
	@RequestMapping("/syncToMySql")
	public String syncToMySql(Model model){
		mysqlSyncService.syncService();
		return "RedisAllKeyVal"; 
	}
}
