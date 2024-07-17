package person.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonController {
	
	// 리턴 타입이나 매개변수(파라미터) 자유롭게 변경 가능!
	// 메서드 추가 가능! <-- 추천!!
	/*
	 * 1. 드라이버 로딩
	 * 2. DB 연결
	 * 3. PreparedStatement - 쿼리 
	 * 4. 쿼리 실행
	 * */
	
	public PersonController() {
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 고정적인 반복 -- DB 연결, 자원 반납 -> 공통적인 메서드 정의.. 메서드마다 호출해서 사용!
	
	// 2. DB 연결
	public Connection getConnect() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "qwer1234");
	}
	
	// 5. 자원 반납
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		ps.close();
		conn.close();
	}
	
	// 변동적인 반복 -- 비즈니스 로직 DAO(Database Access Object)
	
	// person 테이블에 데이터 추가 - INSERT
	public int addPerson(String name, int age, String addr) throws SQLException {
		
		Connection conn = getConnect();
		
		// 3. PreparedStatement - 쿼리 
		String query = "INSERT INTO person(name, age, addr) VALUES(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, age);
		ps.setString(3, addr);
		
		// 4. 쿼리 실행
		int result = ps.executeUpdate();
		
		closeAll(ps, conn);
		
		return result;
	}
	
	// person 테이블에 데이터 수정 - UPDATE
	public void updatePerson() {
		
	}

	// person 테이블에 데이터 삭제 - DELETE 
	public void removePerson() {}
	
	// person 테이블에 있는 데이터 전체 보여주기 - SELECT
	public void searchAllPerson() {}
	
	// person 테이블에서 데이터 한개만 가져오기 - SELECT
	public void searchPerson() {}
}
