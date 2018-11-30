package com.movie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TheatherDao {
		@Autowired
		static JdbcTemplate jdbcTemplate;
		@Autowired
		static NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
		static SimpleJdbcInsert simpleJdbcInsert;
		
		
	
	
}