package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.vo.Member;

public class MemberDAO {

	public MemberDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/member", "root", "qwer1234");
	}
	
	public void close(PreparedStatement ps, Connection conn) throws SQLException {
		ps.close();
		conn.close();
	}
	
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		rs.close();
		close(ps, conn);
	}
	
	// 회원가입 - member 스키마의 member 테이블
	public void registerMember(String id, String pwd, String name) throws SQLException {
		Connection conn = connect();
		
		String query = "INSERT INTO member VALUES(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, id);
		ps.setString(2, pwd);
		ps.setString(3, name);
		
		ps.executeUpdate();
		
		close(ps, conn);
		
	}
	
	// 전체 회원 보기
	public ArrayList<Member> showAllMember() throws SQLException {
		Connection conn = connect();
		
		String query = "SELECT * FROM member";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ResultSet rs = ps.executeQuery();
		ArrayList<Member> list = new ArrayList<>();
		
		while(rs.next()) {
			list.add(new Member(rs.getString("id"), 
								rs.getString("password"), 
								rs.getString("name")));
		}
		close(rs, ps, conn);
		return list;
	}
	
	public Member searchMember(String id) throws SQLException {
		Connection conn = connect();
		
		String query = "SELECT * FROM member WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		Member member = null;
		if(rs.next()) member = new Member(id, rs.getString("password"), rs.getString("name"));
		
		close(rs, ps, conn);
		return member;
	}
	
}







