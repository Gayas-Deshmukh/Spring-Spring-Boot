package com.spring.condition.annoation.oracleDb;

import java.util.Arrays;
import java.util.List;

import com.spring.condition.annoation.config.UserDao;

public class OracleDb implements UserDao
{
	public List<String> getUses()
	{
		return Arrays.asList("Oracle-1","Oracle-2","Oracle-3");
	}

}
