package com.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.domain.NoticeVO;
import com.movie.repository.NoticeDao;

@Service
public class NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	
	public void insert(NoticeVO noticeVO) {
		System.out.println("noticeVO.add");
		noticeDao.insert(noticeVO);
		System.out.println("noticeVO.getNt_num() : " + noticeVO.getNt_num());
	} // 공지사항 쓰기
	
	public int getNoticeCount() {
		return noticeDao.count();
	}
	
	/*public List<NoticeVO> getNoticeList(int startRow) {
		
	}*/
}