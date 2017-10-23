package com.myProject.entity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.myProject.entity.TMyPvReport;

@Repository(value = "TMyPvReportMapper")  
public interface TMyPvReportMapper {  
		/**
		 * select from t_my_pv_report.
		 * @param sql
		 * @return
		 */
	    @Select(value = "${sql}")  
	    @Results(value = { 
	    		@Result(id = true, property = "id", column = "id"),  
	            @Result(property = "opKey", column = "op_key"),  
	            @Result(property = "opAction", column = "op_action"),  
	            @Result(property = "opType", column = "op_type"),  
	            @Result(property = "employeeId", column = "employee_id"),
	            @Result(property = "opDate", column = "op_date"),
	            @Result(property = "opCount", column = "op_count")})  
	    public List<TMyPvReport> getAllTMyPvReport(@Param(value = "sql") String sql);
	    
		/**
		 * select from t_my_pv_report with condition.
		 * @param sql
		 * @return
		 */
	    @Select(
	    		value =   "<script>"
	    				+ "select count(*) as n "
			    		+ "from t_pv_record pv "
			    		+ "where 1=1 "
			    		+ "<if test='#{opKey}!=null'>"
			    		+ "AND pv.op_key = #{opKey} "
			    		+ "</if>"
			    		+ "</script>"
	    		)
	    @ResultType(value = java.lang.Integer.class)
	    public Integer getCountByCondition(@Param(value = "opKey") String opKey);
		/**
		 * select from t_my_pv_report.
		 * @param sql
		 * @return
		 */
	    @Select(value = "${sql}")  
	    @Results(value = { 
	    		@Result(id = true, property = "id", column = "id"),  
	            @Result(property = "opKey", column = "op_key"),  
	            @Result(property = "opAction", column = "op_action"),  
	            @Result(property = "opType", column = "op_type"),  
	            @Result(property = "employeeId", column = "employee_id"),
	            @Result(property = "opDate", column = "op_date"),
	            @Result(property = "opCount", column = "op_count")})  
	    public List<TMyPvReport> operateReturnBeans(@Param(value = "sql") String sql);
	    /**
	     * insert one
	     * @param po
	     */
	    @Insert(value = "insert into t_pv_record"
	    		+ " (op_key,op_action,op_type,employee_id,op_date,op_count) "
	    		+ " values "
	    		+ " (#{reportPo.opKey},#{reportPo.opAction},#{reportPo.opType},#{reportPo.employeeId},#{reportPo.opDate},#{reportPo.opCount})")
	    public void insertObj(@Param(value = "reportPo") TMyPvReport po);
	    
	    /**
	     * update 
	     * @param po
	     */
	    @Update(value = 
	    		  "update t_pv_record set "
	    		+ " op_action = #{po.opAction},op_type = #{po.opType},employee_id = #{po.employeeId},op_date = #{po.opDate},op_count = #{po.opCount} "
	    		+ " where "
	    		+ " op_key = #{po.opKey} "
	    	   )
	    public void updateObj(@Param(value = "po") TMyPvReport po);
	}  
