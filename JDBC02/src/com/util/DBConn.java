/*=================
    DBConn.java
===================*/

// 예외처리를 try catch가 아닌 throws로...
// DBConn 은 시간이 있을때마다 만들기
// 쪽지시험 있을거! DBConn 외워서 (throws로, try~catch 방식으로) A4용지에 쓰기

/*
-- 내가 작성한 코드
package com.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;


public class DBConn throws IOException
{
	private static Connection dbConn;
	
	public static Connection getConnection() throws IOException
	{
		if (dbConn == null)
		{
			String url = "jdbc:oracle:thin:@localhost:1421:xe";
			String user = "scott";
			String pwd = "tiger";
			
			Class.forName("oracle,jdbc.driver.OracleDriver");
			
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		return dbConn;
	}

	public static Connection getConnection(String url, String user, String pwd) throws IOException
	{
		if (dbConn == null)
		{
			Class.forName("oracle,jdbc.driver.OracleDriver");
			
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		return dbConn;
	}

	public static void close()
	{

		dbConn = null;
	}
}
*/

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	private static Connection dbConn;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if (dbConn == null)
		{
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String pwd = "tiger";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		
		return dbConn;
	}
	
	public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException
	{
		if (dbConn == null)
		{
			//@ cntl + d: 한 줄 지워짐
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		
		return dbConn;
	}
	
	public static void close() throws SQLException
	{
		if (dbConn != null) //@ 연결이 되어 있는 상황
		{
			if (!dbConn.isClosed())
				dbConn.close();
		}
		
		dbConn = null;	//@ 잘 빼먹음 주의!
	}
}











