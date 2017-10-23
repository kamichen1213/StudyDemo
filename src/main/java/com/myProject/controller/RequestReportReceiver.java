package com.myProject.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myProject.entity.DolOperationEntity;
import com.myProject.entity.RecordPO;
import com.myProject.service.ReqOptionRecordService;

@Controller
public class RequestReportReceiver {
	
	@Autowired
	private ReqOptionRecordService recordService;
	
	public static Logger logger = Logger.getLogger(RequestReportReceiver.class);
	
	@RequestMapping(value = "/reqReciver")
	@ResponseBody
	public RecordPO requuestReportReceive(String actionId,String employeeId,String operateType,@RequestParam(value="callback",required=false) String callback){

		RecordPO recordPo = new RecordPO();
		DolOperationEntity entity = new DolOperationEntity();
		if(actionId == null){
			logger.warn("ActionId is null!");
			return null;
		}
		entity.setActionId(actionId);
		entity.setEmployeeId(employeeId);
		entity.setOpType(operateType);
		recordPo.setEntity(entity);
		recordService.record(recordPo);
		recordPo.setErrMsg("process success!");
		logger.info("Over: process success!");
		return recordPo;
	}
	
}
