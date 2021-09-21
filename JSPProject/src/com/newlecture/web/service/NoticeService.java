package com.newlecture.web.service;

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
	
	
		return null;
	}	
	
	public int getNoticeCount() { // 글의 갯수를 불러오는 서비스 메서드
		
		return getNoticeCount("title","");
	}
	
	public int getNoticeCount(String field, String query) { // 사용자가 검색한 내용을 포함한 글의 개수를 불러오는 서비스 메서드
		
		return 0;
	}
	
	public Notice getNotice(int id) { // id에 해당하는 글 하나를 불러오는 서비스 메서드
		
		return null;
	}
	
	public Notice getNextNotice(int id) { // 현재 id의 다음 글을 불러오는 서비스 메서드
		
		return null;
	}

	public Notice getPrevNotice(int id) { // 현재 id의 이전 글을 불러오는 서비스 메서드
		
		return null;
	}
}
