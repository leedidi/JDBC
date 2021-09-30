package com.test;


public class MemberDTO
{
	// 사번  이름  주민번호  입사일  지역  전화번호  부서  직위  기본급  수당  급여
	/*
	CREATE TABLE TBL_EMP
	( EMP_ID    NUMBER(5)
	, EMP_NAME  VARCHAR2(30)
	, SSN       CHAR(14)
	, IBSADATE  DATE
	, CITY_ID   NUMBER(5)
	, TEL       VARCHAR2(20)
	, BUSEO_ID  NUMBER(5)
	, JIKWI_ID  NUMBER(5)
	, BASICPAY  NUMBER(10)
	, SUDANG    NUMBER(10)
	); + 급여
	  */
	
	private String empName, ssn, ibsaDate, tel;
	private int empId, cityId, buseoId, jikwiId, basicPay, sudang, totalPay;
	
	public String getEmpName()
	{
		return empName;
	}
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	public String getSsn()
	{
		return ssn;
	}
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}
	public String getIbsaDate()
	{
		return ibsaDate;
	}
	public void setIbsaDate(String ibsaDate)
	{
		this.ibsaDate = ibsaDate;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	public int getEmpId()
	{
		return empId;
	}
	public void setEmpId(int empId)
	{
		this.empId = empId;
	}
	public int getCityId()
	{
		return cityId;
	}
	public void setCityId(int cityId)
	{
		this.cityId = cityId;
	}
	public int getBuseoId()
	{
		return buseoId;
	}
	public void setBuseoId(int buseoId)
	{
		this.buseoId = buseoId;
	}
	public int getJikwiId()
	{
		return jikwiId;
	}
	public void setJikwiId(int jikwiId)
	{
		this.jikwiId = jikwiId;
	}
	public int getBasicPay()
	{
		return basicPay;
	}
	public void setBasicPay(int basicPay)
	{
		this.basicPay = basicPay;
	}
	public int getSudang()
	{
		return sudang;
	}
	public void setSudang(int sudang)
	{
		this.sudang = sudang;
	}
	public int getTotalPay()
	{
		return totalPay;
	}
	public void setTotalPay(int totalPay)
	{
		this.totalPay = totalPay;
	}
	
}
