/*====================
    MemberDTO.java
=====================*/

package com.test;

public class MemberDTO
//@ 번호, 이름, 전화번호 따로가 아닌 묶어서 가져다주기 가능...
//@ 묶어서 뺄 수도 있게 가능....
{
	// 주요 속성 구성
	private String sid, name, tel;

	// getter / setter 구성
	//@ 자동 만들어짐! 
	//@ 마우스 오른쪽 클릭 -> source -> generate getters and setters 클릭해서 생성
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

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}
	
	/*
	// getter / setter 구성 이해만 하기!
	public String getSid()
	{
		return sid;
	}
	
	// 이 이름도 약속임!
	public void setSid(String sid)
	{
		this.sid = sid;
		// 맨위 sid / 내가 넘겨주기로한 sid
	}
	*/
}
