package com.spring.board.persistent;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.board.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	private SqlSession session; //root-context.xml을 가르킴
	private static final String namespace = "com.spring.board"; 
	// namespace = Mapper	

	@Override
	public void insert(BoardVO board) throws Exception {
		session.insert(namespace+".insert", board); 
		// Mapper클래스에 있는 쿼리문을 수행함 		
	}

	@Override
	public void delete(int bNum) throws Exception {
		session.delete(namespace+".delete", bNum);		
	}

	@Override
	public void update(BoardVO board) throws Exception {
		session.update(namespace+".update", board);		
	}

	@Override
	public void updateReadCount(int bNum) throws Exception {
		session.update(namespace+".updateReadCount", bNum);		
	}

	@Override
	public List<BoardVO> list(int page) throws Exception {
		return session.selectList(namespace+".list", (page-1)*5);
	}

	@Override
	public BoardVO select(int bNum) throws Exception {
		return session.selectOne(namespace+".select", bNum);
	}
	
}



















