package com.conn.jdbc.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.po.HttpRequest;
import com.po.HttpRequester;
import com.po.HttpRespons;

public class MyThread extends Thread {
//	public static final String url = "jdbc:mysql://127.0.0.1/alexa";
//	public static final String name = "com.mysql.jdbc.Driver";
//	public static final String user = "root";
//	public static final String password = "840803xxxx";
	public static Connection conn = null;
	public static Statement sm = null;

	// 定义读取的起始点
	private long start;
	// 定义读取的结束点
	private long end;

	public MyThread(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {

		try {
//			Class.forName(name);
//			conn = DriverManager.getConnection(url, user, password);// 获取连接
			JdbcComm jdbc = new JdbcComm();
			conn  = jdbc.getConn();
			sm = conn.createStatement();
			String sql = " select site_url from top_millions limit " + start
					+ "," + end;

			ResultSet rs = sm.executeQuery(sql);
			while (rs.next()) {
				HttpRequester request = new HttpRequester();
//				HttpRespons hr;
				try {
//					 request.sendGet("http://" + rs.getString("site_url")+"/do/reg.php");
				HttpRequest.sendGet("http://" + rs.getString("site_url"),null);
			
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}// 指定连接类型
		finally {

			try {
				conn.close();
				sm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
