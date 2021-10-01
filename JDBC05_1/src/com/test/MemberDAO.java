package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.util.DBConn;

public class MemberDAO
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
	public int add(MemberDTO dto) throws SQLException
	{
		// private String empName, ssn, ibsaDate, tel;
		// private int empId, cityId, buseoId, jikwiId, basicPay, sudang, //totalPay; 
		// 나중에 합쳐서 출력때보여줌

		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format(
				"INSERT INTO TBL_EMP (EMP_ID, EMP_NAME, SSN, IBSADATE"
						+ ", CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
						+ " VALUES (EMPSEQ.NEXTVAL, '%s', '%s', '%s'" + ", %d, '%s', %d, %d, %d, %d)",
				dto.getEmpName(), dto.getSsn(), dto.getIbsaDate(), dto.getCityId(), dto.getTel(), dto.getBuseoId(),
				dto.getBuseoId(), dto.getBasicPay(), dto.getSudang());
		result = stmt.executeUpdate(sql);
		stmt.close();
		return result;
	}

	//============= 전체 리스트 출력 담당 메소드 (사번/이름/부서이름/직위이름/(급여내림차순) 정렬)
	// 일단은 .... 사번 순 정렬. (주석처리)
	// 저거 선택은 CASE 문으로 해야하나..? 따로만들어야하나,,,,
	// 1. 사번순 출력
	public ArrayList<MemberDTO> listsOutputEmpId() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID"
				+ ", BASICPAY, SUDANG, (BASICPAY+SUDANG) AS TOTALPAY" + " FROM TBL_EMP" + " ORDER BY EMP_ID";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityId(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoId(rs.getInt("BUSEO_ID"));
			dto.setJikwiId(rs.getInt("JIKWI_ID"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setTotalPay(rs.getInt("TOTALPAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}

	// 2. 이름순 출력
	public ArrayList<MemberDTO> listsOutputEmpName() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID"
				+ ", BASICPAY, SUDANG, (BASICPAY+SUDANG) AS TOTALPAY" + " FROM TBL_EMP" + " ORDER BY EMP_NAME";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityId(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoId(rs.getInt("BUSEO_ID"));
			dto.setJikwiId(rs.getInt("JIKWI_ID"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setTotalPay(rs.getInt("TOTALPAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}

	// 3. 부서이름순 출력
	public ArrayList<MemberDTO> listsOutputBuseoID() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID"
				+ ", BASICPAY, SUDANG, (BASICPAY+SUDANG) AS TOTALPAY" + " FROM TBL_EMP" + " ORDER BY BUSEOID";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityId(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoId(rs.getInt("BUSEO_ID"));
			dto.setJikwiId(rs.getInt("JIKWI_ID"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setTotalPay(rs.getInt("TOTALPAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;
	}

	// 4. 직위이름순
	public ArrayList<MemberDTO> listsOutputJikwiID() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID"
				+ ", BASICPAY, SUDANG, (BASICPAY+SUDANG) AS TOTALPAY" + " FROM TBL_EMP" + " ORDER BY JIKWIID";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityId(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoId(rs.getInt("BUSEO_ID"));
			dto.setJikwiId(rs.getInt("JIKWI_ID"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setTotalPay(rs.getInt("TOTALPAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}
	
	// 5. (급여내림차순) 
	public ArrayList<MemberDTO> listsOutputTotalPay() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID"
				+ ", BASICPAY, SUDANG, (BASICPAY+SUDANG) AS TOTALPAY" + " FROM TBL_EMP" + " ORDER BY TOTALPAY DESC";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityId(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoId(rs.getInt("BUSEO_ID"));
			dto.setJikwiId(rs.getInt("JIKWI_ID"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setTotalPay(rs.getInt("TOTALPAY"));

			result.add(dto);

		}
		rs.close();
		stmt.close();

		return result;

	}
	

	// 전체 리스트 검색 담당 메소드 (사번/이름/부서/직위)
	// 1. 사번 검색
	public ArrayList<MemberDTO> listsSearch_EmpId(int empId) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		String sql = String.format(
				"SELECT *" + " FROM" + " (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID"
						+ ", BASICPAY, SUDANG, (BASICPAY+SUDANG) AS TOTALPAY" + " FROM TBL_EMP)" + " WHERE EMP_ID=%d",
				empId);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityId(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoId(rs.getInt("BUSEO_ID"));
			dto.setJikwiId(rs.getInt("JIKWI_ID"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setTotalPay(rs.getInt("TOTALPAY"));

			result.add(dto);
		}
		rs.close();
		stmt.close();

		return result;
	}// end lists(int empID);
	
	//2. 이름 검색
	public ArrayList<MemberDTO> listsSearch_EmpName(String empName) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		String sql = String.format(
				"SELECT *" + " FROM" + " (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID"
						+ ", BASICPAY, SUDANG, (BASICPAY+SUDANG) AS TOTALPAY" + " FROM TBL_EMP)" + " WHERE EMP_NAME='%s'",
				empName);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityId(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoId(rs.getInt("BUSEO_ID"));
			dto.setJikwiId(rs.getInt("JIKWI_ID"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setTotalPay(rs.getInt("TOTALPAY"));

			result.add(dto);
		}
		rs.close();
		stmt.close();

		return result;
	}// listsSearch_EmpName(String empName);
	

	//4. 부서 검색
	public ArrayList<MemberDTO> listsSearch_BuseoId(int buseoId) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		String sql = String.format(
				"SELECT *" + " FROM" + " (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID"
						+ ", BASICPAY, SUDANG, (BASICPAY+SUDANG) AS TOTALPAY" + " FROM TBL_EMP)" + " WHERE BUSEO_ID=%d",
				buseoId);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityId(rs.getInt("CITY_ID"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoId(rs.getInt("BUSEO_ID"));
			dto.setJikwiId(rs.getInt("JIKWI_ID"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setTotalPay(rs.getInt("TOTALPAY"));

			result.add(dto);
		}
		rs.close();
		stmt.close();

		return result;
	}// listsSearch_BuseoId(int buseoId);
	
	
	//4. 직위 검색
		public ArrayList<MemberDTO> listsSearch_JikwiID(int jikwiId) throws SQLException
		{
			ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
			Statement stmt = conn.createStatement();
			String sql = String.format(
					"SELECT *" + " FROM" + " (SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID"
							+ ", BASICPAY, SUDANG, (BASICPAY+SUDANG) AS TOTALPAY" + " FROM TBL_EMP)" + " WHERE JIKWI_ID=%d",
					jikwiId);

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				MemberDTO dto = new MemberDTO();

				dto.setEmpId(rs.getInt("EMP_ID"));
				dto.setEmpName(rs.getString("EMP_NAME"));
				dto.setSsn(rs.getString("SSN"));
				dto.setIbsaDate(rs.getString("IBSADATE"));
				dto.setCityId(rs.getInt("CITY_ID"));
				dto.setTel(rs.getString("TEL"));
				dto.setBuseoId(rs.getInt("BUSEO_ID"));
				dto.setJikwiId(rs.getInt("JIKWI_ID"));
				dto.setBasicPay(rs.getInt("BASICPAY"));
				dto.setSudang(rs.getInt("SUDANG"));
				dto.setTotalPay(rs.getInt("TOTALPAY"));

				result.add(dto);
			}
			rs.close();
			stmt.close();

			return result;
		}// listsSearch_JikwiID(int jikwiId);

	
	
	// 인원 수 확인 담당 메소드
	public int count() throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
			result = rs.getInt("COUNT");
		rs.close();
		stmt.close();

		return result;
	}

	// 데이터 수정 담당 메소드
	public int modify(MemberDTO dto) throws SQLException
	{
		// UPDATE TBL_EMP SET EMP_NAME='가가가', SSN='111111-1111111', CITY_ID=8,
		// TEL='010-1111-1111', BUSEO_ID=1, JIKWI_ID=1, BASICPAY= 3333333, SUDANG = 111
		// WHERE EMP_ID = 1004
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format(
				"UPDATE TBL_EMP SET EMP_NAME='%s', SSN='%s'" + ", CITY_ID=%d, TEL='%s', BUSEO_ID=%d, JIKWI_ID=%d"
						+ ", BASICPAY= %d, SUDANG = %d WHERE EMP_ID = %d",
				dto.getEmpName(), dto.getSsn(), dto.getCityId(), dto.getTel(), dto.getBuseoId(), dto.getJikwiId(),
				dto.getBasicPay(), dto.getSudang(), dto.getEmpId());

		result = stmt.executeUpdate(sql);
		stmt.close();
		return result;
	}

	// 데이터 삭제 담당 메소드
	public int remove(int empId) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d", empId);
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
