/*===================
    ScoreDAO.java
====================*/
/*
 abcdk; abcdk; abcdk; abcdk; abcdk; abcdk; abcdk;
*/
//@ 블럭 잡고 cntl + shift + 슬러시 : 주석 한번에 생김
//@ 블럭 잡고 cntl + shift + 역슬러시 : 주석 사라짐
//@ 한줄 다 제거 : cntl + d


// @ cntl + shift + f 누르면 이클립스가 들여쓰기 정리해줌!
// @ (블럭잡고 컨트롤 i 해도 가능!)
// @ 당분간 꼭 기억~~~!!!
// @ 누구에게 코드 보여주기 전의 매너임!
// @ sts 가장 많이 씀..이거도 이클립스로 만든 것!


package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	// 주요 속성 구성
	private Connection conn;

	// 데이터베이스 연결 담당 메소드
	public Connection connection() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
		return conn;
	}

	// 데이터 입력 담당 메소드
	public int add(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d)"
				, dto.getName(), dto.getKor(), dto.getEng(), dto.getMat());
		result = stmt.executeUpdate(sql);
		stmt.close();
		return result;
	}

	// 전체 리스트 출력 담당 메소드
	public ArrayList<ScoreDTO> lists() throws SQLException // @ 모든애들 출력이니까.. 넘겨받을 매개변수 X
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		Statement stmt = conn.createStatement();
		String sql = "SELECT SID, NAME, KOR, ENG, MAT" + ", (KOR + ENG + MAT) AS TOT" + ", (KOR + ENG + MAT)/3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK" + " FROM TBL_SCORE" + " ORDER BY SID ASC";
		// @ 어디서 개행하는지 위치도 보기! 이거대로 개행하는 습관만 들여도 편해질 거임
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();

			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));

			result.add(dto);
		}
		rs.close();
		stmt.close();

		return result;

	}

	// 이름 검색 담당 메소드 //@ 메소드 오버로딩
	public ArrayList<ScoreDTO> lists(String name) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT *"
				+ " FROM"
				+ " (SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT" 
				+ ", (KOR+ENG+MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE)"
				+ " WHERE NAME = '%s'", name);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();

			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;

	}// end lists(String name)
	
	
	// 번호 검색 담당 메소드 //@ 메소드 오버로딩
	public ArrayList<ScoreDTO> lists(int sid) throws SQLException //@ String name 넘겨받으면 위와 겹쳐버려서 생성불가!
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT *"
				+ " FROM"
				+ " (SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT"
				+ ", (KOR+ENG+MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE) WHERE SID=%d", sid);
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			//@ 숫자로 1,2,3,4,.. 도 가능("SID" .. 자리에!)
			
			result.add(dto);
			//@ 지금은 복붙 1도 도움안됨! 코드 한번이라도 기회있을때마다 작성하는게 도움됨..좋음..
		}
		rs.close();
		stmt.close();
		
		return result;
	
	}// end lists(int sid)
	
	
	// 인원 수 확인 담당 메소드
	//@ 코드 한번이라도 더 작성, 익숙해지기!
	public int count() throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())					// if (rs.next()) 가능! 한번이니까..
			result = rs.getInt("COUNT");	// rs.getInt(1);
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 데이터 수정 담당 메소드
	
	//@ sid만 받는 int가 아님... 수정경우에는 필요 데이터들 다 받아야 함. 
	//@ 번호는 위의 번호검색에서 받아와서 수정데이터들 입력받음..
	//@ 수정부분일때 이 부분 잘 헷깔리니까 잘 기억해 두기!
	//@ DTO에서는 sid 부분을 왜 int가 아닌 String으로 구성할까...? 	
	/*
	public int modify(ScoreDTO dto) throws SQLException 	//@ check~!!! 
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("UPDATE TBL_SCORE SET NAME='%s', KOR=%d, ENG=%d, MAT=%d WHERE SID=%s;"
				, dto.getName(), dto.getKor(), dto.getEng(), dto.getMat(), dto.getSid());
		//@ WHERE SID=%s -> dto에서 얻어온 sid가 String 일지언정 오라클이 인식하기엔 숫자 형식으로 ㅣ인식! '' 안 들어가니까.
		
		result = stmt.executeUpdate(sql);
		stmt.close();
		return result;
	// @ 뭔가 오타 있었던듯...나중에 확인 하기
	}
	*/
	
	public int modify(ScoreDTO dto) throws SQLException
	   {
	      int result = 0;
	      Statement stmt = conn.createStatement();
	   
	      String sql = String.format("UPDATE TBL_SCORE"
	               + " SET NAME='%s', KOR=%d, ENG=%d, MAT=%d"
	               + " WHERE SID = %s", dto.getName(), dto.getKor(), dto.getEng(), dto.getMat(), dto.getSid());
	      
	      result = stmt.executeUpdate(sql);
	      
	      return result;
	   }
	
	// 데이터 삭제 담당 메소드
	public int remove(int sid) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_SCORE WHERE SID=%d", sid);
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
	}
	
	// 데이터베이스 연결 종료 담당 메소드
	public void close() throws SQLException
	{
		DBConn.close();
	}
	
	
	
	
	
	
	
	
	

}

	

























