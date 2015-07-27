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
	public static final String password = "840803xxxx";
	public static Connection conn = null;
	public static Statement sm = null;

	public  Connection getConn() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return conn;
	}

	public   void closed() {
		try {
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
