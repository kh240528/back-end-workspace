package com.kh.inheritance;

import com.kh.inheritance.parent.Product;

/*
 * 상속
 * 
 * class 자식클래스 extends 부모클래스 {
 * 
 * }
 * 
 * * 상속의 장점
 *   - 적은 양의 코드로 새로운 클래스 작성 가능
 *   - 코드를 공통적으로 관리하기 때문에 코드의 추가나 변경에 용이
 *   - 코드의 중복을 제거하여 프로그램의 생산성과 유지 보수에 크게 기여
 *   
 * * 상속의 특징
 *   - 클래스 간의 상속은 단일 상속만 가능
 *   - 명시되지 않아도 모든 클래스는 Object 클래스 상속
 *     -> Object 클래스의 메서드들을 오버라이딩 하여 메서드 재정의 가능
 *   - 부모 클래스의 생성자, 초기화 블록은 상속되지 않는다.
 *     -> 자식 클래스 생성 시에 부모 클래스 생성자가 먼저 실행
 *   - 부모의 private 변수는 상속은 되지만 직접 접근은 불가능
 *     -> Getter / Setter 사용해서 간접 접근 가능  
 * */

public class Notebook extends Product {

}





