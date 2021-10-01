/*==========================================
    MemberDAO.java
    - 데이터베이스 액션 처리 전용 클래스
========================================= */

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 속성 구성
	private Connection conn;
	
	// 데이터베이스 연결
	public Connection connection()
	{
		conn = DBConn.getConnection();
		return conn;
	}
	
	// 데이터베이스 연결 종료
	public void close()
	{
		DBConn.close();
	}
	
	// 직원 데이터 입력
	public int add(MemberDTO dto) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		//@ 하나씩 꺼내며 처리하기! 한꺼번에 하면 빼먹거나, 헷깔릴 수 있으므로...
		//@ dto부터 쓰고 위에 문자열 처리하기!
		//@ dto.~~ 위에 마우스 올리면 어떤거 반환하는지 보임!
		String sql = String.format("INSERT INTO TBL_EMP"
				+ "(EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID"
				+ ", TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
				+ " VALUES(EMPSEQ.NEXTVAL, '%s', '%s', TO_DATE('%s','YYYY-MM-DD')"
				+ ", (SELECT CITY_ID FROM TBL_CITY WHERE CITY_NAME = '%s')"
				+ ", '%s'"
				+ ", (SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s')"
				+ ", (SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s')"
				+ ", %d, %d)" 
				, dto.getEmpName(), dto.getSsn(), dto.getIbsaDate()
				, dto.getCityName(), dto.getTel(), dto.getBuseoName(), dto.getJikwiName()
				, dto.getBasicPay(), dto.getSudang());
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
		
	}// end add()

	// 전체 직원 수 조회
	public int memberCount() throws SQLException
	{
		// 반환할 결과 변수 선언 및 초기화
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		
		// 쿼리문 실행 → select → executeQuery() → ResultSet 반환
		ResultSet rs = stmt.executeQuery(sql);
		
		// Resultset 처리 → 반복문 구성(단일문일 경우 조건문도 가능) → 결과값 수신
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	
	}//end memberCount()

	// 검색 결과 직원 수 조회
	// EMP_ID=1001		    → Key : EMP_ID		/ value : 1001
	// EMP_NAME='송해덕'	→ Key : EMP_NAME	/ value : '송해덕'
	// BUSEO_NAME='개발부'	→ Key : BUSEO_NAME	/ value : '개발부'
	// JIKWI_NAME='대리'	→ Key : JIKWI_NAME	/ value : '대리'
	public int membercount(String key, String value) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = "";
		
		if (key.equals("EMP_ID"))
			sql = String.format("SELECT COUNT(*) AS COUNT FROM EMPVIEW WHERE %s=%s", key, value); // 오라클에서 숫자타입처리
		else 
			sql = String.format("SELECT COUNT(*) AS COUNT FROM EMPVIEW WHERE %s='%s'", key, value); // 오라클에서 문자타입처리
			//@ 오라클의 자동형변환 처리 믿지 말기!
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		rs.close();
		stmt.close();
		
		//String sql = String.format
		//		("SELECT COUNT(*) AS COUNT FROM EMPVIEW WHERE EMP_ID='%s'", key, value);
		//@ 지금같은 경우엔 하나로도 가능!
		
		return result;
	}//end memberCount()
	//@ ↑ memberCount() 메소드, 다한거 같긴 한뎅... 확인 해보기..?
	
	
	//@ 위는 부서 - 그중에서 개발부 찾아야 했음. 그래서 두개가 필요 했음1
	//@ 아래에는 부서, 딱 하나만 필요함! 
	//@ 만약, 사번 오름차순/내림차순... 이었으면 두개가 편할 수도. 하지만 지금은 다른 옵션 없음.
	
	//@ 한 명의 하나의 컬럼 -> int, String.. 데이터타입
	//@ 한 명의 여러 컬럼들  -> memberDTO
	//@ 여러명(들) -> ArrayList<memberDTO> 반드시 arraylist일 필욘 없음!
	
	//@ 김밥.... 1001 김 올리고 홍길동 햄 올리고 951022,,, 단무지 올리고
	//@ 한줄다 쌈! 그 다음 김밥줄,,,다음 줄,,,
	//@ 각 재료 넣는 통이 있음!
	
	// 직원 데이터 전체 조회 (사번/이름/부서/직위/급여내림차순)
	public ArrayList<MemberDTO> lists(String key) throws SQLException
	{	
		//@ 최종반환: ArrayList, ArrayList의 각 방을 구성 : MemberDTO
		//@ ArrayList 반환위해서는 MemberDTO가 채워져 있어야 함 
		// 반환할 결과값 선언 및 초기화
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN"
				+ ", TO_CHAR(IBSADATE,'YYYY-MM-DD') AS IBSADATE"
				+ ", CITY_NAME, TEL, BUSEO_NAME, JIKWI_NAME"
				+ ", BASICPAY, SUDANG, PAY"
				+ " FROM EMPVIEW"
				+ " ORDER BY %s", key);
		// 
		
		// 쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 반복문 구성
		while (rs.next())
		{
			// 1개의 MemberDTO 생성 → 비어있는 상태
			MemberDTO dto = new MemberDTO();
			
			// 생성된 MemberDTO 에 값 채워넣기 → 값이 채워진 MemberDTO
			
			// ArrayList 에 요소로 추가
			//@ 이 과정 계속 반복~!!! resultset에 있는다음값이 없을 때까지!
			//@ like 김밥재료 다 떨어졌는데요! 할 때까지..
			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityName(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoName(rs.getString("BUSEO_NAME"));
			dto.setJikwiName(rs.getString("JIKWI_NAME"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setBasicPay(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));
			//@ 뒤에 채워넣기 완료!

			// ArrayList 에 요소로 추가
			result.add(dto);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
				
		// 최종 결과값 반환
		return result;
	}// end lists()
	
	// 직원 데이터 검색 (사번/이름/부서/직위)
	public ArrayList<MemberDTO> searchLists(String key, String value) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		
		String sql = "";
		if (key.equals("EMP_ID"))
		{
			sql = String.format("SELECT EMP_ID, EMP_NAME, SSN"
					+ ", TO_CHAR(IBSADATE,'YYYY-MM-DD') AS IBSADATE"
					+ ", CITY_NAME, TEL, BUSEO_NAME, JIKWI_NAME"
					+ ", BASICPAY, SUDANG, PAY"
					+ " FROM EMPVIEW"
					+ " WHERE %s='%s'", key, value);
		}
		else
		{
			sql = String.format("SELECT EMP_ID, EMP_NAME, SSN"
					+ ", TO_CHAR(IBSADATE,'YYYY-MM-DD') AS IBSADATE"
					+ ", CITY_NAME, TEL, BUSEO_NAME, JIKWI_NAME"
					+ ", BASICPAY, SUDANG, PAY"
					+ " FROM EMPVIEW"
					+ " WHERE %s=%s", key, value);
		}
		//@ 한글파일 텍스트파일 부분으로도 대체 사용 가능!
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityName(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoName(rs.getString("BUSEO_NAME"));
			dto.setJikwiName(rs.getString("JIKWI_NAME"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setBasicPay(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));
			
			result.add(dto);
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}// end searchLists()
	
	// 지역 리스트 조회
	public ArrayList<String> searchCity() throws SQLException
	{
		ArrayList<String> result = new ArrayList<String>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT CITY_NAME FROM TBL_CITY";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
			result.add(rs.getString("CITY_NAME"));
		rs.close();
		stmt.close();
		
		return result;
	}// end searchCIty()
	
	// 부서 리스트 조회
	public ArrayList<String> searchBuseo() throws SQLException 
	{
		ArrayList<String> result = new ArrayList<String>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT BUSEO_NAME FROM TBL_BUSEO";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
			result.add(rs.getString("BUSEO_NAME"));
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 직위 리스트 조회
	public ArrayList<String> searchJikwi() throws SQLException
	{
		ArrayList<String> result = new ArrayList<String>();
		
		Statement stmt = conn.createStatement();
		String sql = "SELECT JIKWI_NAME FROM TBL_JIKWI";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
			result.add(rs.getString("JIKWI_NAME"));
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 직위에 따른 최소 기본급 검색
	public int searchBasicPay(String jikwi) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT MIN_BASICPAY FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'", jikwi);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
			result = rs.getInt("MIN_BASICPAY");
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 직원 수정
	public int modify(MemberDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("UPDATE TBL_EMP"
				+ " SET EMP_NAME='%s', SSN='%s', IBSADATE=TO_DATE('%s', 'YYYY-MM-DD')"
				+ ", CITY_ID=(SELECT CITY_ID FROM TBL_CITY WHERE CITY_NAME='%s')"
				+ ", TEL='%s'"
				+ ", BUSEO_ID=(SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME='%s')"
				+ ", JIKWI_ID=(SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME='%s')"
				+ ", BASICPAY=%d, SUDANG=%d"
				+ " WHERE EMP_ID=%d" 
				, dto.getEmpName(), dto.getSsn(), dto.getIbsaDate()
				, dto.getCityName(), dto.getTel(), dto.getBuseoName(), dto.getJikwiName()
				, dto.getBasicPay(), dto.getSudang()
				, dto.getEmpId());
		
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
		
	}
	
	// 직원 삭제
	public int remove(int empId) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID=%d", empId);
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
		
	}
	
	
	
	
}


























