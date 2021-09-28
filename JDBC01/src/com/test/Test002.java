/*============================================
   Test002.java 
   - main() 메소드를 포함하는 테스트 클래스
============================================*/
package com.test;

import java.sql.Connection;

import com.util.DBConnBackup;

//@ cntl + d : 줄 자체 지워짐
//@ I: 인터페이스 / C: 클래스
//@ 빗금이 그어진 C: 얘를 대체할 문법이 있으니까 가급적 쓰지 마...라는 얘기
//@ A가 붙어있는 C: 추상 클래스(abstract)

public class Test002
{   // main 쓰고 cntl + space 누르고 Enter
	public static void main(String[] args)
	{
		Connection conn = DBConnBackup.getConnection();
		//@ 노란색 느낌표: 경고(너지금 만든 Conn 암데서도 안썽...
	    //@ 필요없는거만든거 아니닝?)
		//@ 빨간색 느낌표: 에러
		// ※ DB 연결 과정이 가장 부하가 크기 때문에
		//    한 번 연결된 객체를 계속 사용할 수 있도록 Singleton 패턴 적용
		
		// getConnection() 메소드를 통해 정상적인 연결이 이루어 진 상황이라면....
		if (conn != null)
		{
			System.out.println("데이터베이스 연결 성공~!!!");
		}
		
		DBConnBackup.close();
		//-- close() 메소드 호출을 통해 연결 종료
	}

	// cntl + f11 -> 실행, 현재는 자바로 실행...
}
