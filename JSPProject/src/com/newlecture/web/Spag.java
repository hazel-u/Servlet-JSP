package com.newlecture.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=0;
		String num_ = request.getParameter("n");
		if(num_ != null && !num_.equals("")){
			num = Integer.parseInt(num_);
		}
		
		String result;
		if(num%2 != 0) result = "홀수";
		else result = "짝수";
		
		request.setAttribute("result", result);
		
		// redirect : 현재 작업하던 내용과 별개로 새로운 요청
		// forward : 현재 작업하던 내용을 이어갈 수 있음
		
		// 지금 할 일은 spag.jsp로 result를 보낼 거야
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		// 현재 작업했던 내용이 request, response에 담겨있다면 그걸 forward해
		// 이렇게 하면 지금 사용하고 있는 request, response가 spag.jsp의 request와 response가 된다.
		// 그래서 spag.jsp에서도 같은 내용을 사용할 수 있게 된다.
		// result를 forward한 곳에 사용하기 위해 request에 result가 담겨간다.
		dispatcher.forward(request, response);
	}
}
