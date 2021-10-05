package com.test;

import java.util.Scanner;

public class MemberProcess
{
	// 주요 속성 구성 → 데이터베이스 액션 처리 전담 객체 → MemberDAO
	private MemberDAO dao;
	
	// 생성자 정의
	public MemberProcess()
	{
		dao = new MemberDAO();
	}
	
	/*
	=====[ 직원 관리 ]=====
	1. 직원 정보 입력
	2. 직원 전체 출력
	   - 사번 정렬
	   - 이름 정렬
	   - 부서 정렬
	   - 직위 정렬
	   - 급여 내림차순 정렬
	3. 직원 검색 출력
	   - 사번 검색
	   - 이름 검색
	   - 부서 검색
	   - 직위 검색
	4. 직원 정보 수정
	5. 직원 정보 삭제
	========================
	>> 메뉴 선택(1~5, -1종료) :  
	 */
	
	// 1. 직원 정보 입력 
	//! 강원,경기,경남,경북 등.... 추가하기!
	public void JikwonInfoInsert()
	{
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			// 레코드 수 확인
			int count = dao.count();
			
			Scanner sc = new Scanner(System.in);
			
			/*
			이름 : 김진희
			주민등록번호(yymmdd-nnnnnnn) : 990320-2234567
			입사일(yyyy-mm-dd) : 2019-07-14
			지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/) : 경기
			전화번호 : 010-1111-1111
			부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : 개발부
			직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : 대리
			기본급(최소 400000원 이상) : 500000
			-@ 각 직위의 최소금액 가져와서 그 이상... 
			수당 : 200000
			
			직원 정보 입력 완료~!!! 
			 */
			// 부서이름, 직위이름 추가해줘야함....
			do
			{
				System.out.println();
				System.out.println("이름 : ");
				String empName = sc.next();
				System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				System.out.print("입사일(yyyy-mm-dd) : ");
				String ibsaDate = sc.next();
				System.out.print("지역(");
				// 강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/
				System.out.print(") : ");
				String cityName = sc.next();
				System.out.print("전화번호 : ");
				String tel = sc.next();
				System.out.println("부서(");
				// 개발부/기획부/영업부/인사부/자재부/총무부/홍보부/
				//@ 부서이름 가져오는 select.. 그값 반환하는 거 가져와서 사이사이 /... 
				//@ DAO 에 메소드를 하나 더 구성!
				System.out.println(" ) :");
				String buseoName = sc.next();
				System.out.println("직위(");
				// 사장/전무/상무/이사/부장/차장/과장/대리/사원/
				//@ DAO 에 메소드를 하나 더 구성!
				System.out.println(") : ");
				String jikwiName = sc.next();
				System.out.println("기본급(최소 ");
				// 4000000
				System.out.println("원 이상) : ");
				int basicPay = sc.nextInt();
				System.out.println("수당 : ");
				int sudang = sc.nextInt();
				
				// memberDTO 객체 구성
				MemberDTO dto = new MemberDTO();
				dto.setEmpName(empName);
				dto.setSsn(ssn);
				dto.setIbsaDate(ibsaDate);
				dto.setCityName(cityName);
				dto.setTel(tel);
				dto.setBuseoName(buseoName);
				dto.setJikwiName(jikwiName);
				dto.setBasicPay(basicPay);
				dto.setSudang(sudang);
				
				// dao 의 add() 메소드 호출
				int result = dao.add(dto);
				
				if(result > 0)
					System.out.println("직원 정보 입력 완료~!!!");
				
			} while (true);
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}// end JikwonInfoInsert()
	
	// 2. 직원 전체 출력
	// 2-1. 사번 정렬
	// 2-2. 이름 정렬
	// 2-3. 부서 정렬
	// 2-4. 직위 정렬
	// 2-5. 급여(내림차순) 정렬
	
	// 3. 직원 검색 출력
	// 3-1. 사번 검색
	// 3-2. 이름 검색
	// 3-3. 부서 검색
	// 3-4. 직위 검색
	
	// 4. 직원 정보 수정
	
	// 5. 직원 정보 삭제
	
	
	
}






















