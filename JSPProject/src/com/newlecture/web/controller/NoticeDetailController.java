package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String sql = "select * from notice_ where ID=?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "NEWLEC", "ssafy");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();

			rs.next();
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regdate = rs.getDate("REGDATE");
			String hit = rs.getString("HIT");
			String files = rs.getString("FILES");
			String content = rs.getString("CONTENT");

			Notice notice = new Notice(
					id, title, writerId, regdate, hit, files, content
					);
			request.setAttribute("n",notice);
			/*
			// 먼저 forward할 데이터 심기
			request.setAttribute("title",title);
			request.setAttribute("writerId",writerId);
			request.setAttribute("regDate",regDate);
			request.setAttribute("hit",hit);
			request.setAttribute("files",files);
			request.setAttribute("content",content);
			*/
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// servlet에서 servlet으로 데이터를 전달할 수 있는 방법
		/*
		 	1. redirect
		 	servlet을 호출했을 때 아예 다른 페이지로 가는 방법
		 	ex) 로그인이 필요한데 로그인이 안되어있을때 로그인 페이지로 보내버림
		 
		 	2. forward
		 	현재 페이지에서 작업했던 내용들을 이어받아서 다음 페이지에서도 사용할 수 있게 하는 방법
		 	지금은 이 방식을 쓸거임!(dispatcher)
		 */
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
		
	}
}
