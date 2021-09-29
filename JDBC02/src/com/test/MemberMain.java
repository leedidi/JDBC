/*====================
    MemberMain.java
=====================*/

/*
 ○ TBL_MEMBER 테이블을 활용하여
    이름과 전화번호를 여러 건 입력받고, 전체 출력하는 프로그램을 구현한다.
    단, 데이터베이스 연동이 이루어져야 하고
    MemberDAO, MemberDTO 클래스를 활용해야 한다.

실행 예)

이름 전화번호 입력(1) : 이찬호 010-1111-1111
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(2) : 박혜진 010-2222-2222
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(3) : 윤유동 010-3333-3333
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(4) : .

-------------------------------
전체 회원 수 : 3명
-------------------------------
번호     이름       전화번호
1		이찬호 010-1111-1111
2		박혜진 010-2222-2222
3		윤유동 010-3333-3333
-------------------------------
*/

package com.test;

import java.util.Scanner;

import com.util.DBConn;

public class MemberMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			MemberDAO dao = new MemberDAO();
			
			int count = dao.count();
			
			do
			{
				System.out.printf("이름 전화번호 입력(%d) : ", (++count));		// 이찬호 010-1111-1111
				String name = sc.next();
				
				// 반복의 조건을 무너뜨리는 코드 구성
				//@ cntl + d : 한 줄 삭제
				if (name.equals("."))
					break;				
				
				String tel = sc.next();
				
				// MemberDTO 객체 구성
				//@ add 메소드에 넘겨주기 위해서 구성...(MemberDTO의 add)
				//@ 여기선 name, tel 만 필요.. sid는 시퀀스 사용해서 넣어버릴 거기 때문에 sid 필요 없음
				//@ 우리가 넘겨받기도, 넘길 필요도 없음. DTO 구성에는 문제 없음
				MemberDTO dto = new MemberDTO();
				dto.setName(name);
				dto.setTel(tel);
				
				// 데이터베이스에 데이터를 입력하는 메소드 호출 → add()
				int result = dao.add(dto);
				if (result > 0)
					System.out.println(">> 회원 정보 입력 완료~!!!");
				
			} while (true);
			
			/*
			-------------------------------
			전체 회원 수 : 3명
			-------------------------------
			번호     이름       전화번호
			1		이찬호 010-1111-1111
			2		박혜진 010-2222-2222
			3		윤유동 010-3333-3333
			-------------------------------
			*/
			
			System.out.println();
			//@ cntl + alt + 아래화살표 누르면 그대로 복사됨
			System.out.println("-------------------------------");
			System.out.printf("전체 회원 수 : %d명\n", dao.count());
			System.out.println("-------------------------------");
			System.out.println("번호     이름       전화번호");
			
			// 리스트 가져와 출력
			//@ 향상된 for문 -> foreach
			for (MemberDTO obj : dao.lists())
			{
				System.out.printf("%3s %6s %12s\n"
						, obj.getSid(), obj.getName(), obj.getTel());
			}
			
			System.out.println("-------------------------------");


		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		//@ finally -> test143 에서 사용했었음!
		//@ try ~ catch 랑 finally 는 무조건 같이 사용!
		finally 
		{
			try
			{
				DBConn.close();
				System.out.println("데이터베이스 연결 닫힘~!!!");
				System.out.println("프로그램 종료됨~!!!");
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
}

/*
이름 전화번호 입력(2) : 이찬호 010-2222-2222
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(3) : 박혜진 010-3333-3333
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(4) : 윤유동 010-4444-4444
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(5) : .

-------------------------------
전체 회원 수 : 4명
-------------------------------
번호     이름       전화번호
  1    김진희 010-1111-1111
  2    이찬호 010-2222-2222
  3    박혜진 010-3333-3333
  4    윤유동 010-4444-4444
-------------------------------
데이터베이스 연결 닫힘~!!!
프로그램 종료됨~!!!

*/












