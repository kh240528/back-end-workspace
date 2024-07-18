package com.kh.controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.model.Member;

import config.ServerInfo;

public class MemberController {
	
	Properties p = new Properties();
	
	// 싱글톤 패턴(Singleton Pattern)
	// - 디자인 패턴 중 하나로 클래스가 최대 한 번만 객체 생성되도록 하는 패턴
	
	// 1. 생성자는 private
	private MemberController() {
		
		try {
		
			p.load(new FileInputStream("src/config/jdbc.properties"));
			
			// (1) 드라이버 로딩
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 2. 유일한 객체를 담을 static 변수
	private static MemberController instance;
	
	// 3. 객체를 반환하는 static 메서드
	public static MemberController getInstance() {
		if(instance == null) instance = new MemberController();
		return instance;
	}
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
	}
	
	public void close(PreparedStatement ps, Connection conn) throws SQLException {
		ps.close();
		conn.close();
	}
	
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		rs.close();
		close(ps, conn);
	}
	
	
	
	public boolean signUp(Member m) {

		// 회원가입 기능 구현! 
		// -> 아이디가 기존에 있는지 체크 여부!
		// -> member 테이블에 데이터 추가! 
		try {
			Connection conn = connect();
			PreparedStatement ps = conn.prepareStatement(p.getProperty("signUp"));
			ps.setString(1, m.getId());
			ps.setString(2, m.getPassword());
			ps.setString(3, m.getName());
			
			ps.executeUpdate();
			close(ps, conn);
			return true;
	
		} catch (SQLException e) {
			return false;
		} 

	}
	
	public void login() {

		// 로그인 기능 구현! 
		// -> member 테이블에서 id와 password로 멤버 정보 하나 가져오기!
		
	}
	
	public void changePassword() {

		// 비밀번호 바꾸기 기능 구현!
		// -> login 메서드 활용 후 사용자 이름이 null이 아니면 member 테이블에서 id로 새로운 패스워드로 변경

	}
	
	public void changeName() {

		// 이름 바꾸기 기능 구현!
		// -> member 테이블에서 id로 새로운 이름으로 변경 
		
	}
	
}









