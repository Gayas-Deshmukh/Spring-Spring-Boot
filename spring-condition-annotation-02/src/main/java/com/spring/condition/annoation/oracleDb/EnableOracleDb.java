package com.spring.condition.annoation.oracleDb;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnableOracleDb implements Condition 
{

	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
	{
		// Without adding database dependencies
		
		  String dbType = System.getProperty("dbType"); //we will pass : -DdbType=Oracle
		  
		  System.out.println("db type is:\t"+ dbType + " : from EnableOracleDb");
		  
		  return dbType != null && dbType.contentEquals("Oracle");
		
		
		
		// Check this when we add database dependencies
		/*
		boolean enableH2 = false;
		
		try 
		{
			Class<?> h2Driver = Class.forName("org.h2.Driver");
			
			if(h2Driver != null) 
			{
				System.out.println("Driver evalutaing by the Conditional is:\t"+h2Driver.getName());
				enableH2 = true;
			}
		} 
		catch (ClassNotFoundException e)
		{
			enableH2=false;
		}
		
		return enableH2;
		*/
	}

}
