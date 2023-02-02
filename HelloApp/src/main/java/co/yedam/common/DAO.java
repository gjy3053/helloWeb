package co.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pass = "hr";

	public Connection conn;
	public Statement stmt = null; // 파라미터 처리하기 불편 그래서 PreparedStatement쓴다
	public PreparedStatement psmt = null;
	public ResultSet rs = null;
	public String sql;

	public void connect() { // 연결
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("에러발생.");
			e.printStackTrace();
		}
	}

	public void disconn() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
