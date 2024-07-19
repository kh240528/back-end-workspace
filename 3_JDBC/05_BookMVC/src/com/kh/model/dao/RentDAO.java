package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentDAO {
	
	public RentDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "qwer1234");
	}
	
	public void close(PreparedStatement ps, Connection conn) throws SQLException {
		ps.close();
		conn.close();
	}
	
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		rs.close();
		close(ps, conn);
	}
	
	// 대여한 책이 있는지 조회
	public boolean checkRentBook(int no) throws SQLException {
		Connection conn = connect();
		
		String query = "SELECT * FROM rent WHERE rent_book_no = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, no);
		
		ResultSet rs = ps.executeQuery();
		boolean check = rs.next();
		
		close(rs, ps, conn);
		return check;
	}
}







