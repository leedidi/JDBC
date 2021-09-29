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
import java.util.ArrayList;

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
		
	
	// 내가 작성한 코드
	
	/*
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
	public ResultSet lists(MemberDTO dto) throws SQLException
	{
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
		
		// 작업 객체를 활용하여 쿼리문 실행(전달)
		ResultSet rs = stmt.executeQuery(sql);
		
		// 사용한 리소스 반납
	    stmt.close();
	    
	    // 최종 결과값 반환
	    return rs;
	}
	
}
*/
	// 함께 작성한 코드
	//메소드 정의 → 전체 인원 수 확인 기능
	public int count() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비 → select
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
		
		// 생성된 작업 객체를 활용하여 쿼리문 실행 → select → executeQuery() → ResultSet 반환 → 일반적 반복 처리
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 반복문 구성 → 결과값 수신
		//@ 아 ResultSet 썼네? 그럼 while 반복문... 해도 큰 상관 없음
		//@ ResultSet 관리하는동안 커넥션은 연결되어야(살아있어야) 한다.
		while (rs.next())					// if (rs.next())
		{
			result = rs.getInt("COUNT");	// rs.getInt(1);	// ※ 컬럼 인덱스는 1 부터.. (@ 0부터 아님!)
			//@ COUNT 컬럼의 값 정수로 얻어내겠다.
		}
		
		// 리소스 반납
		//@ 보통 먼저 얻어쓴 걸 나중에 반납함.
		rs.close();
		stmt.close();

		// 최종 결과값 반환
		return result;
		
	}// end count()
	
	/*
	// 내가 작성한 코드 2
	//메소드 정의 → 전체 리스트 조회 기능
	public String lists() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		String result = " ";
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비 → select
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
		
		// 생성된 작업 객체를 활용하여 쿼리문 실행 → select → excuteQuery() → ResultSet 반환 → 일반적 반복 처리
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 반복문 구성 → 결과값 수신
		while (rs.next())
		{
			String sid = rs.getString("SID");
			String name = rs.getString("NAME");
			String tel = rs.getString("TEL");
			
			String str = String.format("%3s %5s %7s", sid, name, tel);
			System.out.println(str);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
		
	}
	*/
	
	//메소드 정의 → 전체 리스트 조회 기능
	//@ 작업을 부탁받음! 거래처와의 협업 전담....
	//@ 혼자 가져다주고 끝나는게 아니라 나 전해줬어! 하고 유동이한테 전달해줘야함
	//@ 직접 출력하는게 아니라, list()에게 부탁하니까 그값을 반환자료 형태로 전달해줘야함
	//@ 그래서 void 아님!
	//@ NAME 컬럼 가져다 줘! → String    / 여러개 NAME 컬럼: String들, String[], ArrayList<String>
	//@ 전체 컬럼 가져다 줘! → MemberDTO / 여러개 컬럼 : MemberDTO들, ArrayList<MemberDTO> 
	//@                                                                - MemberDTO를 반환할수있는 자료구조 반환
	public ArrayList<MemberDTO> lists() throws SQLException
	{
		//@ 틀 잘 잡기!
		// 결과값으로 반환할 변수 선언 및 초기화
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
		
		// 생성된 작업 객체를 활용하여 쿼리문 실행 → select → executeQuery() → ResultSet 반환
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 일반적 반복문 활용 → MemberDTO 인스턴스 생성 → 속성 구성 → ArrayList 에 적재
		//@ 아 ResultSet 썼네? 그럼 while 반복문 쓰자! 해도 상관없을 만큼 같이 다님
		//@ 값 출력이 아니라 반환할 것.... MemberDTO들...
		//@ while 한번 반환할때마다 하나의 MemberDTO 가 만들어 질 것...
		//@ 가장 먼저 MemberDTO 대한 인스턴스 생성 필요
		while (rs.next())
		{
			//@ 반복문 돌 때마다 MemberDTO 인스턴스 생성
			//@ like 사과장수.... 그래서 MemberDTO들로 합쳐짐(첫번째, 두번째, 세번째 사과장수...)
			MemberDTO dto = new MemberDTO();
			
			//@ 받아온 1번 dto에 넣어줌
			//@ 받아온 홍길동 dto에 넣어줌
			//@ 받아온 010-1111-1111 dto에 넣어줌
			//@ -->> 빈 껍데기였던 dto에 내용 채워짐
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setTel(rs.getString("TEL"));
			
			result.add(dto);

		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
		
	}//end lists()
	

	// 메소드 정의 → 데이터베이스 연결 종료 → DBConn 활용
	public void close() throws SQLException
	{
		DBConn.close();
	}
	
}










	
	
	
	
	
