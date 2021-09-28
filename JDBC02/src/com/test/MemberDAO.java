/*====================
    MemberDAO.java
=====================*/

/* @@@
있어야 하는 것?
DB 연결

인원 수 확인
데이터 입력
리스트 전체 조회
 */

// Database 에 Access 하는 기능
// → DBConn 활용

// 데이터를 입력하는 기능 → INSERT

// 인원 수 확인하는 기능
// 대상 테이블(TBL_MEMBER)의 레코드 카운팅 기능 → SELECT

// 전체 리스트 조회하는 기능
// 대상 테이블(TBL_MEMBER)의 데이터를 조회하는 기능 → SELECT

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 변수 선언 → DB 연결 객체
	//@ 세가지 방법... 1. 세터 만들기 2. 별도로 메소드 만들기 3. 생성자 만들기(?)(이방법으로 하단 함)
	//@ 오라클 쿼리는 DAO만 가지고 있을 거! 모두다 너혼자 감당해~!
	//@ 오라클과의 딜은 니가 전담해서 처리해! A라는 회사는 가연이가 감당해서 처리해!
	private Connection conn;
	
	// 생성자 정의
	public MemberDAO() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	// 메소드 정의 → 데이터를 입력하는 기능 → insert
	public int add(MemberDTO dto) throws SQLException
	{
		// 반환할 결과값을 담아낼 변수(적용된 행의 갯수)
		int result = 0;
		
		/*
		 * conn 객체 활용	Statement	executeUpdate()	 insert 쿼리 수행	int 적용된 행의 갯수 반환
		 */
		
		// 작업 객체 생성
	    Statement stmt = conn.createStatement();
	    
	    /*
	     ※ Statement 의 종류
	     	- Statement
	     	  하나의 쿼리를 사용하고 나면 더 이상 사용할 수 없다.
	     	//@ 감잡을때까지만 쓸거.. 나중엔 보안성 좋은 PreparedStatement만 사용할 것
	     	- PreparedStatement
	     	  하나의 PreparedStatement 로 쿼리를 여러 번 처리할 수 있다.
	     	- CallableStatement
	     	  //@ call 부르는 것, able 할수 있는 것... 추리 가능한 작업객체라고 직역할 수 있음.
	     	  //@ --> 오라클의 함수 등 호출할 때 사용!
	     	  데이터베이스 내의 스토어드 프로시저나 함수 등을 호출할 수 있다.
	     	  
	     ※ Statement 의 의미
	        자바에서 사용되는 3가지 종류의 작업 객체들은
	        데이터베이스로 쿼리를 담아서 보내는 그릇 정도로 생각하자.
	        즉, 작업 객체에 쿼리를 실어 데이터베이스로 보내버리면
	        그 내용이 데이터베이스에서 처리되는 것이다.
	        이 때, 한 번 사용하고 버리는 그릇은 Statement 이며,
	        재사용이 가능한 그릇은 PreparedStatement 이다.
	        
	     */
		
	    // 쿼리문 준비
	    String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
	    						+ " VALUES(MEMBERSEQ.NEXTVAL, '%s', '%s')"
	    		                , dto.getName(), dto.getTel());
	    //@ ctrl키 누른상태로 방향키 위아래 누르면 중심 잡힌 상태로 움직임!
	    
	    // 작업 객체를 활용하여 쿼리문 실행(전달) (@ 오라클에 전달해서 실행 자체는 오라클이 함!)
	    result = stmt.executeUpdate(sql);
	    
	    // 사용한 리소스 반납
	    stmt.close();
	    
		// 최종 결과값 반환
		return result;
		
	}// end add()
		
	
	// 메소드 정의 → 전체 인원 수 확인 기능
	public ResultSet count(MemberDTO dto) throws SQLException
	{
		// 반환할 결과값을 담아낼 변수
		//int result = 0;
		
		// 작업 객체 생성 
		Statement stmt = conn.createStatement();
		//@ 저장하면 색깔 생긴당....
		
		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
		
		// 작업 객체를 활용하여 쿼리문 실행(전달)
		ResultSet rs = stmt.executeQuery(sql);
		
		// 사용한 리소스 반납
		stmt.close();
		
		// 최종 결과값 반환
		return rs;
	}
	
	// 메소드 정의 → 전체 리스트 조회 기능
	public ResultSet lists()
	{
		//
		//
		//
		
	}
	
}













