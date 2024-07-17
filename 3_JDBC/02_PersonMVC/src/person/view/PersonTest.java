package person.view;

import java.sql.SQLException;

import person.controller.PersonController;

public class PersonTest {
	
	PersonController pc = new PersonController();

	public static void main(String[] args) {
		PersonTest pt = new PersonTest();
	
		try {
			
			// 이 부분은 테스트 용도로만! 
			// person 테이블에 데이터 추가
			//pt.addPerson("박세영", 5, "서울");
			//pt.addPerson("배영운", 6, "경기");
			pt.addPerson("이동호", 7, "서울");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 각 Controller에 메서드들 연결하는 건 각 메서드들에서 구현
	
	// person 테이블에 데이터 추가 - INSERT
	public void addPerson(String name, int age, String addr) throws SQLException {
		int result = pc.addPerson(name, age, addr);
		if(result == 1) {
			System.out.println(name + "님, 회원가입 완료!");
		}
	}
	
	// person 테이블에 데이터 수정 - UPDATE
	public void updatePerson() {
		// ~~님, 정보 수정 완료!
	}

	// person 테이블에 데이터 삭제 - DELETE 
	public void removePerson() {
		// ~~님, 회원탈퇴 완료!
	}
	
	// person 테이블에 있는 데이터 전체 보여주기 - SELECT
	public void searchAllPerson() {}
	
	// person 테이블에서 데이터 한개만 가져오기 - SELECT
	public void searchPerson() {}
	
	
}
