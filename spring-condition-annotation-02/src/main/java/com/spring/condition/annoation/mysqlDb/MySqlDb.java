package com.spring.condition.annoation.mysqlDb;

import java.util.Arrays;
import java.util.List;

import com.spring.condition.annoation.config.UserDao;

public class MySqlDb implements UserDao {

	public List<String> getUses() 
	{
		return Arrays.asList("My-Sql -1","My-Sql-2","My-Sql-3");
	}

}
