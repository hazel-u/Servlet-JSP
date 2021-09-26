package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨트롤러 : 사용자가 요청한 내용을 받는 역할
		// 사용자가 검색을 할 때 들어오는 내용 : list?f=title&q=a (f는 field, q는 검색 내용)
		
		// 옵션으로 오는 값을 사용할 때는 null이 올수도 있기 때문에 사전에 미리 체크를 해주어야한다.
		// 방법 : 임시변수에 값을 담아두고 null 체크 후 정해진 변수에 담는다.
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String field = "title"; // 기본값 설정
		String query="";
		if(field_ != null) field = field_; // null이 아닐 경우에만 field에 넣기
		if(query_ != null) query = query_;
		
		
		NoticeService service = new NoticeService();
		
		List<Notice> list = service.getNoticeList(field, query, 1);
		
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response); // view 단으로 데이터 전송
	}
}
