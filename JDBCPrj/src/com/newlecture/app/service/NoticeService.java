package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.app.entity.Notice;

public class NoticeService {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "NEWLEC";
	private String pwd = "ssafy";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	public List<Notice> getList() throws ClassNotFoundException, SQLException{
		String sql = "select * from notice_";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		List<Notice> list = new ArrayList<>();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String writerId = rs.getString("writer_id");
			Date regDate = rs.getDate("regdate");
			String content = rs.getString("content");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
			
			Notice notice = new Notice(
						id, 
						title,
						writerId,
						regDate,
						content,
						hit,
						files
					);
			
			list.add(notice);
		}
		
		
		rs.close();
		st.close();
		con.close();
		
		return list;
		
	}

	public int insert (Notice notice) throws SQLException, ClassNotFoundException {
		String title=notice.getTitle();
		String writerId=notice.getWriterId();
		String content=notice.getContent();
		String files=notice.getFiles();
		
		String sql = "INSERT INTO notice_ (title, writer_id, content, files) VALUES(?,?,?,?)";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		st.close();
		con.close();
		
		return result;
	}
	
	public int update (Notice notice) throws ClassNotFoundException, SQLException {
		String title=notice.getTitle();
		String content=notice.getContent();
		String files=notice.getFiles();
		int id = notice.getId();
		
		String sql = "update notice_ set title = ?, content = ?, files=? where id=?";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		int result = st.executeUpdate();
		
		
		st.close();
		con.close();
		return result;
	}
	
	public int delete (int id) throws ClassNotFoundException, SQLException {
		String sql = "delete notice_ where id=?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result;
	}
}
