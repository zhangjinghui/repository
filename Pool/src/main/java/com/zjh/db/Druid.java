package com.zjh.db;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class Druid {
	private static Properties pro=null;
	static{
		pro=new Properties();
		InputStream is=Druid.class.getClassLoader().getResourceAsStream("druid.ini");
		try {
			pro.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		DataSource ds=null;
		Connection conn=null;
		try {
			ds=DruidDataSourceFactory.createDataSource(pro);
			conn=ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
