/*=====================================================
   Test001.java
   - CallableStatement 를 활용한 SQL 구문 전송 실습
=====================================================*/

package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			Connection conn = DBConn.getConnection();
			
			do
			{
				System.out.print("이름 입력(-1종료) : ");
				String name = sc.next();
				
				if (name.equals("-1"))
					break;
				
				System.out.print("전화번호 입력 : ");
				String tel = sc.next();
				
				if (conn != null)
				{
					System.out.println("데이터베이스 연결 성공~!!!");
					
					try
					{
						// 쿼리문 준비
						String sql = "{call PRC_MEMBERINSERT(?, ?)}";
						
						// CallableStatement 작업 객체 생성
						CallableStatement cstmt = conn.prepareCall(sql);
						
						// 매개변수 전달
						cstmt.setString(1, name);
						cstmt.setString(2, tel);
						//@ 프로시저 가동시(작동시) insert,update,delete.. 오라클에 변동사항 생기면 executeUpdate
						//@ 프로시저 가동시(작동시)                        오라클에 변동사항 없으면 executeQuery
						//@ 얻어만 쓰면 executeQuery......!!!
						
						int result = cstmt.executeUpdate();
						if (result > 0)
						{
							System.out.println("프로시저 호출 및 데이터 입력 완료~!!!");
						}
						
					} catch (Exception e)
					{
						System.out.println(e.toString());
					}
				}
					
				
			} while (true);
			DBConn.close();
			
			System.out.println("\n데이터베이스 연결 종료~!!!");
			System.out.println("프로그램 종료됨~!!!");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

}

/*
이름 입력(-1종료) : 
장민지
전화번호 입력 : 010-6857-1996
데이터베이스 연결 성공~!!!
프로시저 호출 및 데이터 입력 완료~!!!
이름 입력(-1종료) : 
장진하
전화번호 입력 : 010-4434-2587
데이터베이스 연결 성공~!!!
프로시저 호출 및 데이터 입력 완료~!!!
이름 입력(-1종료) : 
정가연
전화번호 입력 : 010-4780-9592
데이터베이스 연결 성공~!!!
프로시저 호출 및 데이터 입력 완료~!!!
이름 입력(-1종료) : 
-1

데이터베이스 연결 종료~!!!
프로그램 종료됨~!!! 
*/











