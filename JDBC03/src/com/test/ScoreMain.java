/*===================
   ScoreMain.java
=================== */

// 같이 안풀 거...!
/*
○ 성적 처리 프로그램 구현 → 데이터베이스 연동 → ScoreDAO, ScoreDTO 클래스 활용

   여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
   총점, 평균을 연산하여 내용을 출력하는 프로그램을 구현한다.
   출력 시 번호(이름, 총점 등) 오름차순 정렬하여 출력한다.

실행 예)

1번 학생 성적 입력(이름 국어 영어 수학) : 김진령 80 75 60
2번 학생 성적 입력(이름 국어 영어 수학) : 이윤서 100 90 80
3번 학생 성적 입력(이름 국어 영어 수학) : 송해덕 80 85 80
4번 학생 성적 입력(이름 국어 영어 수학) : .

------------------------------------------------------
번호     이름       국어  영어  수학   총점    평균
------------------------------------------------------
  1     김진령       80    75    60    xxx     xx.x
  2     이윤서      100    90    80    xxx     xx.x
  3     송해덕       80    85    80    xxx     xx.x
------------------------------------------------------
 */


package com.test;

import java.util.Scanner;
import com.util.DBConn;

public class ScoreMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			ScoreDAO dao = new ScoreDAO();
			
			int count = dao.count();
			
			do
			{
				// 1번 학생 성적 입력(이름 국어 영어 수학) : 김진령 80 75 60
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
				
				// 입력 구문....!!!! << *** 요기를 빼먹었었음!
				int result = dao.add(dto);
				
			} while (true);
			
			/*
			------------------------------------------------------
			번호     이름       국어  영어  수학   총점    평균
			------------------------------------------------------
			  1     김진령       80    75    60    xxx     xx.x
			  2     이윤서      100    90    80    xxx     xx.x
			  3     송해덕       80    85    80    xxx     xx.x
			------------------------------------------------------
			 */
			
			System.out.println();
			System.out.println("------------------------------------------------------");
			System.out.println("번호     이름       국어  영어  수학   총점    평균");
			System.out.println("------------------------------------------------------");
			
			// 리스트 가져와 출력
			for (ScoreDTO obj : dao.lists())
			{
				//System.out.printf("%3s %6s %3d %3d %3d %4d %5d\n"
				//			     , obj.getSid(), obj.getName(), obj.getKor(), obj.getMat()
				//			     , obj.getEng(), obj.getSum(), obj.getAvg());
				System.out.printf("%3s %8s %6d %6d %6d %6d %7.1f\n"
							     , obj.getSid(), obj.getName(), obj.getKor(), obj.getMat()
							     , obj.getEng(), obj.getKor()+obj.getMat()+obj.getEng(), (obj.getKor()+obj.getMat()+obj.getEng())/3.0);
			}
			
			System.out.println("------------------------------------------------------");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}// end Scoremain

/*
1번 학생 성적 입력(이름 국어 영어 수학) : 김진령 80 75 60
2번 학생 성적 입력(이름 국어 영어 수학) : 이윤서 100 90 80
3번 학생 성적 입력(이름 국어 영어 수학) : 송해덕 80 85 80
4번 학생 성적 입력(이름 국어 영어 수학) : .

------------------------------------------------------
번호     이름       국어  영어  수학   총점    평균
------------------------------------------------------
  1    김진령  80  60  75  215  71.7
  2    김진령  80  60  75  215  71.7
  3    이윤서 100  80  90  270  90.0
  4    송해덕  80  80  85  245  81.7
  5    홍길동  80  90  90  260  86.7
------------------------------------------------------
*/

/*
Q. 총점이랑 평균 구할 때 
DTO에 속성을 추가하는 경우랑,
속성 구성은 안하고 출력시에만 계산하는 방식이 있었는데
이 중에 보통 어떤식으로 구성하나요?
-> 지금은 작은 범위.... 둘 다 상관 없음! 
-> 나중에 업무 확장되면 좀 달라질수도 있음...
어딘가에 계속 누적 필요 : dto 안에 구성이 바람직 / 
값으로 저장, 기억필요는 없고 시각적 조회만 필요: 메소드에서 보여주는거도 괜찮음

Q. dto에서 속성 구성할때 국어 영어 수학은 int로 처리하던 string으로 처리하던
-> 지금은 작은 범위.... 둘 다 상관 없음! 편의상 string도 괜찮음
-> 나중에 업무 확장되고, 여기저기서 산술적 문제 연산 필요하면, int나 double로 하는게 바람직!
 */








