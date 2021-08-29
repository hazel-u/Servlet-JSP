package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program_select {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "select * from notice_ where hit>=10";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "NEWLEC", "ssafy");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String writerId = rs.getString("writer_id");
			Date regDate = rs.getDate("regdate");
			String content = rs.getString("content");
			int hit = rs.getInt("hit");
			
			System.out.printf("id : %d, title : %s, writerId : %s, regDate : %s, content : %s, hit : %d\n", id, title, writerId, regDate, content, hit);
		}
		
		
		rs.close();
		st.close();
		con.close();
	}

}
