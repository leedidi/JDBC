/*==============================================
    MemberProcess.java
    - 콘솔 기반 서브 메뉴 입출력 전용 클래스
============================================= */

package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberProcess
{
	// 주요 속성 구성
	private MemberDAO dao;

	// 생성자 정의
	public MemberProcess()
	{
		dao = new MemberDAO();
	}
	// 직원 정보 입력 메소드 정의
	public void memberInsert()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			//@ 이런 생각의 흐름 순서가 되어야 코딩할수 있는 거!
			//@ -- 아래... 거꾸로 (안내메시지하려면 - 리스트 하려면 - 데이터베이스 구성해야 가능!)
			//@ 지금은 디테일한 코드에 신경쓸때가 X
			//@ 큰 틀! 잡아야 함
			//@ 그림그릴때도 내가 스케치북에 큰 틀을 잡아야 그림 그림...
			//@ 위치도 안잡고 눈 그리는 스킬만 있으면? 스케치북에 얼굴 못 그림
			//@ 오히려 그렸던 눈 지워야 함
			//@ 틀, 뼈대 잡혀야.. 상세 코드들 넣을게 뭐구나, 생각하게 됨!
			
			// 데이터베이스 연결 
			dao.connection();
			
			// 지역 리스트 구성
			ArrayList<String> citys = dao.searchCity();
			StringBuilder cityStr = new StringBuilder();
			for (String city : citys)
				cityStr.append(city + "/");
			// "강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/"
			
			
			//@ 위 지역 리스트와 같은 방법으로 아래 리스트들 구성
			// 부서 리스트 구성
			
			// 직위 리스트 구성
			
			// 사용자에게 안내 메세지 출력(보여지는 화면 처리)
			//@ "강원" + "/" + "경기"
			//@ "강원/경기/경남..."
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	// 직원 전체 출력 메소드 정의
	public void memberLists()
	{
		
	}
	
	// 직원 검색 출력 메소드 정의
	public void memberSearch()
	{
		
	}
	
	// 직원 정보 수정 메소드 정의
	public void memberUpdate()
	{
		
	}
	
	// 직원 정보 삭제 메소드 정의
	public void memberDelete()
	{
		
	}
	
}
