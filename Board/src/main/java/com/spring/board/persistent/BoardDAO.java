package com.spring.board.persistent;

import java.util.List;

import com.spring.board.domain.BoardVO;

public interface BoardDAO {
	// 매퍼를 실행할 수 있는 함수 생성
	// public abstract 생략되어 있음
	void insert(BoardVO board) throws Exception; 
	// boardVO의 파라미터가 매퍼로 들어가서 변수를 찾을때 마이바티스 함수명과 VO 변수명이 일치해야함
	// 파라미터 두개 이상인 경우에는 VO클래스.. 필요없는 파라미터는 Null값으로 처리됨
	void delete(int bNum) throws Exception;
	void update(BoardVO board) throws Exception;
	void updateReadCount(int bNum) throws Exception;
	// select는 리턴 받음
	List<BoardVO> list(int page) throws Exception;
	BoardVO select(int bNum) throws Exception;
}
