package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program_insert {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String title="TEST2";
		String writerId="newlec";
		String content="hahaha";
		String files="";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "INSERT INTO notice_ (title, writer_id, content, files) VALUES(?,?,?,?)";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "NEWLEC", "ssafy");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		int result = st.executeUpdate();
		
		System.out.println(result);
		
		st.close();
		con.close();
	}

}
