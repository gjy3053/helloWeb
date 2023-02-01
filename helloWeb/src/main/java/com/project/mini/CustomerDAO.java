package com.project.mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.emp.EmpVO;


public class CustomerDAO {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "dev";
	String pass = "dev";

	Connection conn;
	Statement stmt = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String sql;

//	public void disconn() {
//		try {
//			if (conn != null)
//				conn.close();
//			if (stmt != null)
//				stmt.close();
//			if (psmt != null)
//				psmt.close();
//			if (rs != null)
//				rs.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("에러발생.");
			e.printStackTrace();
		}
	}

	// 입력
	public int addCustomer(CustomerVO customer) {
		connect();
		sql = "insert into customer(customer_id, customer_pw, customer_addr) " + "values(?, ?, ?)"; 
																									
																									
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, customer.getCustomerId());
			psmt.setString(2, customer.getCustomerPw());
			psmt.setString(3, customer.getCustomerAddr());
			r = psmt.executeUpdate(); 

		} catch (SQLException e) {
			//e.printStackTrace();
		}
//		finally {
//			disconn();
//		}
		return r;

	}

	// 단건조회
	public CustomerVO getCustomer(String id) {

		sql = "select * from customer where customer_id = '" + id+"'"; 
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				CustomerVO customer = new CustomerVO();
				customer.setCustomerId(rs.getString("customer_id"));
				customer.setCustomerPw(rs.getString("customer_pw"));

				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			disconn();
//		}
		return null; 
	}
	
	public List<CustomerVO> CustomerVoList() {
		connect(); // 호출 반드시 하기
		sql = "select * from customer";
		List<CustomerVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CustomerVO customer = new CustomerVO();
				customer.setCustomerId(rs.getString("customer_id"));
				customer.setCustomerPw(rs.getString("customer_pw"));
				customer.setCustomerAddr(rs.getString("customer_addr"));
			
				list.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
