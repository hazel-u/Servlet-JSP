package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet{
	/* 
	 * 밑에 doPost, doGet을 overriding하면 service를 override할 필요가 없다.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// GET과 POST 나눠서 출력하는 첫번째 방법
		if(req.getMethod().equals("GET")) { // 반드시 대문자로  비교
			System.out.println("GET요청이 왔습니다.");
		}
		else if(req.getMethod().equals("POST")) {
			System.out.println("POST요청이 왔습니다.");			
		}
		
		super.service(req, resp); // doGET, doPOST 오버라이딩 필요, 오버라이딩 안하면 405에러남
		
		
	}
	*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGET 메소드가 호출되었습니다.");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPOST 메소드가 호출되었습니다.");
	}
}
