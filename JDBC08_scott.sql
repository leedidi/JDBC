SELECT USER
FROM DUAL;
--==>> SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	김진희	010-1111-1111
2	이찬호	010-2222-2222
3	박혜진	010-3333-3333
4	윤유동	010-4444-4444
5	박혜진	010-5555-5555
6	정효진	010-6666-6666
7	손다정	010-7777-7777
8	박효빈	010-8888-8888
9	최현정	010-9999-9999
10	손범석	010-1010-1010
11	최수지	010-1100-1100
12	채지윤	010-1212-1212
*/

-- 따로 PLSQL 안 만들거.. 블럭 잘 잡아서 실행하기!

--○ CallableStatement 실습을 위한 프로시저 작성
--   프로시저 명   : PRC_MEMBERINSERT
--   프로시저 기능 : TBL_MEMBER 테이블에 데이터를 입력하는 프로시저
/*
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VNAME IN TBL_MEMBER.NAME%TYPE
, VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
    -- 주요 변수 선언
    VSID    TBL_MEMBER.SID%TYPE;
BEGIN

    -- 기존 SID 의 최대값 얻어오기
    SELECT NVL(MAX(SID), 0) INTO VSID
    FROM TBL_MEMBER;
    
    -- 데이터 입력
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES((VISD+1), VNAME, VTEL);
    
    -- 커밋
    COMMIT;
    
END;
*/

CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VNAME IN TBL_MEMBER.NAME%TYPE
, VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
    -- 주요 변수 선언
    VSID TBL_MEMBER.SID%TYPE;
    
BEGIN

    -- 기존 SID의 최대값 얻어오기
    SELECT NVL(MAX(SID),0) INTO VSID
    FROM TBL_MEMBER;
    
    
    -- 데이터 입력
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES((VSID+1), VNAME, VTEL);
    
    -- 커밋
    COMMIT;    
    
END;
--==>> Procedure PRC_MEMBERINSERT이(가) 컴파일되었습니다.


-- ○ 생성된 프로시저 테스트(확인)
EXEC PRC_MEMBERINSERT('서승균', '010-3733-7202');
--==>> PL/SQL 프로시저가 성공적으로 완료되었습니다.

--○ 테이블 조회
SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	김진희	010-1111-1111
2	이찬호	010-2222-2222
3	박혜진	010-3333-3333
4	윤유동	010-4444-4444
5	박혜진	010-5555-5555
6	정효진	010-6666-6666
7	손다정	010-7777-7777
8	박효빈	010-8888-8888
9	최현정	010-9999-9999
10	손범석	010-1010-1010
11	최수지	010-1100-1100
12	채지윤	010-1212-1212
13	서승균	010-3733-7202
*/


--○ Test001 실행 후 확인
SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	김진희	010-1111-1111
2	이찬호	010-2222-2222
3	박혜진	010-3333-3333
4	윤유동	010-4444-4444
5	박혜진	010-5555-5555
6	정효진	010-6666-6666
7	손다정	010-7777-7777
8	박효빈	010-8888-8888
9	최현정	010-9999-9999
10	손범석	010-1010-1010
11	최수지	010-1100-1100
12	채지윤	010-1212-1212
13	서승균	010-3733-7202
14	장민지	010-6857-1996
15	장진하	010-4434-2587
16	정가연	010-4780-9592
*/

--○ CallableStatement 실습을 위한 프로시저 작성
--   프로시저 명   : PRC_MEMBERSELECT
--   프로시저 기능 : TBL_MEMBER 테이블의 데이터를 읽어오는 프로시저
--   ※ 『SYS_REFCURSOR』 자료형을 이용하여 커서 다루기
--@ 이미 커서만들어져 있기 때문에.. 따로 정의할 필요 X. 레퍼런스 커서, 참조 커서라고 부름...

CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT
( VRESULT   OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN VRESULT FOR
        SELECT SID, NAME, TEL
        FROM TBL_MEMBER
        ORDER BY SID;
        
    --CLOSE VRESULT;
    --@ 닫지 않음! 여기서 닫아버리면 받는 쪽에서 못 열음
END;
--==>> Procedure PRC_MEMBERSELECT이(가) 컴파일되었습니다.













