package com.zjh.db;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {
	private static ComboPooledDataSource ds=null;
	private static Connection conn=null;
	static{
		ds=new ComboPooledDataSource("mysql");
	}
	public static Connection getConnection(){
		try{
			conn=ds.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
