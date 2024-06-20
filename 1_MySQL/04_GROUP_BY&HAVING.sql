/*
	GROUP BY 
    - 그룹 기준을 제시할 수 있는 구문 
    - 여러 개의 값들을 하나의 그룹으로 묶어서 처리할 목적으로 사용 
*/
-- 각 부서별 조회 
SELECT 
	dept_code, 
    count(*) "사원 수",
    format(sum(salary), 0) "총 급여",
    format(avg(salary), 0) "평균 급여",
    min(salary) "최저 급여",
    max(salary) "최고 급여"
FROM employee
GROUP BY dept_code;

-- 직급 코드(job_code)별 사원 수 조회 
SELECT job_code, count(*) 
FROM employee
GROUP BY job_code;

-- 성별(남자/여자) 별 사원 수 조회 
SELECT if(substr(emp_no, 8, 1) = 1, '남자', '여자') 성별, count(*)
FROM employee
GROUP BY 성별;

/*
	HAVING
    - 그룹에 대한 조건을 제시할 때 사용하는 구문 
    
    5 SELECT    * | 조회하고자 하는 컬럼명 as 별칭 | 함수
    1 FROM      조회하고자 하는 테이블명
    2 WHERE     조건식 (연산자들 가지고 기술)
    3 GROUP BY  그룹 기준에 해당하는 컬럼명 | 함수 
    4 HAVING    조건식 (그룹 함수를 가지고 기술)
    6 ORDER BY  컬럼명 | 컬럼순번 | 별칭 [ASC | DESC];
*/
-- 부서별 평균 급여가 300만원 이상인 부서의 평균 급여 조회 
SELECT dept_code, format(avg(salary), 0) 평균급여
FROM employee
GROUP BY dept_code
HAVING avg(salary) >= 3000000;

-- 직급별 총 급여의 합이 1000만원 이상인 직급만 조회
SELECT job_code, sum(salary)
FROM employee
GROUP BY job_code
HAVING sum(salary) >= 10000000;

-- 부서별 보너스를 받는 사원이 없는 부서만 조회 
SELECT dept_code, count(bonus)
FROM employee
GROUP BY dept_code
HAVING count(bonus) = 0;

-- 부서별 보너스를 받는 사원들 수 조회 
SELECT dept_code, count(bonus)
FROM employee
GROUP BY dept_code
HAVING count(bonus) != 0;

SELECT dept_code, count(*) -- 보너스가 null이 아닌 경우 
FROM employee
WHERE bonus is not null
GROUP BY dept_code;

/*
	rollup|cube(컬럼1,컬럼2) (cube는 MySQL X) - 실제 볼일은 없는 집계 함수 
    - 그룹별 산출한 결과 값의 중간 집계를 계산 해주는 함수 
    - rollup : 컬럼1을 가지고 다시 중간집계를 내는 함수 
    - cube : 컬럼1을 가지고 중간집계도 내고, 컬럼2를 가지고도 중간집계를 냄 
    
    MySQL에서의 rollup 
    컬럼1, 컬럼2 with rollup
    
    grouping : rollup이나 cube에 의해 산출된 값이 해당 컬럼의 집합의 산출물이면 0, 아니면 1 
    -> 집계된 값인지, 아닌지 정도만 구분 
    
    SQLD 자격증 시험에서 꼭 이상하게 나오기도 하지만... 실제 쓰이는 일은 없음!!
    저희 문제에서도 안 나옴! 몰라도 됩니다..
*/
-- 부서별, 직급별 급여 합 조회 
SELECT dept_code, job_code, sum(salary)
FROM employee
GROUP BY dept_code, job_code with rollup;

/*
	집합 연산자 
    - 여러 개의 쿼리문을 하나의 쿼리문으로 만드는 연산자 
    - 여러 개의 쿼리문에서 조회하려고 하는 컬럼의 개수와 이름이 같아야 사용할 수 있다. 
    
    주의! ORDER BY 절은 쓰시려면 마지막에만 기술 가능 
    
    UNION (합집합) : 두 쿼리문을 수행한 결과값을 하나로 합쳐서 추출 (중복되는 행 제거)
    UNION ALL (합집합) : 두 쿼리문을 수행한 결과값을 하나로 합쳐서 추출 (중복되는 행 제거 X)
    INTERSECT (교집합) : 두 쿼리문을 수행한 결과값에 중복된 결과값만 추출 (MySQL X)
    MINUS (차집합) : 선행 쿼리문의 결과값에서 후행 쿼리문의 결과값을 뺀 나머지 결과값만 추출 (MySQL X)
		--> INTERSECT, MINUS : WHERE절에서 AND 연산자를 사용해서 처리 가능!
*/
-- 1. UNION
-- (1) 부서 코드가 D5인 사원들의 사번, 사원명, 부서코드, 급여 조회 
SELECT emp_id, emp_name, dept_code, salary
FROM employee
WHERE dept_code = 'D5'
UNION
-- (2) 급여가 300만원 초과인 사원들의 사번, 사원명, 부서코드, 급여 조회 
SELECT emp_id, emp_name, dept_code, salary
FROM employee
WHERE salary > 3000000
ORDER BY emp_id;

-- 부서 코드가 D5이거나 급여가 300만원 초과인 사원들의 사번, 사원명, 부서코드, 급여 조회 
-- 위 쿼리문 대신 WHERE 절에 OR 연산자를 사용해서 처리 가능 
SELECT emp_id, emp_name, dept_code, salary
FROM employee
WHERE dept_code = 'D5' OR salary > 3000000;

-- 2. UNION ALL 
SELECT emp_id, emp_name, dept_code, salary
FROM employee
WHERE dept_code = 'D5'
UNION ALL 
SELECT emp_id, emp_name, dept_code, salary
FROM employee
WHERE salary > 3000000;



