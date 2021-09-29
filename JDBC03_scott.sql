SELECT USER
FROM DUAL;
--==>> SCOTT

--○ 실습 테이블 생성
CREATE TABLE TBL_SCORE
( SID   NUMBER
, NAME  VARCHAR2(30)
, KOR   NUMBER(3)
, ENG   NUMBER(3)
, MAT   NUMBER(3)
);
--==>> Table TBL_SCORE이(가) 생성되었습니다.

--○ 제약조건 추가(SID 컬럼에 PK 제약조건 추가)
ALTER TABLE TBL_SCORE
ADD CONSTRAINT SCORE_SID_PK PRIMARY KEY(SID);
--=>> Table TBL_SCORE이(가) 변경되었습니다.

--○ 제약조건 추가(KOR, ENG, MAT 컬럼에 CK 제약조건 추가)
ALTER TABLE TBL_SCORE
ADD ( CONSTRAINT SCORE_KOR_CK CHECK (KOR BETWEEN 0 AND 100)
    , CONSTRAINT SCORE_ENG_CK CHECK (ENG BETWEEN 0 AND 100)
    , CONSTRAINT SOCRE_MAT_CK CHECK (MAT BETWEEN 0 AND 100) );
--==>> Table TBL_SCORE이(가) 변경되었습니다.

--○ 시퀀스 생성
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==>> Sequence SCORESEQ이(가) 생성되었습니다.

--○ 데이터 입력 쿼리문 구성
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '김진령', 80, 75, 60)
;
--==>> 1 행 이(가) 삽입되었습니다.

--○ 인원 수 확인 쿼리문 구성
SELECT COUNT(*) AS COUNT
FROM TBL_SCORE;
--==>> 1
--> 한 줄 구성
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;

--○ 전체 리스트 조회 쿼리문 구성
SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT), TRUNC(((KOR+ENG+MAT)/3),1)
FROM TBL_SCORE
ORDER BY SID;
--==>>
/*
       SID NAME                                  KOR        ENG        MAT (KOR+ENG+MAT) TRUNC(((KOR+ENG+MAT)/3),1)
---------- ------------------------------ ---------- ---------- ---------- ------------- --------------------------
         1 김진령                                 80         75         60           215                       71.6
*/

--> 한 줄 구성
SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT), TRUNC(((KOR+ENG+MAT)/3),1) FROM TBL_SCORE ORDER BY SID
;

SELECT *
FROM TBL_SCORE;

COMMIT;
