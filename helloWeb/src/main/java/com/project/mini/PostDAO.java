package com.project.mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "dev";
	String pass = "dev";

	Connection conn;
	Statement stmt = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String sql;

	// db연결
	public void connect() {
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
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			if (psmt != null)
				psmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 전체조회
	public List<PostVO> postVoList() {
		connect();
		sql = "select * from post";
		List<PostVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PostVO post = new PostVO();
				post.setPostNum(rs.getInt("post_num"));
				post.setPostId(rs.getString("post_id"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostDate(rs.getString("post_date"));
				post.setPostGood(rs.getInt("post_good"));

				list.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	//인기글 조회
	public List<PostVO> postVoGoodList() {
		connect();
		sql = "select * from post order by post_good desc";
		List<PostVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PostVO post = new PostVO();
				post.setPostNum(rs.getInt("post_num"));
				post.setPostId(rs.getString("post_id"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostDate(rs.getString("post_date"));
				post.setPostGood(rs.getInt("post_good"));

				list.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	// 단건조회 
	public PostVO getPost(int postNum) {
		sql = "select * from post where post_num = '" + postNum + "'"; // 기본키조회니까 많아봐야 한건
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				PostVO post = new PostVO();
				post.setPostTitle(rs.getString("post_title"));
				post.setPostContents(rs.getString("post_Contents"));
				post.setPostId(rs.getString("post_id"));
				post.setPostGood(rs.getInt("post_good"));

				return post;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return null; 
	}
	


	// 게시글 작성
	public int addPost(PostVO post) {
		connect();
		sql = "insert into post(post_num, post_id, post_title, post_contents, post_good)"
				+ "values(post_seq.NEXTVAL, ?, ?, ?, ?)";

		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, post.getPostId());
			psmt.setString(2, post.getPostTitle());
			psmt.setString(3, post.getPostContents());
			psmt.setInt(4, post.getPostGood());
			r = psmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconn();
		}
		return r;
	}

	// 수정
	public int updatePost(String title, String contents, int num) {
		connect();
		sql = "update post set post_title = ?, post_contents = ? where post_num = " + num;
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, contents);
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
		
	}
	
	//삭제
	public int deletePost(int num) {
		connect();
		sql = "delete from post where post_num = " + num;
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	//추천수 변경
	public int goodPost(int num, PostVO post) {
		connect();
		sql = "update post set post_good = ? where post_num = " + num;
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, post.getPostGood()+1);
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
		
	}
}
