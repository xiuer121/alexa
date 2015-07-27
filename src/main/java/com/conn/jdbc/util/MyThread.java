package com.conn.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MyThread extends Thread {
	public static final String url = "jdbc:mysql://127.0.0.1/alexa";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123456";
	public static Connection conn = null;
	public static Statement sm = null;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Class.forName(name);
				conn = DriverManager.getConnection(url, user, password);// 获取连接
				sm = conn.createStatement();
				String sql =" select site_url from top_millions limit "+i+","+i*10000;
				
				i=i*10000;
			} catch (Exception e) {

				e.printStackTrace();
			}// 指定连接类型

		}
	}

}
