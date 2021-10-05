/*=====================
    Test002.java
 - 쿼리문 전송 실습
=====================*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.util.DBConn;

public class Test002
{
	public static void main(String[] args)
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			Connection conn = DBConn.getConnection();
			
			do
			{
				System.out.print("번호 입력(-1 종료) : ");
				String sid = sc.next();
				//@ 1> 문자열로 받아옴
				
				if (sid.equals("-1"))
					break;
				System.out.print("이름 입력 : ");
				String name = sc.next();
				
				System.out.println("전화번호 입력 : ");
				String tel = sc.next();
				
				if (conn != null)
				{
					System.out.println("데이터베이스 연결 성공~!!!");
					
					try
					{
						String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
								+ " VALUES(?, ?, ?)";
						
						PreparedStatement pstmt = conn.prepareStatement(sql);
						
						// pstmt.setString(1, sid); //@ -> ? 자리에 따옴표 추가해서...ex> '1063' 의미! 
						//@ 이 자리에서 String인지.. int인지 등등... 처리. 잘 맞춰서 처리해야 함 주의!
						
						pstmt.setInt(1, Integer.parseInt(sid));
						//@ 2> 여기서 받아온 문자열, 숫자로 변환
						pstmt.setString(2, name);
						pstmt.setString(3, tel);
						
						int result = pstmt.executeUpdate();
						if (result > 0)
							System.out.println("회원 데이터 입력 완료~!!!");
						
						pstmt.close();
						
					} catch (Exception e)
					{
						System.out.println(e.toString());
					}
				}
				
			} while (true);
			
			DBConn.close();
			
			System.out.println();
			System.out.println("데이터베이스 연결 닫힘~!!!");
			System.out.println("프로그램 종료됨~!!!");	
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}

/*
번호 입력(-1 종료) : 10
이름 입력 : 손범석
전화번호 입력 : 
010-1010-1010
데이터베이스 연결 성공~!!!
회원 데이터 입력 완료~!!!
번호 입력(-1 종료) : 11
이름 입력 : 최수지
전화번호 입력 : 
010-1100-1100
데이터베이스 연결 성공~!!!
회원 데이터 입력 완료~!!!
번호 입력(-1 종료) : 12
이름 입력 : 채지윤
전화번호 입력 : 
010-1212-1212
데이터베이스 연결 성공~!!!
회원 데이터 입력 완료~!!!
번호 입력(-1 종료) : -1

데이터베이스 연결 닫힘~!!!
프로그램 종료됨~!!! 
*/

//10번 손범석으로 바꾸고.. 9번 추가!
// 9 최현정 010-9999-9999
// 9번을 어디서 입력했지....?








