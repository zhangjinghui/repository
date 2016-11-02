package com.zjh.db;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCP {
	private static Properties pro=null;
	static{
		pro=new Properties();
		InputStream is=DBCP.class.getClassLoader().getResourceAsStream("dbcp.ini");
		try {
			pro.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		DataSource ds=null;
		Connection conn=null;
		try {
			ds=BasicDataSourceFactory.createDataSource(pro);
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
