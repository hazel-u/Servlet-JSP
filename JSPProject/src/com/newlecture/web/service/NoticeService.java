package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

public class NoticeService {
	// getNoticeList메서드를 부를 때 인자의 개수에 따라 불려지는 메서드가 많은데 하는 일이 대부분 비슷함.(getNoticeCount도 마찬가지)
	// 이런 기능들을 메서드마다 다 구현하게 되면 코드의 중복이 생기기 때문에
	// 하나의 메서드만 구현하고 나머지 메서드는 구현된 메서드를 이용하는 형태로 만든다.
	public List<Notice> getNoticeList(){ // 전체 글을 불러오는 서비스 메서드
		
		return getNoticeList("title","",1);
	}
	public List<Notice> getNoticeList(int page){ // 해당 페이지의 글을 불러오는 서비스 메서드
		
		
		return getNoticeList("title","",page);
	}
	public List<Notice> getNoticeList(String field, String query, int page){ // 사용자가 검색한 내용을 포함한 글을 불러오는 서비스 메서드
		
		List<Notice> list = new ArrayList<>();
		
		// field는 왜 ?로 안넣나
		// -> ?로 넣게 되면 값이 'title'이런 형태로 들어가게 되는데 여기서 우리가 원하는 형태는 ''가 빠진 title 이다.
		// 그래서 어쩔 수 없이 그냥 sql에 넣었다.
		String sql = "select * from( " + 
				"select rownum num, N.* " + 
				"from (select * from notice_ where "+field+" like ? order by regdate desc) N) " + 
				"where num between ? and ?";
		
		// page 시작 id : 1, 11, 21, 31 ... -> 등차수열 an = 1+(page-1)*10
		// page 끝 id : 10, 20, 30, 40 ... -> page*10
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "ssafy");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			ResultSet rs = st.executeQuery();

			while(rs.next()){
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getTimestamp("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				Notice notice = new Notice(id, title, writerId, regdate, hit, files, content);
				list.add(notice);
			}

			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	}	
	
	public int getNoticeCount() { // 글의 갯수를 불러오는 서비스 메서드
		
		return getNoticeCount("title","");
	}
	
	public int getNoticeCount(String field, String query) { // 사용자가 검색한 내용을 포함한 글의 개수를 불러오는 서비스 메서드
		
		int count = 0;
		
		String sql = "select COUNT(ID) count from( " + 
				"select rownum num, N.* " + 
				"from (select * from notice_ where "+field+" like ? order by regdate desc) N " + 
				") ";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "ssafy");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");

			ResultSet rs = st.executeQuery();

			if(rs.next()) count = rs.getInt("count");

			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public Notice getNotice(int id) { // id에 해당하는 글 하나를 불러오는 서비스 메서드
		Notice notice = null;
		
		String sql = "select * from notice_ where id=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "ssafy");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getTimestamp("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				notice = new Notice(nid, title, writerId, regdate, hit, files, content);
			}

			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public Notice getNextNotice(int id) { // 현재 id의 다음 글을 불러오는 서비스 메서드
		
		Notice notice = null;
		
		// 인자로 받은 id의 다음 게시글 id가 무엇인지 알아야한다.
		String sql = "select * " + 
				"from notice_ " + 
				"where id=( " + 
				"    select id " + 
				"    from notice_ " + 
				"    where regdate > (select regdate from notice_ where id=3) and rownum=?)";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "ssafy");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getTimestamp("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				notice = new Notice(nid, title, writerId, regdate, hit, files, content);
			}

			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}

	public Notice getPrevNotice(int id) { // 현재 id의 이전 글을 불러오는 서비스 메서드
		
		Notice notice = null;
		
		String sql = "select id " + 
				"from (select * from notice_ order by regdate desc) " + 
				"where regdate < (select regdate from notice_ where id=3) and rownum=?";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "ssafy");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();

			if(rs.next()){
				int nid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String writerId = rs.getString("WRITER_ID");
				Date regdate = rs.getTimestamp("REGDATE");
				String hit = rs.getString("HIT");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				notice = new Notice(nid, title, writerId, regdate, hit, files, content);
			}

			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
}
