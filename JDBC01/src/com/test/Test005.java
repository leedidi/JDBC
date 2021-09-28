/*================================
   Test005.java 
   - 테이블 내의 데이터 읽어오기
================================*/

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class Test005
{
	public static void main(String[] args)
	{
		Connection conn = DBConn.getConnection();
		
		if(conn != null)
		{
			System.out.println("데이터베이스 연결 성공~!!!");
			//@ syso, 컨트롤+스페이스바+안에 "" 넣고 + 엔터 치기
			
			try
			{
				// 작업 객체 생성
				Statement stmt = conn.createStatement();
				
				// 쿼리문 준비
				//@ 작업객체에 담아 전송할 쿼리문이 필요...
				String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
				//String sql = "SELECT SID, NAME, TEL"
				//		+ " FROM TBL_MEMBER"
				//		+ " ORDER BY SID"; 
				//@ 이렇게도 가능한데 공백구성 잘 해야함! 공백 맨앞에 구성. 공백구성 파악 쉽기 위해~!!!
				//@ 맨 뒤에 넣으면 어떤 문장은 길고, 어떤 문장은 짧고... 파악 쉽지 않음
				
				//String sql = "SELECT SID, NAME, TEL" 
				//		+ "FROM TBL_MEMBER" 
				//		+ "ORDER BY SID";
				//String sql = "SELECT SID, NAME, TELFROM TBL_MEMBERORDER BY SID";
				//@ 공백 없는 경우 TEL, FROM 붙어버림... MEMBER, ORDERBY도 마찬가지
				
				// ※ 쿼리문 구성 간 공백 처리 check~!!!
				
				// ※ executeQuery() 메소드를 사용하면
				//    질의 결과를 ResultSet 객체로 가져올 수 있다.
				//    하지만, ResultSet 객체가 질의에 대한 결과물 모두를
				//    한꺼번에 갖고 있는 구조는 아니다.
				//    단지, 데이터베이스로부터 획득한 질의 결과물에 대한
				//    관리가 가능한 상태가 되는 것이다.
				//    이 때문에 데이터베이스와 연결을 끊게 되면
				//    ResultSet 객체는 더 이상 질의 결과를 관리할 수 없게 된다.
				
				// 쿼리문 실행
				ResultSet rs = stmt.executeQuery(sql);
				
				// DBConn.close();
				
				while (rs.next())
				{
					String sid = rs.getString("SID");
					String name = rs.getString("NAME");
					String tel = rs.getString("TEL");
					
					String str = String.format("%3s %8s %12s", sid, name, tel);
					
					System.out.println(str);
				}
				
				rs.close();
				stmt.close();
				
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}// end if
		
		DBConn.close();
		
		System.out.println("데이터베이스 연결 닫힘~!!!");
		System.out.println("프로그램 종료됨~!!!");
		
	}
	
}

// 실행 결과
/*
데이터베이스 연결 성공~!!!
1      이다영 010-4113-2353
2      채지윤 010-2222-2222
3      김소연 010-3333-3333
4      윤유동 010-4444-4444
5      손다정 010-5555-5555
데이터베이스 연결 닫힘~!!!
프로그램 종료됨~!!!
*/








