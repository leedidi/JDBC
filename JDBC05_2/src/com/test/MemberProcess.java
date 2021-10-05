/*==============================================
    MemberProcess.java
    - 콘솔 기반 서브 메뉴 입출력 전용 클래스
============================================= */
//@ DAO는 connection을 멤버로 구성... Process는 DAO를 멤버로 구성

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
			//@ --> 아래... 거꾸로 생각하기 (안내메시지하려면 -> 리스트 필요, 리스트 출력 하려면 -> 데이터베이스 구성해야 가능!)
			//@ 지금은 디테일한 코드에 신경쓰고 집중하려고 하면 안 됨
			//@ 큰 틀! 잡아야 함
			//@ 그림 그릴때도 내가 스케치북에 큰 틀을 잡아야 그림 그릴 수 있음...
			//@ 위치도 안 잡고 눈 그리는 스킬만 있으면? 스케치북에 얼굴 못 그림
			//@ 오히려 그렸던 눈 지워야 함
			//@ 틀, 뼈대 잡혀야... 상세 코드들 넣을게 뭐구나, 생각하게 됨!
			//@ 백일장, 사생대회... 누가 앞에 몇줄만 써주면 그뒤 쓸수 있을거 같은데. 원고지에 글 어떻게 시작해야할지 모르겠어...
			//@ -> 글을 많이 안 써봐서 그럼. 글을 많이 쓰려면 많이 읽어봐야 하고, 그냥이 아니라 많이 생각하며 읽어야 함...
			//@ 기본적 틀과 뼈대가 잡혀야 상세한 코드들이 그다음 넣을 게 뭐구나, 생각할 수 있게 됨
			
			// 데이터베이스 연결 
			dao.connection();
			
			// 지역 리스트 구성
			ArrayList<String> citys = dao.searchCity();
			StringBuilder cityStr = new StringBuilder();
			for (String city : citys) //@ city들에서 city 하나씩 꺼냄
				cityStr.append(city + "/");
			// "강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/"
			//@ 이만큼의 StringBuilder 객체가 만들어짐
			
			//@ 위 지역 리스트와 같은 방법으로 아래 리스트들 구성
			// 부서 리스트 구성
			ArrayList<String> buseos = dao.searchBuseo();
			StringBuilder buseoStr = new StringBuilder();
			for (String buseo : buseos)
				buseoStr.append(buseo + "/");
			// 개발부/기획부/영업부/인사부/자재부/총무부/홍보부/"
			
			// 직위 리스트 구성
			ArrayList<String> jikwis = dao.searchJikwi();
			StringBuilder jikwiStr = new StringBuilder();
			for (String jikwi : jikwis)
				jikwiStr.append(jikwi + "/");
			//사장/전무/상무/이사/부장/차장/과장/대리/사원/
			
			// 사용자에게 안내 메세지 출력(보여지는 화면 처리)
			/*
			직원 정보 입력 -------------------------------------
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
			---------------------------------- 직원 정보 입력 
			 */
			//@ cntl + alt + 화살표 누르면 복사
			//@ alt + shif + a 열 복사 모드
			
			System.out.println();
			System.out.println("직원 정보 입력 -------------------------------------");
			//@ 사용자에게 입력받을시에는 println이 아니라 print!
			System.out.print("이름 : ");
			String empName = sc.next();	
			System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
			String ssn = sc.next();
			System.out.print("입사일(yyyy-mm-dd) : ");
			String ibsaDate = sc.next();
			System.out.printf("지역(%s) : ", cityStr.toString());
			String cityName = sc.next();
			System.out.print("전화번호 : ");
			String tel = sc.next();
			System.out.printf("부서(%s) : ", buseoStr.toString());
			String buseoName = sc.next();
			System.out.printf("직위(%s) : ", jikwiStr.toString());
			String jikwiName = sc.next();
			System.out.printf("기본급(최소 %d원 이상) : ", dao.searchBasicPay(jikwiName));
			int basicPay = sc.nextInt();
			System.out.print("수당 : ");
			int sudang = sc.nextInt();
			
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
			
			int result = dao.add(dto);
			if(result > 0)
				System.out.println("직원 정보 입력 완료~!!!");
			
			System.out.println("---------------------------------- 직원 정보 입력 ");			
			
			//@ "강원" + "/" + "경기"
			//@ "강원/경기/경남..."
			//@ 하나로 꿍~ 구성되는 문자열이라면 String 을 쓰면 되겠지만...
			//@ 계속 변경되는 문자열이라면 String이 아닌 StringBuilder나 StringBuffer 사용... 
			//@ StringBuffer가 동기화, 동시성 지원되기 때문에 StringBuilder가 성능이 더 좋음!
			//@ String + String 연산은 새로운 String을 생성.
			//@ 즉, 메모리 할당과 메모리 해제를 발생시키며 더하는 연산이 많아진다면 성능적으로 좋지 않음
			//@ StringBuilder는 String과 문자열을 더할 때 새로운 객체를 생성하는 것이 아니라 기존의 데이터에 더하는
			//@ 방식을 사용하기 때문에 속도도 빠르며 상대적으로 부하가 좋다.
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
	
	// 직원 전체 출력 메소드 정의
	public void memberLists()
	{
		Scanner sc = new Scanner(System.in);
		
		// 서브 메뉴 출력(안내)
		System.out.println();
		System.out.println("1. 사번 정렬");				// EMP_ID
		System.out.println("2. 이름 정렬");				// EMP_NAME
		System.out.println("3. 부서 정렬");				// BUSEO_NAME
		System.out.println("4. 직위 정렬");				// JIKWI_NAME
		System.out.println("5. 급여 내림차순 정렬");	// PAY DESC
		System.out.print(">> 항목 선택(1~5, -1종료) : ");
		String menuStr = sc.next();
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			if (menu == -1)
				return;
			
			String key = "";
			switch (menu)
			{
				case 1:
					key = "EMP_ID";
					break;
				case 2:
					key = "EMP_NAME";
					break;
				case 3:
					key = "BUSEO_NAME";
					break;
				case 4:
					key = "JIKWI_NAME";
					break;
				case 5:
					key = "PAY DESC";
					break;
			}
			// ORDER BY %s 니까 앞에 따로 띄어쓰기 필요 X
			
			// 데이터베이스 연결
			dao.connection();
			
			// 직원 리스트 출력
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", dao.memberCount());
			System.out.println("사번  이름     주민번호      입사일   지역   전화번호     부서  직위  기본급  수당   급여");
			ArrayList<MemberDTO> memList = dao.lists(key);
			//@ 타이핑보다 반 빠르게.. 뭐 들어갈까 예측해보기
			for (MemberDTO memberDTO : memList)
			{
				System.out.printf("%5d %4s %14s %10s %4s %12s %4s %3s %8d %8d %8d\n"
						//@ 요부분 수정하기..! -> 수정 완료
						//@ 번호 (-)부분 줄 안맞는건 이클립스 문제.... 신경쓰지 X
								, memberDTO.getEmpId(), memberDTO.getEmpName()
								, memberDTO.getSsn(), memberDTO.getIbsaDate()
								, memberDTO.getCityName(), memberDTO.getTel()
								, memberDTO.getBuseoName(), memberDTO.getJikwiName()
								, memberDTO.getBasicPay(), memberDTO.getSudang(), memberDTO.getPay());
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
	}// end memberList();
	
	// 직원 검색 출력 메소드 정의
	public void memberSearch()
	{
		Scanner sc = new Scanner(System.in);
		
		// 서브 메뉴 구성
		System.out.println();
		System.out.println("1. 사번 검색");			// EMP_ID
		System.out.println("2. 이름 검색");			// EMP_NAME
		System.out.println("3. 부서 검색");			// BUSEO_NAME
		System.out.println("4. 직위 검색");			// JIKWI_NAME
		System.out.print(">> 항목 선택(1~4, -1종료) : ");
		String menuStr = sc.next();
		//@ 정수 아닌 형식 입력했을때 하단에서 try catch exception 잡기 위해 일단 String으로 받음
		//@ 숫자가 아닌 값을 입력했을 때의 에러 처리 때문
		//@ 실무에서도 먼저 문자로 입력받고 숫자로 변환하는게 훨씬 더 안정적임~!!!
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			if (menu == -1)
				return;
			
			String key = "";
			String value = "";
			
			switch (menu)
			{
				case 1:
					key = "EMP_ID";
					System.out.print("검색할 사원번호 입력 : ");
					value = sc.next();
					break;
				case 2:
					key = "EMP_NAME";
					System.out.print("검색할 이름 입력 : ");
					value = sc.next();
					break;
				case 3:
					key = "BUSEO_NAME";
					System.out.print("검색할 부서명 입력 : ");
					value = sc.next();
					break;
				case 4:
					key = "JIKWI_NAME";
					System.out.print("검색할 직위명 입력 : ");
					value = sc.next();
					break;
			}
			
			// 데이터베이스 연결
			dao.connection();
			
			// 검색 결과 출력
			System.out.println();
			System.out.printf("검색 인원 : %d명\n", dao.membercount(key, value));
			System.out.println("사번  이름     주민번호      입사일   지역   전화번호     부서  직위  기본급  수당   급여");
			ArrayList<MemberDTO> memList = dao.searchLists(key, value);
			for (MemberDTO memberDTO : memList)
			{
				System.out.printf("%5d %4s %14s %10s %4s %12s %4s %3s %8d %8d %8d\n"
								, memberDTO.getEmpId(), memberDTO.getEmpName()
								, memberDTO.getSsn(), memberDTO.getIbsaDate()
								, memberDTO.getCityName(), memberDTO.getTel()
								, memberDTO.getBuseoName(), memberDTO.getJikwiName()
								, memberDTO.getBasicPay(), memberDTO.getSudang(), memberDTO.getPay());
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}			
		}
		
	}// end memberSearch()
	
	
	// 직원 정보 수정 메소드 정의
	public void memberUpdate()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			// 수정할 대상 입력받기
			System.out.print("수정할 직원의 사원번호 입력 : ");
			String value = sc.next();
			
			// 데이터베이스 연결
			dao.connection();
			
			ArrayList<MemberDTO> members = dao.searchLists("EMP_ID", value);
			
			if (members.size() > 0)
			{
				// 지역 리스트 구성
				ArrayList<String> citys = dao.searchCity();
				StringBuilder cityStr = new StringBuilder();
				for (String city : citys)
					cityStr.append(city + "/");
				
				// 부서 리스트 구성
				ArrayList<String> buseos = dao.searchBuseo();
				StringBuilder buseoStr = new StringBuilder();
				for (String buseo : buseos)
					buseoStr.append(buseo + "/");
				
				// 직위 리스트 구성
				ArrayList<String> jikwis = dao.searchJikwi();
				StringBuilder jikwiStr = new StringBuilder();
				for (String jikwi : jikwis)
					jikwiStr.append(jikwi + "/");
				
				// 사용자에게 안내 메세지 출력(보여지는 화면 처리)
				/*
				직원 정보 수정 -------------------------------------
				기존 이름 : 김진희
				수정 이름 : - (@ 그대로 사용하게 해줌)
				기존 주민등록번호(yymmdd-nnnnnnn) : 990320-2234567
				수정 주민등록번호(yymmdd-nnnnnnn) : -
				기존 입사일(yyyy-mm-dd) : 2019-07-14
				수정 입사일(yyyy-mm-dd) :
				기존 지역 : 경기
				수정 지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/) : 
				기존 전화번호 : 010-1111-1111
				수정 전화번호 :
				기존 부서 : 개발부
				수정 부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : 
				기존 직위 : 대리
				수정 직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : 
				기존 기본급 : 500000
				수정 기본급(최소 400000원 이상) : 
				기존 수당 : 200000
				수정 수당 : 
				
				직원 정보 수정 완료~!!!
				---------------------------------- 직원 정보 수정 
				 */
				
				MemberDTO mMember = members.get(0);
				//@ 하나만 가져 올 거... 이게 우리가 가져올 멤버값
				//@ type은 arraylist지만 0번방만 채워져 있을 것이기 때문
				int mEmpId = mMember.getEmpId();
				String mEmpName = mMember.getEmpName();
				String mSsn = mMember.getSsn();
				String mIbsaDate = mMember.getIbsaDate();
				String mCityName = mMember.getCityName();
				String mTel = mMember.getTel();
				String mBuseoName = mMember.getBuseoName();
				String mJikwiName = mMember.getJikwiName();
				int mBasicPay = mMember.getBasicPay();
				int mSudang = mMember.getSudang();
				
				System.out.println();
				System.out.println("직원 정보 수정 -------------------------------------");
				System.out.printf("기존 이름 : %s%n", mEmpName);
				System.out.print("수정 이름 : ");
				String empName = sc.next();
				if (empName.equals("-"))
					empName = mEmpName;
				
				// 내가 작성한 코드
				System.out.printf("기존 주민등록번호(yymmdd-nnnnnnn) : %s%n", mSsn);
				System.out.print("수정 주민등록번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				if (ssn.equals("-"))
					ssn = mSsn;
				
				System.out.printf("기존 입사일(yyyy-mm-dd) : %s%n", mIbsaDate);
				System.out.print("수정 입사일(yyyy-mm-dd) : ");
				String ibsaDate = sc.next();
				if (ibsaDate.equals("-"))
					ibsaDate = mIbsaDate;
				
				System.out.printf("기존 지역 : %s%n", mCityName);
				System.out.printf("수정 지역(%s) : ", cityStr);
				//@ System.out.printf("수정 지역(%s) : ", cityStr.toString()); 이렇게 안해도 되는듯!
				String cityName = sc.next();
				if (cityName.equals("-"))
					cityName = mCityName;
				
				System.out.printf("기존 전화번호 : %s%n", mTel);
				System.out.print("수정 전화번호 : ");
				String tel = sc.next();
				if (tel.equals("-"))
					tel = mTel;
				
				System.out.printf("기존 부서 : %s%n", mBuseoName);
				System.out.printf("수정 부서(%s) : ", buseoStr);
				String buseoName = sc.next();
				if (buseoName.equals("-"))
					buseoName = mBuseoName;
				
				System.out.printf("기존 직위 : %s%n", mJikwiName);
				System.out.printf("수정 직위(%s) : ", jikwiStr);
				String jikwiName = sc.next();
				if (jikwiName.equals("-"))
					jikwiName = mJikwiName;
				
				System.out.printf("기존 기본급 : %d%n", mBasicPay);
				System.out.printf("수정 기본급(최소 %d원 이상) : ", dao.searchBasicPay(jikwiName)); //@ 주의! 변경된 직위 기본급
				//int basicPay = sc.nextInt();
				//@ 기본급 입력시 "-" 입력 가능해야 함... 그러니까 int로 받아 올 수 없음!
				//@ "-" 숫자모양의 문자열 아니기 때문
				String basicPayStr = sc.next();
				//@ -> 때문에 일단 문자열로 입력 받아야 함!
				int basicPay = 0; //@ 바꿔받을 basicPay 변수
				if (basicPayStr.equals("-"))
					basicPay = mBasicPay;
				else
					basicPay = Integer.parseInt(basicPayStr); //@ basicPay를 형변환해서 담아주세요!
				
				System.out.printf("기존 수당 : %d%n", mSudang);
				System.out.print("수정 수당 :  ");
				String sudangStr = sc.next();
				int sudang = 0;
				if (sudangStr.equals("-"))
					sudang = mSudang;
				else
					sudang = Integer.parseInt(sudangStr);
				
				//@ 변경할 속성값 다 담아둔 상태
				
				// 새로 입력받은(수정한) 내용을 통해 DTO 구성
				MemberDTO member = new MemberDTO();
				member.setEmpId(mEmpId);
				member.setEmpName(empName);
				member.setSsn(ssn);
				member.setIbsaDate(ibsaDate);
				member.setCityName(cityName);
				member.setTel(tel);
				member.setBuseoName(buseoName);
				member.setJikwiName(jikwiName);
				member.setBasicPay(basicPay);
				member.setSudang(sudang);
				
				int result = dao.modify(member);
				if (result > 0)
					System.out.println("직원 정보 수정 완료~!!!");
				
				System.out.println("---------------------------------- 직원 정보 수정");
				
			}
			else
			{
				System.out.println("수정 대상을 검색하지 못했습니다.");
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		finally
		{
			try
			{
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		
	}// end memberUpdate()
	
	// 직원 정보 삭제 메소드 정의
	public void memberDelete()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			System.out.print("삭제할 직원의 사원번호 입력 : ");
			String value = sc.next();
			
			// 직원 정보 확인 후 삭제여부 결정
			
			dao.connection();
			ArrayList<MemberDTO> members = dao.searchLists("EMP_ID", value);
			//@ 우리가 쓰게 될 공간은 0번째 공간만. 여러개가 되지 않을거임...
			
			if (members.size() > 0)
			{
				System.out.println();
				System.out.println("사번  이름     주민번호      입사일   지역   전화번호     부서  직위  기본급  수당   급여");
				for (MemberDTO memberDTO : members)
				{
					System.out.printf("%5d %4s %14s %10s %4s %12s %4s %3s %8d %8d %8d\n"
							, memberDTO.getEmpId(), memberDTO.getEmpName()
							, memberDTO.getSsn(), memberDTO.getIbsaDate()
							, memberDTO.getCityName(), memberDTO.getTel()
							, memberDTO.getBuseoName(), memberDTO.getJikwiName()
							, memberDTO.getBasicPay(), memberDTO.getSudang(), memberDTO.getPay());
				}
				
				System.out.print("\n정말 삭제하시겠습니까(Y/N) : ");
				String response = sc.next();
				if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(Integer.parseInt(value));
					//@ value는 현재 String...remove에는 int 로 넣어줘야 함
					if (result > 0)
					{
						System.out.println("직원 정보가 정상적으로 삭제되었습니다.");
					}
				}
			}
			else
			{
				System.out.println("삭제 대상을 검색하지 못하였습니다.");
			}
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
	
}












