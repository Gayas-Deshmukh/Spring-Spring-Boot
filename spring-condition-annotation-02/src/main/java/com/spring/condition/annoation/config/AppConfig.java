package com.spring.condition.annoation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.spring.condition.annoation.mysqlDb.EnableMySqlDao;
import com.spring.condition.annoation.mysqlDb.MySqlDb;
import com.spring.condition.annoation.oracleDb.EnableOracleDb;
import com.spring.condition.annoation.oracleDb.OracleDb;

@Configuration
public class AppConfig 
{	
	@Autowired
	UserDao user;
	
	@Bean
	@Conditional(EnableMySqlDao.class)
	public UserDao getmySqlDb()
	{
		System.out.println("Created MySql");
		return new MySqlDb();
	}
	
	@Bean
	@Conditional(EnableOracleDb.class)
	public UserDao getOracleDb()
	{
		System.out.println("Created Oracle");
		return new OracleDb();
	}
	
}
