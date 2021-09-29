/*===================================
    ScoreDTO.java
    - 데이터 보관 및 전송 전용 객체
====================================*/

package com.test;

public class ScoreDTO
{
	// 주요 속성 구성
	//@ 속성만 존재하는 클래스 만들었던거 떠올리기~!!!
	//@ 이름부여, getter/setter 등등 만드는 개념 잘 조합해서 하면 오키오키
	//@ 흔들림 없이 머릿속에 그려낼수 있어야 그후 확장해서 자바 빈 개념 할때 헷깔리지 않음!

	//@ alt+shift+A 하면 열 복사 모드! 
	//@ 한번에 쓰기 가능... 한번 더 하면 풀림!
	
	private String sid, name;
	private int kor, eng, mat;
	private int tot, rank;
	private double avg;
	
	// getter / setter 구성
	public String getSid()
	{
		return sid;
	}
	public void setSid(String sid)
	{
		this.sid = sid;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getKor()
	{
		return kor;
	}
	public void setKor(int kor)
	{
		this.kor = kor;
	}
	public int getEng()
	{
		return eng;
	}
	public void setEng(int eng)
	{
		this.eng = eng;
	}
	public int getMat()
	{
		return mat;
	}
	public void setMat(int mat)
	{
		this.mat = mat;
	}
	public int getTot()
	{
		return tot;
	}
	public void setTot(int tot)
	{
		this.tot = tot;
	}
	public int getRank()
	{
		return rank;
	}
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	public double getAvg()
	{
		return avg;
	}
	public void setAvg(double avg)
	{
		this.avg = avg;
	}
	
}
