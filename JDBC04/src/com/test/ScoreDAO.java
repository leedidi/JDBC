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
		String sql = String.format(
				"INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d)",
				dto.getName(), dto.getKor(), dto.getMat(), dto.getMat());
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
		String sql = String.format(
				"SELECT * FROM (SELECT SID, NAME, KOR, ENG, MAT" + ", (KOR+ENG+MAT) AS TOT" + ", (KOR+ENG+MAT)/3 AS AVG"
						+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE)" + " WHERE NAME = '%s'",
				dto.getName());

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();

			dto.setSid(rs.getString("SID"));

		}
		return result;

	}

}
