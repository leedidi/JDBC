/*=======================
    Process.java
========================*/

package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreProcess
{
	// 주요 속성 구성
	private ScoreDAO dao;
	
	// 생성자 정의
	public ScoreProcess()
	{
		dao = new ScoreDAO();
	}
	
	// 성적 입력 기능
	public void sungjikInsert()
	{
		try
		{
			dao.connection();
			int count = dao.count();
			Scanner sc = new Scanner(System.in);
			
			do
			{
				System.out.println();
				System.out.printf("%d번 학생의 성적 입력(이름 국어 영어 수학) : ", (++count));
				String name = sc.next();
				
				if (name.equals("."))
					break;
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				ScoreDTO dto = new ScoreDTO();
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				int result = dao.add(dto);
				
				if (result > 0)
					System.out.println("성적 입력이 완료되었습니다.");
				
				
			} while (true);
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// 성적 전체 출력 기능
	public void sungjukSelectAll()
	{
		try
		{
			dao.connection();
			
			int count = dao.count();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", count);
			System.out.println("번호  이름   국어 영어 수학 총점 평균 석차");
			
			for (ScoreDTO dto : dao.lists())
			{
				System.out.printf("%3s %4s %4d %4d %4d %4d %5.1f %3d\n"
									, dto.getSid(), dto.getName()
									, dto.getKor(), dto.getEng(), dto.getMat()
									, dto.getTot(), dto.getAvg(), dto.getRank());
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// 이름 검색 출력 기능
	public void sungjukSearchName()
	{
		try
		{
			// 검색할 이름 입력받기
			Scanner sc = new Scanner(System.in);
			System.out.print("검색할 이름 입력 : ");
			String name = sc.next();
			
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.lists(name);
			
			if (arrayList.size() > 0)
			{
				System.out.println("번호  이름   국어 영어 수학 총점 평균 석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %4s %4d %4d %4d %4d %5.1f %3d\n"
										, dto.getSid(), dto.getName()
										, dto.getKor(), dto.getEng(), dto.getMat()
										, dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
			}
			else
			{
				System.out.println("검색 결과가 존재하지 않습니다.");
			}
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// 성적 수정 기능
	public void sungjukUpdate()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 번호를 입력하세요 : ");
			int sid = sc.nextInt();
			
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.lists(sid);
			
			if (arrayList.size() > 0)
			{
				System.out.println("번호  이름   국어 영어 수학 총점 평균 석차");
				
				for (ScoreDTO dto : arrayList)
				{	
					System.out.printf("%3s %4s %4d %4d %4d %4d %5.1f %3d\n"
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
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// 성적 삭제 기능
	public void sungjukDelete()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("삭제할 번호를 입력하세요 : ");
			int sid = sc.nextInt();
			
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.lists(sid);
			
			if (arrayList.size() > 0)
			{
				System.out.println("번호  이름   국어 영어 수학 총점 평균 석차");
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %4s %4d %4d %4d %4d %5.1f %3d\n"
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
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	

}
