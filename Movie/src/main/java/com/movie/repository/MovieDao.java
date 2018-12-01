package com.movie.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.movie.domain.MovieVO;

@Repository
public class MovieDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert simpleJdbcInsert;
	
	@PostConstruct
	public void init() {
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("movie");

	}
	
	// 상영중 영화 모두 가져오기
	public List<MovieVO> getMovieTitle() {
		List<MovieVO> list = jdbcTemplate.query("select * from movie where mv_isTrue='T' group by mv_title",
				new BeanPropertyRowMapper<MovieVO>(MovieVO.class));
		return list;
	}
	

	// 영화 타이틀 별로 가져오기 (영화 제목을 선택하세요)
	public List<MovieVO> getMovieListByTitle(String mv_title) {
		List<MovieVO> list = jdbcTemplate.query("select * from movie where mv_title=?",
				new BeanPropertyRowMapper<MovieVO>(MovieVO.class), mv_title);
		return list;
	}
	

	// 상영 종료일 확인하여 캘린더 max값 조정하기
	public MovieVO getMovieByTitle(String mv_title) {
		MovieVO movieVO = jdbcTemplate.queryForObject("select * from movie where mv_title=? group by mv_title",
				new BeanPropertyRowMapper<MovieVO>(MovieVO.class), mv_title);
		return movieVO;
	}
	
	
	// 선택한 영화 타이틀의 상영시간 가져오기
	public List<MovieVO> getMovieListByTime(String mv_title) {
		List<MovieVO> list = jdbcTemplate.query("select * from movie where mv_title=? group by mv_time",
				new BeanPropertyRowMapper<MovieVO>(MovieVO.class), mv_title);
		return list;
	}
	
	// 선택한 시간의 상영관 정보 가져오기
	public List<MovieVO> getMovieListByTtnum(String mv_title, String mv_time) {
		List<MovieVO> list = jdbcTemplate.query("select * from movie where mv_title=? and mv_time=?",
				new BeanPropertyRowMapper<MovieVO>(MovieVO.class), mv_title, mv_time);
		return list;
	}	
	
	
	// 선택한 모든 정보로 mv_num(기본키) 찾기 
	public MovieVO getMovieMvnum(String mv_title, String mv_time, String tt_num) {
		MovieVO movieVO = jdbcTemplate.queryForObject("select * from movie where mv_title=? and mv_time=? and tt_num=?",
				new BeanPropertyRowMapper<MovieVO>(MovieVO.class), mv_title, mv_time, tt_num);
		return movieVO;
	}	
}
	