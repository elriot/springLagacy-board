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
	public List<MovieVO> getSelectableMovie() {
		List<MovieVO> list = jdbcTemplate.query("select * from movie where mv_placed='T'",
				new BeanPropertyRowMapper<MovieVO>(MovieVO.class));
		return list;
	}
	
	
}
	