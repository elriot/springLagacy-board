package com.movie.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.movie.domain.MemberVO;

@Repository
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert simpleJdbcInsert;

	@PostConstruct
	public void init() {
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("member"); // 필수
		// 기본키 이면서 AUTO_INCREMENT 컬럼이름 설정
		// simpleJdbcInsert.usingGeneratedKeyColumns("");
	}

	public void add(MemberVO memberVO) {
		// NamedParameterJdbcTemplate 방식
		/*
		 * SqlParameterSource param = new BeanPropertySqlParameterSource(member); String
		 * sql = "INSERT INTO member (id,passwd,name,reg_date,age,gender,email) " +
		 * "VALUES (:id, :passwd, :name, :reg_date, :age, :gender, :email)";
		 * namedParameterJdbcTemplate.update(sql, param);
		 */

		// SimpleJdbcInsert 방식
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(memberVO));
	} // add()

	public int update(MemberVO member) {
		int rowCount = namedParameterJdbcTemplate.update(
				"UPDATE member SET name=:name, age=:age, gender=:gender, email=:email WHERE id=:id",
				new BeanPropertySqlParameterSource(member));
		return rowCount;
	}

	public void delete(String id) {
		// JdbcTemplate 방식
		// jdbcTemplate.update("DELETE FROM member WHERE id=?", id);

		// NamedParameterJdbcTemplate 방식
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		namedParameterJdbcTemplate.update("DELETE FROM member WHERE id=:id", param);
	} // delete()

	public int deleteAll() {
		return jdbcTemplate.update("DELETE FROM member");
	}

	public MemberVO get(String id) {
		// queryForObject() 메소드는 행 1개만 가져오는 select문 실행시 사용함.
		// 행이 0개 또는 2개 이상 가져오면 예외가 발생함.
		try {
			// JdbcTemplate 방식
			/*
			 * Member m = jdbcTemplate.queryForObject("SELECT * FROM member WHERE id=?", new
			 * BeanPropertyRowMapper<Member>(Member.class), id); return m;
			 */

			// NamedParameterJdbcTemplate 방식
			MemberVO m = namedParameterJdbcTemplate.queryForObject("SELECT * FROM member WHERE id=:id",
					new MapSqlParameterSource("id", id), new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
			return m;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	} // get()

	public List<MemberVO> getAll() {
		// 행이 0개 또는 0개 이상일때 query() 메소드 호출
		// 항상 List로 반환해줌
		List<MemberVO> list = namedParameterJdbcTemplate.query("SELECT * FROM member ORDER BY name ASC",
				new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
		return list;
	}

	public List<MemberVO> search(String name) {
		List<MemberVO> list = jdbcTemplate.query("SELECT * FROM member WHERE name LIKE ?", new BeanPropertyRowMapper<MemberVO>(MemberVO.class),
				"%" + name + "%");
		return list;
	}
	
	public int count() {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM member", Integer.class);
		return count;
	}
	
	public int countById(String id) {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM member WHERE id = ?", Integer.class, id);
		return count;
	}

}
