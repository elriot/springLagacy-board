package com.movie.repository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.movie.domain.MemberVO;
import com.movie.domain.MovieVO;

@Repository
public class AdminDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert simpleJdbcInsert;;

	
	public void insertMv(MovieVO movie) {
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("movie");
		movie.setMv_isTrue("T");
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(movie));
	}
	
	// 현재 등록된 영화의 최대 mv_num 찾기, 영화 등록시 해당영화의 파일업로드 경로 설정을 위해 필요
		public int getMaxMvnum() {
			String sql = "select max(mv_num) from movie";
			int max = jdbcTemplate.queryForObject(sql, Integer.class);
			return max;
		}
	
	// 모든 멤버 가져오기 - 지우면 안됨
	public List<MemberVO> getAll(){
		List<MemberVO> list = jdbcTemplate.query
				("SELECT * FROM member ORDER BY mb_num ASC", 
						new BeanPropertyRowMapper<MemberVO>(MemberVO.class));				
		return list;
	}
	
}
