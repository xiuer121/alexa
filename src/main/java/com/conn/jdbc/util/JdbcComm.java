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
 
	public static void main(String[] args){
		int start = 0 ;
		int end = 0;
		for(int i=0;i<10;i++){ 
		
			if(i==0){
				start=0;
				end = i+1*100000;
			}else{
				end = i*100000;
			}
			MyThread myThread = new MyThread(start, end);
			myThread.start();
			start=i*10000;
			
		}
	}

	 

}
