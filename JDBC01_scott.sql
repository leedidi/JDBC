SELECT USER
FROM DUAL;
--==>> SCOTT

DROP TABLE TBL_MEMBER;
--==>> Table TBL_MEMBER이(가) 삭제되었습니다.

--○ 실습 테이블 생성
CREATE TABLE TBL_MEMBER
( SID   NUMBER
, NAME  VARCHAR2(30)
, TEL   VARCHAR2(60)
, CONSTRAINT MEMBER_SID_PK PRIMARY KEY(SID)
);
--==>> Table TBL_MEMBER이(가) 생성되었습니다.

--@ 오라클에서 DML 작업 수행할때는 꼭 COMMIT 하기!!!
--@ 오라클, 자바 왔다갔다 하면서 진행할텐데 커밋해놔야 자바쪽에서 갱신됨

--○ 샘플 데이터 입력
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(1, '이다영', '010-4113-2353');
--==>> 1 행 이(가) 삽입되었습니다.

--○ 확인
SELECT *
FROM TBL_MEMBER;
--==>> 1	이다영	010-4113-2353

--○ 커밋
COMMIT;
--==>> 커밋 완료.


--○ 자바에서 Test003 클래스 실행 후 다시 확인
SELECT *
FROM TBL_MEMBER;
--==>>
/*
1	이다영	010-4113-2353
2	채지윤	010-2222-2222
*/


--○ 자바에서 Test004 클래스 실행 후 다시 확인
SELECT *
FROM TBL_MEMBER;
--==>>
/*
1	이다영	010-4113-2353
2	채지윤	010-2222-2222
3	김소연	010-3333-3333
4	윤유동	010-4444-4444
5	손다정	010-5555-5555
*/


--○ 데이터 조회 쿼리문 준비
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--> 한 줄 구성
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;












