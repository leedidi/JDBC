/*================
    Process.Java
==================*/

package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class Process
{
	// 주요 속성 구성 → 데이터베이스 액션 처리 전담 객체 → ScoreDAO
	private ScoreDAO dao;
	
	// 생성자 정의
	public Process()
	{
		dao = new ScoreDAO();
	
	}
	
	// 성적 입력 기능
	public void sungjukInsert()
	{
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			// 레코드 수 확인
			int count = dao.count();
			
			Scanner sc = new Scanner(System.in);
			
			do
			{
				System.out.println();
				System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));
				String name = sc.next();
				
				if (name.equals("."))
					break;
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				// ScoreDTO 객체 구성
				ScoreDTO dto = new ScoreDTO();
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				// dao 의 add() 메소드 호출
				int result = dao.add(dto);
				
				if (result > 0)
					System.out.println("성적 입력이 완료되었습니다.");
				
			} while (true);
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}// end sungjukInsert()
	
	
	// 성적 전체 출력 기능
	public void sungjukSelectAll()
	{
		try
		{	// dao 의 connection() 메소드 호출 → 데이터베이스 연결
			dao.connection();
			
			// dao 의 count() 메소드 호출 → 인원 수 확인
			int count = dao.count();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", count);
			System.out.println("번호  이름  국어  영어  수학  총점  평균  석차");
			
			//@ for문 선택시 foreach : 향상된 for문 나옴!
			//@ shift+tab 이전 탭으로 감(? 안댐 ㅠㅠ 왜지) (나만안되는건 아닌듯 다른사람들도들여쓰기만 되는듯!)
			//@ for each로 생성하면 박스가 빡빡빡 쳐서 나오는데 이때는 이거 됨....!
			for (ScoreDTO dto : dao.lists())
			{	//@ %s : string 문자, %d : int 숫자, %f : double 숫자
				System.out.printf("%3s  %4s  %4d  %5d  %5d  %5d  %5.1f  %5d\n"
								   , dto.getSid(), dto.getName()
								   , dto.getKor(), dto.getEng(), dto.getMat()
								   , dto.getTot(), dto.getAvg(), dto.getRank());
			}
			
			// dao 의 close() 메소드 호출 → 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}//end sungjukSelectAll()
	
	// 이름 검색 출력 기능
	public void sungjukSearchName()
	{
		try
		{
			// 검색할 이름 입력받기
			Scanner sc = new Scanner(System.in);
			System.out.print("검색할 이름 입력 : ");
			String name = sc.next();
			
			//-- 필요한 경우 이 과정에서 프로그래밍적으로 검증(검사) 수행
			//@ 이 과정을 유효성 검사라고 부름(Like 주민등록번호 유효성 검사)
			//@ 휴대폰 사러가면 써야하는 페이퍼들 많음... 이거 다 썼다고 통신사직원들이 회사에 바로 보내는게아니라
			//@ 직원들이 한번 미리 체크. 여기 형광펜 친 쪽에 서명안하셨네요! 날짜 안 쓰셨네요!
			//@ 이 과정이 클라이언트쪽에서 이뤄지는 유효성 검사..그후 본사에 팩스로 보냄(서버로 전송하는 개념)
			//@ 지금은 그냥 가져오기만 할 것
			
			// 데이터베이스 연결
			dao.connection();
			
			// dao 의 lists() 메소드 호출 → 매개변수로 검색할 이름을 문자열 형태로 넘겨주기
			//@ ScoreDTO 들이 담겨있는 ArrayList 반환
			ArrayList<ScoreDTO> arrayList = dao.lists(name);
			
			if (arrayList.size() > 0)
			{
				// 수신된 내용 출력
				System.out.println("번호  이름  국어  영어  수학  총점  평균  석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s  %4s  %4d  %5d  %5d  %5d  %5.1f  %5d\n"
							   , dto.getSid(), dto.getName()
							   , dto.getKor(), dto.getEng(), dto.getMat()
							   , dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
			}
			else
			{	
				// 수신된 내용이 없다는 상황 안내
				System.out.println("검색 결과가 존재하지 않습니다.");
			}
			
			// 데이터베이스 연결 종료
			dao.close();
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}// end sungjukSearchName()
	
	
	// 성적 수정 기능
	public void sungjukUpdate()
	{
		try
		{
			// 수정할 대상의 번호 입력받기
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 번호를 입력하세요 : ");
			int sid = sc.nextInt();
			
			//-- 입력받은 번호로 체크해야 할 로직 적용 삽입 가능
			
			// 데이터베이스 연결
			dao.connection();
			
			// 수정할 대상 수신 → 데이터 수정을 위해 대상 검색
			ArrayList<ScoreDTO> arrayList = dao.lists(sid);
			
			if (arrayList.size() > 0)
			{
				// 수신된 내용 출력
				System.out.println("번호  이름  국어  영어  수학  총점  평균  석차");
				
				for (ScoreDTO dto : arrayList)
				{	
					//@ 줄 간격 추후에 잘 맞추기~!!!
					System.out.printf("%3s  %4s  %4d  %5d  %5d  %5d  %5.1f  %5d\n"
							   , dto.getSid(), dto.getName()
							   , dto.getKor(), dto.getEng(), dto.getMat()
							   , dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
				System.out.println();
				System.out.print("수정 데이터 입력(이름 국어 영어 수학) : ");
				String name = sc.next();
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				// dto 구성
				ScoreDTO dto = new ScoreDTO();
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				dto.setSid(String.valueOf(sid));
				//@ sid int로 받아놓은 상태.. String으로 바꿔서 넣어줘야 함!
				
				int result = dao.modify(dto);
				if (result > 0)
				{
					System.out.println("수정이 완료되었습니다.");
				}
			}
			else
			{
				System.out.println("수정 대상이 존재하지 않습니다.");
			}
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}//end sungjukUpdate()
	
	// 성적 삭제 기능
	public void sungjukDelete()
	{
		try
		{
			// 삭제할 대상의 번호 입력받기
			Scanner sc = new Scanner(System.in);
			System.out.print("삭제할 번호를 입력하세요 : ");
			int sid = sc.nextInt();
			
			//-- 입력받은 번호로 체크해야 할 로직 적용 삽입 가능
			
			// 데이터베이스 연결
			dao.connection();
			
			// dao 의 lists() 메소드 호출
			ArrayList<ScoreDTO> arrayList = dao.lists(sid);
			
			if (arrayList.size() > 0 )
			{
				// 수신된 내용 출력
				System.out.println("번호  이름  국어  영어  수학  총점  평균  석차");
				
				//@ 네모네모 쳐지면 shift + tab으로 옆 칸 네모로 이동 가능
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s  %4s  %4d  %5d  %5d  %5d  %5.1f  %5d\n"
									  , dto.getSid(), dto.getName()
									  , dto.getKor(), dto.getEng(), dto.getMat()
									  , dto.getTot(), dto.getAvg(), dto.getRank());
				}
				System.out.print(">> 정말 삭제하시겠습니까?(Y/N) : ");
				
				String response = sc.next();
				if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(sid);
					if (result > 0)
					{
						System.out.println("삭제가 완료되었습니다.");
					}
				}
				else
				{
					System.out.println("취소되었습니다.");
				}
			} else
			{
				System.out.println("삭제할 대상이 존재하지 않습니다.");
			}
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}// end sungjukDelete()
	
	
}





