package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 클라이언트로 보내는 것을 UTF-8방식으로 보내 (이걸로 보내도 제대로 나오지는 않을거야)
		// 왜? 보내는 것만 UTF-8로 설정하고, 받는 쪽은 설정을 안해놔서(받는쪽은 EUC-KR로 되어있음)
		response.setCharacterEncoding("UTF-8");
		
		// 받는 쪽한테 이거 ~~~하게 해석해! 라고 알려주는 코드
		response.setContentType("text/html; charset=UTF-8");
		// request.setCharacterEncoding("UTF-8"); -> 이 코드를 필터에서 실행시켜
		
		
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		out.println(title);
		out.println(content);
		
	}
}
