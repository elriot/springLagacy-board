package com.movie.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBodyReturnValueHandler;

import com.movie.domain.BookVO;
import com.movie.domain.TheatherVO;


@Repository
public class BookDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert simpleJdbcInsert;
	
	@PostConstruct
	public void init() {
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("book");

	}
	
	
	// 좌석 예약 여부 가져오기
	public List<TheatherVO> getTheatherSeatList(String tt_num, String bk_wDate, String mv_time, int mv_num) {

		String sql = "select * from theather where tt_num=?" ;
		String bookSql = "select * from book where mv_num=? and bk_wDate=? and mv_time=? and tt_num=?";
		List<TheatherVO> seatList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TheatherVO>(TheatherVO.class), tt_num);
		List<BookVO>bookList = jdbcTemplate.query(bookSql, new BeanPropertyRowMapper<BookVO>(BookVO.class), mv_num, bk_wDate, mv_time, tt_num);

		for(int i=0;i<seatList.size();i++) {
			for(int j=0;j<bookList.size();j++) {
				if(seatList.get(i).getTt_seatNum().equals(bookList.get(j).getTt_seatNum())) {
					System.out.println("seatList"+seatList.get(i).getIsBooked());
					seatList.get(i).setIsBooked("T");
				} else {
					seatList.get(i).setIsBooked("F");
				}
				
				if(seatList.get(i).getIsBooked()==null) {
					seatList.get(i).setIsBooked("F");
				}
				System.out.println(i+1+"번째"+seatList.get(i).getIsBooked());
			}
/*			System.out.println("----------"+i+"번째--------");
			System.out.println(seatList.get(i));*/
		}
		
		return seatList;
	}
	
//	public void insert(Book book) {
//		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(book));
//		 
//	}
	
	public void addBook(String tt_seatNum) {
		String sql = "INSERT ";
		jdbcTemplate.update(sql, "T", tt_seatNum);
	}

}
