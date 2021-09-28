/*==================
   DBConn.java
================= */
//@ DB와 연결을 수행하기 위한 전용 객체
// ※ 싱글톤(singleton) 디자인 패턴을 이용한 Database 연결 객체 생성 전용 클래스
//    → DB 연결 과정이 가장 부하가 크기 때문에
//       한 번 연결된 객체를 계속 사용하는 것이 좋다.
//@ 이런유형 처리하는 로직이 있고, 이렇게 설계하는게 유지,보수,사용 등에 좋더라.. 공식처럼 누적시켜서
//@ 이렇게 설계하도록 합시다! → 디자인 패턴(ex> 수학의 이 문제는 이 공식에 넣고 사용하자.. 처럼 공식화)

//@ 이 DBConn은 외워질거고 외울거.. 이거 외워두면 나중이 편한

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnBackup
{
	// 변수 선언
	private static Connection dbConn;
	//@          커넥션 타입 / 변수이름 dbConn
	//-- 자동으로 null 초기화
	
	//@ 커넥션, statement 3개..... 4가지만 알면됨 
	
	// 메소드 정의 → 연결
	public static Connection getConnection()
	{ //@         커넥션을 반환
		
		// 한 번 연결된 객체를 계속 사용
		// 즉, 연결되지 않은 경우에만 연결을 시도하겠다는 의미
		// → 싱글톤(디자인 패턴)
		if(dbConn == null)
		//@ 연결 안된 상태
		{
			try
			{
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				//@                            ----------- ---- --
				//@                            오라클기본서버IP/ 포트넘버 / xid
				//-- localhost는 오라클 서버의 ip 주소를 기재하는 부분
				//	 1521 은 오라클 Port Number
				//@ 리스너 : 데려가는 사람....
				//@ 오라클 리스너 like 나이트의 삐끼.. 호객행위.. 얘기를 듣고 찾아가고 싶어하는 쪽(클럽, 테이블)에 데려다 줌
				//	 xe 는 오라클 SID(Express Edition 은 xe)
				
				String user = "scott";		//-- 오라클 사용자 계정 이름
				String pwd = "tiger";		//-- 오라클 사용자 계정 암호
					
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//@ 클래스에서.이런 이름 가진 드라이버 찾아라!
				//-- OracleDriver 클래스에 대한 객체 생성
				
				dbConn = DriverManager.getConnection(url, user, pwd);
				//@ 찾아서 연결 해달라.. 이 커넥션을 dbConn에 담겠다.
				//-- 오라클 서버 실제 연결
				//   갖고 있는 인자값(매개변수)은 오라클주소, 계정명, 패스워드
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
				//-- 오라클 서버 연결 실패 시 오류 메세지 출력 부분
			}
			
		}
		
		return dbConn;
		//@ dbConn을 반환함.
		//-- 구성된 연결 객체 반환
		}
	
	// getConnection() 메소드의 오버로딩 → 연결
	public static Connection getConnection(String url, String user, String pwd)
	//@ 메소드 오버로딩
	//@ 니가 메소드에 넘겨준 걸로 바꿔줄게...오버로딩
	//@ 위와 중간부분 빼고 동일(중간부분 삭제)
	{
		if(dbConn == null)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
			
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
		
		return dbConn;
	}
	
	// 메소드 정의 → 연결 종료
	public static void close()
	{
		// dbConn 변수(멤버 변수)는
		// Database 가 연결된 상태일 경우 Connection 을 갖는다.
		// 연결되지 않은 상태라면 nul 을 갖는다.
		if (dbConn != null)
		{
			try
			{
				// 연결 객체의 isClosed() 메소드를 통해 연결 상태 확인
				//-- 연결이 닫혀있는 경우 true 반환
				//   연결이 닫혀있지 않은 경우 false 반환
				if(!dbConn.isClosed())
					dbConn.close();
					//-- 연결 객체의 close() 메소드를 통해 연결 종료
			
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		dbConn = null;
		//-- 연결 객체 초기화
	
	}
}
