package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	private Connection conn;
	
	// 데이터 입력 기능
	// 전체 인원 수 확인 기능
	// 전체 리스트 조회 기능
	// 이걸 총점, 평균 계산해서 함께 번호로 오름차순 정렬시킴
 
	// 생성자 정의
	public ScoreDAO() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	// 메소드 정의 → 데이터를 입력하는 기능 → insert
	public int add(ScoreDTO dto) throws SQLException
	{
		// 반환할 결과값을 담아낼 변수(적용된 행의 갯수)
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)"
				+ " VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d)"
				, dto.getName(), dto.getKor(), dto.getEng(), dto.getMat());
		
		// 작업 객체를 활용하여 쿼리문 실행(전달)
		result = stmt.executeUpdate(sql);
		
		// 사용한 리소스 반납
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	}
	
	// 메소드 정의 → 전체 인원 수 확인 기능
	public int count() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비 → select
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		// 생성된 작업 객체를 활용하여 쿼리문 실행 → select → executeQuery() → ResultSet 변환 → 일반적 반복 처리
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 반복문(while) 구성 → 결과값 수신
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	}
	
	// 메소드 정의 → 전체 리스트 조회 기능
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		// 결과값으로 반환할 변수 선언 및 초기화
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT SID, NAME, KOR, ENG, MAT FROM TBL_SCORE ORDER BY SID";
		
		// 생성된 작업 객체를 활용하여 쿼리문 실행 → select → executeQuery() → ResultSet 반환
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 일반적 반복문 활용 → MemberDTO 인스턴스 생성 → 속성 구성 → ArrayList 에 적재
		while (rs.next())
		{
			//반복문 돌 때마다 ScoreDTO 인스턴스 생성
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID")); 
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			//dto.setSum(rs.getInt("(KOR+ENG+MAT)"));
 			//dto.setAvg(rs.getInt("TRUNC(((KOR+ENG+MAT)/3),1)"));
			
			result.add(dto);
			
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
		
	}// end lists()
	
	// 메소드 정의 → 데이터베이스 연결 종료 → DBConn 활용
	public void close() throws SQLException
	{
		DBConn.close();
	}

}
