SELECT USER
FROM DUAL;
--==>> SCOTT

--@ 지금 작업하는 순서 기억해두기! 요구사항 파악... 필요 재료들 준비 중
--@ 카레를 만들거다.. 하면 양파, 당근, 고기 등등 바로 카레 만들수있도록 준비중

TRUNCATE TABLE TBL_SCORE;
--==>> Table TBL_SCORE이(가) 잘렸습니다.

DROP SEQUENCE SCORESEQ;
--==>> Sequence SCORESEQ이(가) 삭제되었습니다.

--○ 시퀀스 생성
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==>> Sequence SCORESEQ이(가) 생성되었습니다.


--○ 쿼리문 준비

-- 1. 데이터 입력 쿼리문 구성
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)
VALUES(SCORESEQ.NEXTVAL, '이다영', 90, 80, 70);
--> 한 줄 구성
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '이다영', 90, 80, 70)
;
--==>> 1 행 이(가) 삽입되었습니다.

COMMIT;
--==>> 커밋 완료.


-- 2. 리스트 출력 쿼리문 구성(총점, 평균, 석차 포함)
--@ RANK OVER 못쓰는 상황에선 서브쿼리문 활용하기!
SELECT SID, NAME, KOR, ENG, MAT
     , (KOR + ENG + MAT) AS TOT
     , (KOR + ENG + MAT)/3 AS AVG
     , RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK
FROM TBL_SCORE
ORDER BY SID ASC;
--> 한 줄 구성
SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT, (KOR + ENG + MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE ORDER BY SID ASC
;
--==>> 1	이다영	90	80	70	240	80	1

-- 3. 인원 수 조회 쿼리문 구성
SELECT COUNT(*) AS COUNT
FROM TBL_SCORE;
--> 한 줄 구성
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;


-- 4. 이름 검색 쿼리문 구성
SELECT *
FROM
(
SELECT SID, NAME, KOR, ENG, MAT
     , (KOR+ENG+MAT) AS TOT
     , (KOR+ENG+MAT)/3 AS AVG
     , RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK
FROM TBL_SCORE
)
WHERE NAME = '이다영';
--> 한 줄 구성
SELECT * FROM (SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE NAME = '이다영'
;
--@ 한줄구문 오류 안나도록 만들고 그때그때 확인하기!
--==>> 1	이다영	90	80	70	240	80	1


-- 5. 번호 검색 쿼리문 구성 
SELECT *
FROM
(
SELECT SID, NAME, KOR, ENG, MAT
     , (KOR+ENG+MAT) AS TOT
     , (KOR+ENG+MAT)/3 AS AVG
     , RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK
FROM TBL_SCORE
)
WHERE SID=1;
--> 한 줄 구성
SELECT * FROM (SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT)/3 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE SID=1
;
--==>> 1	이다영	90	80	70	240	80	1

-- 6. 데이터 수정 쿼리문 구성
UPDATE TBL_SCORE
SET NAME='이지영', KOR=10, ENG=20, MAT=30
WHERE SID=1;
--> 한 줄 구성
UPDATE TBL_SCORE SET NAME='이지영', KOR=10, ENG=20, MAT=30 WHERE SID=1;
--==>> 1 행 이(가) 업데이트되었습니다.

SELECT *
FROM TBL_SCORE;
--==>> 1	이지영	10	20	30

COMMIT;
--==>> 커밋 완료.


-- 7. 데이터 삭제 쿼리문 구성
DELETE
FROM TBL_SCORE
WHERE SID=1;
--> 한 줄 구성
DELETE FROM TBL_SCORE WHERE SID=1
;
--==>> 1 행 이(가) 삭제되었습니다.

ROLLBACK;
--==>> 롤백 완료.
--@ 지운채로 커밋하면 시퀀스가 2부터 시작...


--@○ 영어 점수 잘못 입력된 부분 수정
SELECT *
FROM TBL_SCORE;

UPDATE TBL_SCORE
SET ENG = 80
WHERE SID = 2;

UPDATE TBL_SCORE
SET ENG = 88, MAT = 77
WHERE SID = 3;

COMMIT;
--==>> 커밋 완료.


