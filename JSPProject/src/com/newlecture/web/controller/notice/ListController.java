package com.newlecture.web.controller.notice;

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
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class ListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨트롤러 : 사용자가 요청한 내용을 받는 역할
		// 사용자가 검색을 할 때 들어오는 내용 : list?p=1&f=title&q=a (p는 page, f는 field, q는 검색 내용)
		
		// 옵션으로 오는 값을 사용할 때는 null이 올수도 있기 때문에 사전에 미리 체크를 해주어야한다.
		// 방법 : 임시변수에 값을 담아두고 null 체크 후 정해진 변수에 담는다.
		String page_ = request.getParameter("p"); // int형일 경우 null 체크를 못하기 때문에 String으로 받고 나중에 형변환을 해준다. (Integer로 할경우에는 나중에 형변환을 못하기 때문에 불가능)
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		
		int page=1;
		String field = "title"; // 기본값 설정
		String query="";
		if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
		if(field_ != null && !field_.equals("")) field = field_; // null이 아닐 경우에만 field에 넣기
		if(query_ != null && !query_.equals("")) query = query_;
		
		
		NoticeService service = new NoticeService();
		
		List<NoticeView> list = service.getNoticeList(field, query, page);
		int count = service.getNoticeCount(field, query);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);		
		
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response); // view 단으로 데이터 전송
	}
}
