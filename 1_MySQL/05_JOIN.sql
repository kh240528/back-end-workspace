/*
	JOIN
    - 두 개 이상의 테이블에서 데이터를 조회하고자 할 때 사용하는 구문
    - 조회 결과는 하나의 결과물(RESULT SET)으로 나옴 
    - 관계형 데이터베이스는 최소한의 데이터로 각각의 테이블에 담고 있음
      (중복을 최소화하기 위해 최대한 쪼개서 관리)
      부서 데이터는 부서 테이블, 사원에 대한 데이터는 사원 테이블, 직급 테이블 등...
      
      만약 어떤 사원이 어떤 부서에 속해있는지 부서명과 같이 조회하고 싶다면?
      만약 어떤 사원이 어떤 직급인지 직급명과 같이 조회하고 싶다면?
      
      => 즉, 관계형 데이터베이스에서 SQL 문을 이용한 테이블 간에 "관계"를 맺어
         원하는 데이터만 조회하는 방법 
*/
/*
	1. 내부 조인(INNER JOIN)
    - 연결시키는 컬럼의 값이 일치하는 행들만 조인되어 조회 (일치하는 값이 없는 행은 조회 X)
    
    1) WHERE 구문 
    SELECT 컬럼, 컬럼, ...
    FROM 테이블1, 테이블2 
    WHERE 테이블1.컬럼명 = 테이블2.컬럼명;
    
    - FROM 절에 조회하고자 하는 테이블들을 콤마(,)로 구분하여 나열 
    - WHERE 절에 매칭시킬 컬럼명에 대한 조건 제시 
    
    2) ANSI(미국국립표준협회 : 산업표준을 제정하는 단체) 표준 구문 -> 다른 DB에서도 사용 가능!
    SELECT 컬럼, 컬럼, ...
    FROM 테이블1 
    [INNER] JOIN 테이블2 ON (테이블1.컬럼명 = 테이블2.컬럼명);
    
    - FROM 절에서 기준이 되는 테이블을 기술
    - JOIN 절에서 같이 조회하고자 하는 테이블을 기술 후 매칭 시킬 컬럼에 대한 조건을 기술 (ON, USING)
    - 연결에 사용하려는 컬럼명이 같은 경우 ON 구문 대신 USING(컬럼명) 구문 사용 
*/
-- 1) 연결할 두 컬럼명이 다른 경우 (employee : dept_code - department : dept_id)
-- 사번(emp_id), 사원명(emp_name), 부서코드(dept_code), 부서명(dept_title) 조회
SELECT emp_id, emp_name, dept_code, dept_title
FROM employee, department
WHERE dept_code = dept_id; 

-- >> ANSI 구문 
SELECT emp_id, emp_name, dept_code, dept_title
FROM employee
JOIN department ON (dept_code = dept_id);

-- 일치하는 값이 없는 행은 조회에서 제외된 것 확인!
-- dept_code가 null인 사원 조회 X 

-- 2) 연결할 두 컬럼명이 같은 경우 (employee : job_code - job : job_code)
-- 사번, 사원명, 직급코드, 직급명(job_name) 조회 
SELECT emp_id, emp_name, job_code, job_name
FROM employee, job
WHERE job_code = job_code; -- ambiguous : 애매한, 모호한 / 에러 발생! 

-- 해결방법 1) 테이블명 이용
SELECT emp_id, emp_name, job.job_code, job_name
FROM employee, job
WHERE employee.job_code = job.job_code;

-- 해결방법 2) 테이블에 별칭 부여해서 이용
SELECT emp_id, emp_name, e.job_code, job_name
FROM employee e, job j
WHERE e.job_code = j.job_code;

-- >> ANSI 구문
SELECT emp_id, emp_name, e.job_code, job_name
FROM employee e
JOIN job j ON (e.job_code = j.job_code);

-- 두 컬럼명이 같을 때만 USING 구문 사용 가능!
SELECT emp_id, emp_name, job_code, job_name
FROM employee 
JOIN job USING (job_code);

-- 자연조인(NATURAL JOIN) : 각 테이블마다 동일한 컬럼이 한 개만 존재할 경우 
-- 주의사항! 쓰지 마세요..!!
SELECT emp_id, emp_name, job_code, job_name
FROM employee
NATURAL JOIN job;

-- 직급이 대리인 사원의 사번(emp_id), 이름(emp_name), 직급명(job_name), 급여(salary) 조회 
-- 1) where 구문
SELECT emp_id, emp_name, job_name, salary
FROM employee e, job j 
WHERE e.job_code = j.job_code
	AND job_name = '대리';
    
-- 2) ANSI 구문
SELECT emp_id, emp_name, job_name, salary
FROM employee
	JOIN job USING(job_code)
WHERE job_name = '대리';

-- 실습문제 ---
-- 1. 부서가 인사관리부인 사원들의 사번, 이름, 보너스 조회 (employee, department)

-- 2. 전체 부서의 부서코드, 부서명, 지역코드, 지역명 조회 (department, location)

-- 3. 보너스를 받는 사원들의 사번, 사원명, 보너스, 부서명 조회 (employee, department)

-- 4. 부서가 총무부가 아닌 사원들의 사원명, 급여 조회 (employee, department)










