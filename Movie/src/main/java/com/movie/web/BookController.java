package com.movie.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.StyleSheet.ListPainter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.domain.BookVO;
import com.movie.domain.MovieVO;
import com.movie.domain.TheatherVO;
import com.movie.repository.BookDao;
import com.movie.repository.MovieDao;
import com.movie.repository.TheatherDao;

@Controller
@RequestMapping("book")
public class BookController {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private MovieDao movieDao;

	// 예약 1) 영화 제목 선택하기
	@RequestMapping("selectMovie")
	public String selectMovie(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Calendar cal = new GregorianCalendar();
		
		// 오늘날짜
		Date date = cal.getTime();
		String min = sdf.format(date).toString();
		
		// 7일 후 날짜
		cal.add(Calendar.DATE, 7);
		date = cal.getTime();
		String max = sdf.format(date).toString();
		
		System.out.println(min +","+ max);
		model.addAttribute("min", min);
		model.addAttribute("max", max);		
		
		
		// 상영중인 모든 영화 가져오기
		List<MovieVO> list = movieDao.getMovieTitle();		
		model.addAttribute("list", list);	
						
		return "book/selectMovie";
	}
	
	
	// 예약 2) 영화 관람일 선택하기
	@RequestMapping(value="selectDate", method=RequestMethod.POST)
	public String selectDate(@RequestParam("mv_title") String mv_title, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Calendar cal = new GregorianCalendar();
		
		// 오늘날짜
		Date date = cal.getTime();
		String min = sdf.format(date).toString();
		
		// 7일 후 날짜
		cal.add(Calendar.DATE, 7);
		Date dateAfterWeek = cal.getTime();
		String max = sdf.format(dateAfterWeek).toString();
		
		System.out.println(min +","+ max);
		
		// 영화이름으로 상영종료일 가져오기
		MovieVO movieVO = movieDao.getMovieByTitle(mv_title);
		
		
		// 영화 상영종료 날짜가 현재일+7 보다 작으면 max값을 영화 상영 종료 날짜로 변경하기
		try {
			String strMovieEndDate = movieVO.getMv_endDate();
			Date movieEndDate = sdf.parse(strMovieEndDate);
			int compare = dateAfterWeek.compareTo(movieEndDate);
			// 영화 상영종료 날짜가  현재일+7보다 작을 때..
			if (compare > 0) {
				max = strMovieEndDate;
			}

		} catch (ParseException e) {
			e.printStackTrace();

		}

		model.addAttribute("min", min);
		model.addAttribute("max", max);		
		model.addAttribute("mv_title", mv_title);
						
		return "book/selectDate";
	}
	
	
	
	// 예약 3) 영화 시간 선택하기
	@RequestMapping(value="selectTime", method=RequestMethod.POST)
	public String selectTime(@RequestParam("mv_title") String mv_title, @RequestParam("bk_wDate") String bk_wDate, Model model) {
	
		List<MovieVO> list = movieDao.getMovieListByTime(mv_title);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm", Locale.KOREA);

		String systemTime = sdf.format(System.currentTimeMillis());
		String wDate = bk_wDate;
		System.out.println(systemTime+"," +wDate);
		int compare = systemTime.compareTo(wDate);
		if(compare==0) {
			System.out.println("상영일과 오늘이 같음");
			String now = sdf2.format(System.currentTimeMillis());
			for(MovieVO vo : list) {
				String mvTime = vo.getMv_time();
				int compareTime = now.compareTo(mvTime);
				if(compare<0) {
					System.out.println(mvTime+"아직 상영 전");
					vo.setMv_time("started");//상영이 시작되어서 예매 불가능
				}
			}
		}		
		
		
		model.addAttribute("bk_wDate", bk_wDate);		
		model.addAttribute("mv_title", mv_title);
		
		model.addAttribute("list", list);
						
		return "book/selectTime";
	}
	
	// 예약 4) 상영관 선택하기.
	@RequestMapping(value="selectTtnum", method=RequestMethod.POST)
	public String selectTtnum(@ModelAttribute MovieVO movieVO, @RequestParam("bk_wDate") String bk_wDate, HttpServletRequest request,Model model) {
		List<MovieVO> list = movieDao.getMovieListByTtnum(movieVO.getMv_title(), movieVO.getMv_time());
		
		
		model.addAttribute("mv_title", movieVO.getMv_title());
		model.addAttribute("mv_time", movieVO.getMv_time());
		model.addAttribute("bk_wDate", bk_wDate);		
		model.addAttribute("list", list);
						
		return "book/selectTtnum";
	}
	
	
	// 예약5) 좌석 선택하기
	@RequestMapping(value="selectSeat", method=RequestMethod.POST)
	public String selectSeat(@ModelAttribute BookVO book, @RequestParam("mv_title") String mv_title, Model model) {
		// 1) mv_num 가져오기
		MovieVO movieVO = movieDao.getMovieMvnum(mv_title, book.getMv_time(), book.getTt_num());
		
		// 2) 상영관  가져오기 (예약 여부 T/F값으로 입력됨)
		List<TheatherVO>list = bookDao.getTheatherSeatList(book.getTt_num(), book.getBk_wDate(), book.getMv_time(), movieVO.getMv_num());
		
		for(TheatherVO vo : list) {
			if(vo.getIsBooked()==null) {
				System.out.println("없다 예약된적");
				model.addAttribute("booked", "F");
			}
		}
		

		model.addAttribute("list", list);
				
		model.addAttribute(book);
		model.addAttribute("mv_num", movieVO.getMv_num());
/*		model.addAttribute("tt_num", book.getTt_num());
		model.addAttribute("bk_wDate", book.getBk_wDate());
		model.addAttribute("rg_time", book.getMv_time());*/
		return "book/selectSeat";
	}
	
	
	/*@RequestMapping(value="selectSeat", method=RequestMethod.POST)
	public String selectSeat(@RequestParam("seatNum") String[] str, HttpServletRequest request, Model model, @RequestParam("bk_wDate")String bk_wDate,  @RequestParam("tt_num")String tt_num,@RequestParam("rg_time")String rg_time) {
	
		for(int i=0; i<str.length; i++) {
			System.out.println(i+":"+str[i]);
		}
		
		
		for (int i=0;i<str.length; i++) {
			String tt_seatNum = str[i];
			/bookDao.updateBookPlaced(tt_seatNum);
		}
		
		return "book/selectSeat";
	}
	*/
/*	@RequestMapping("seat")
	public String seat(@ModelAttribute BookVO book, Model model) {
		List<BookVO>list = bookDao.getSeatList();	

		model.addAttribute("list", list);
		for(BookVO b : list) {
			System.out.println();
		}
				
		model.addAttribute(book);
		model.addAttribute("tt_num", book.getTt_num());
		model.addAttribute("bk_wDate", book.getBk_wDate());
		model.addAttribute("rg_time", book.getRg_time());
		return "book/seat";
	}*/
	
	
	
}
