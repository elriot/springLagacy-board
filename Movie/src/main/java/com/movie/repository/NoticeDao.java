package com.movie.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.movie.domain.NoticeVO;

@Repository
public class NoticeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcInsert simpleJdbcInsert;
	
	@PostConstruct
	public void init() {
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("notice");
	}
	
	public int count() {
		int count = jdbcTemplate.queryForObject("SELECT count(*) FROM notice", Integer.class);
		return count;
	} // 글 갯수 확인
	
	public void insert(NoticeVO noticeVO) {
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(noticeVO));
	}
	
	public NoticeVO getNotice(int num) {
		NoticeVO noticeVO = jdbcTemplate.queryForObject("SELECT * FROM notice WHERE num = ?", 
				new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class), num);
		return noticeVO;
	}
	
	public List<NoticeVO> getNoticeList(int startRow) {
		List<NoticeVO> list = jdbcTemplate.query("SELECT * FROM notice offset ? limit 10", 
				new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class), startRow);
		return list;
	}
	
	public int getNoticeMaxNum() {
		int maxNum = jdbcTemplate.queryForObject("SELECT max(nt_num) FROM notice", Integer.class);
		return maxNum;
	}
	
	public void updateNoticeReadcount(int num) {
		jdbcTemplate.update("UPDATE notice SET nt_readCount = readCount + 1 WHERE nt_num = ?",  num);
	}
}