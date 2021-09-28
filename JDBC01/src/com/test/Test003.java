/*===========================
   Test003.java 
   - 데이터베이스 연결 실습
   - 데이터 입력 실습
=============================*/


package com.test;

import java.sql.Connection;
import java.sql.Statement;
//@ cntl+shift 사용해서 입력시 java.beans 가 아니라 javq.sql 이었음 주의!

import com.util.DBConn;

public class Test003
{
   public static void main(String[] args)
   {
      Connection conn = DBConn.getConnection();
      
      if (conn == null)
      {
         System.out.println("데이터베이스 연결 실패~!!!");
         System.exit(0);
      }
      
       //System.out.println("데이터베이스 연결 성공~!!!");
      
      try
      {
    	  // 작업 객체 준비
    	  Statement stmt = conn.createStatement();

    	  // 데이터 입력 쿼리 실행 과정
    	  // 한 번 실행하면 다시 실행하지 못하는 상황이다.
    	  // 기본키 제약조건이 설정되어 있으므로
    	  // 동일한 키 값이 중복될 수 없기 때문이다.
    	  
          // 쿼리문 준비
    	  String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(2, '채지윤', '010-2222-2222')";
    	  //-- 주의. 쿼리문 끝에 『;』 붙이지 않는다.
    	  //-- 주의. 자바에서 실행한 DML 구문은 내부적으로 자동 commit 된다. (@ 오토커밋됨)
    	  //-- 주의. 오라클에서 트랜잭션 처리가 끝나지 않으면
    	  //         데이터 액션 처리가 이루어지지 않는다. (@ 저장안한 잔여물들을 자바에서 실행해버리면
    	  //                                                  자바에서 자동 커밋시켜버릴수 있으므로 막아놓음)
    	  
    	  // stmt.excuteQuery()
    	  // stmt.excuteUpdate() --@ 오라클에 이 처리가 전달됐을때 오라클 내부적 데이터가 바뀌면 이 처리. 
    	  //                     --@ 주의, update문만이 아님! insert, update, delete 등 다 포함...
    	  
    	  int result = stmt.executeUpdate(sql);
    	  //@ 마우스 위에 올려놓으면 뜨는 노란 네모 부분 슬슬 읽어보기
    	  //@ 영어라서 어렵지만.. 자꾸 보다 보면 읽어야하는 부분 눈에 들어옴
    	  //@ 10개 삭제시 10 반환함
    	  
    	  if (result > 0)
    	  {
    		  System.out.println("데이터 입력 성공~!!!");
    	  }
    	  else
    	  {
    		  System.out.println("입력 실패 ㅠ_ㅠ");
    	  }
    	  
      } catch (Exception e)
      {
         System.out.println(e.toString());
      }
      
      DBConn.close();
      
   }
}

// 실행 결과
/*
데이터 입력 성공~!!!
*/

//@ 한번 더 입력시? 실패 됨.
//@ 2번 지윤이가 이미 있으니까 입력되지 않음













