package com.spring.condition.annoation.mysqlDb;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnableMySqlDao implements Condition {

	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
	{
		// Without adding database dependencies
		
		String dbType = System.getProperty("dbType"); // //we will pass : -DdbType=MySql
	  
		System.out.println("db type is:\t"+ dbType + " : from EnableMySqlDao");
	  
		return dbType != null && dbType.contentEquals("MySql");	
	  	
		
		// Check this when we add database dependencies
		/*
		boolean enableMysql = false;
		
		try 
		{
			Class<?> mysqlDriver = Class.forName("com.mysql.cj.jdbc.Driver");
			if(mysqlDriver != null) {
				System.out.println("Driver evalutaing by the Conditional is:\t"+mysqlDriver.getName());
				enableMysql = true;
			}
		} 
		catch (ClassNotFoundException e) 
		{
			enableMysql=false;
		}
		
		return enableMysql;
		*/
	}   

}
