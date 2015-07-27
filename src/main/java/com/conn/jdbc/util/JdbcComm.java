package com.conn.jdbc.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.po.HttpRequester;
import com.po.HttpRespons;



public class JdbcComm {

	public static final String url = "jdbc:mysql://127.0.0.1/alexa";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123456";
	public static Connection conn = null; 
	public static Statement sm =null;
	public static void main(String[] args){
		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			sm= conn.createStatement();
			
			for (int i = 0; i < 10; i++) {
				new Thread(new Runnable() {
					public void run() { 
						String sql =" select site_url from top_millions limit 0,10000";
						try {
							
						   ResultSet rs = sm.executeQuery(sql);
						
						  while(rs.next()){ 
								HttpRequester request = new HttpRequester();
								HttpRespons hr;
								try {
									System.out.println(rs.getString("site_url"));
									hr = request.sendGet("http://"+rs.getString("site_url"));
									 System.out.println(hr.getUrlString());     
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}   
							   
						  }
						} catch (SQLException e) {
							 
							e.printStackTrace();
						}
					}
					}).start();
			}
		
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		try {
//			conn.close();
//			sm.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}

	 

}
