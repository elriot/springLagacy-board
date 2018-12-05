package com.movie.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import ch.qos.logback.core.net.SyslogOutputStream;

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
		String today = sdf.format(date).toString();
		
		// 7일 후 날짜
		cal.add(Calendar.DATE, 7);
		Date dateAfterAWeek = cal.getTime();
		String afterAWeek = sdf.format(dateAfterAWeek).toString();
		
		//System.out.println("오늘 날짜는: "+ today +", 일주일 뒤에는 : "+ afterAWeek);
		
		// 영화이름으로 상영종료일 가져오기
		MovieVO movieVO = movieDao.getMovieByTitle(mv_title);
				
		// 영화 상영종료 날짜가 현재일+7 보다 빠르면 max값을 영화 상영 종료 날짜로 변경하기
		try {
			String strMovieEndDate = movieVO.getMv_endDate();
			//System.out.println("영화 상영 종료일 : " + strMovieEndDate);
			Date movieEndDate = sdf.parse(strMovieEndDate);
			int compare = dateAfterAWeek.compareTo(movieEndDate);
			// 영화 상영종료 날짜가  일주일 뒤의 날짜보다 빠르면
			if (compare > 0) {
				afterAWeek = strMovieEndDate;
				//System.out.println("일주일 뒤의 날짜보다 상영 종료일이 더 빠릅니다.");
			}

		} catch (ParseException e) {
			e.printStackTrace();

		}

		model.addAttribute("min", today);
		model.addAttribute("max", afterAWeek);		
		model.addAttribute("mv_title", mv_title);
						
		return "book/selectDate";
	}
	
	
	
	// 예약 3) 영화 시간 선택하기
	@RequestMapping(value="selectTime", method=RequestMethod.POST)
	public String selectTime(@RequestParam("mv_title") String mv_title, @RequestParam("bk_wDate") 
	String bk_wDate, Model model, HttpServletResponse response) {
	
		List<MovieVO> list = movieDao.getMovieListByTime(mv_title);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm", Locale.KOREA);

		String systemTime = sdf.format(System.currentTimeMillis());
		String wDate = bk_wDate;
		System.out.println(systemTime+"," +wDate);
		int compare = systemTime.compareTo(wDate);
		if(compare==0) {
			//System.out.println("상영일과 오늘이 같음");
			String now = sdf2.format(System.currentTimeMillis());
			for(MovieVO vo : list) {
				String mvTime = vo.getMv_time();
				int compareTime = now.compareTo(mvTime);

				if(compareTime<0) {
					response.setContentType("text/html; charset=UTF-8");
		            PrintWriter out;
		            
					try {
						out = response.getWriter();
			            out.println("<script>");
			            out.println("alert('금일 해당 영화의 상영이 종료되었습니다.날짜를 다시 선택해 주세요');");
			            out.println("history.back();");
			            out.println("</script>");
			            out.close();
			            return null;
					} catch (IOException e) {
						e.printStackTrace();
					}
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
			System.out.println(vo.getIsBooked());
			if(vo.getIsBooked()==null) {
				vo.setIsBooked("F");
				System.out.println(vo.getIsBooked());

			}
			System.out.println(vo.toString());
		}
			
		model.addAttribute("mv_num", movieVO.getMv_num());
		model.addAttribute("mv_title", mv_title);
		model.addAttribute("list", list);
		model.addAttribute(book);

		return "book/selectSeat";
	}
	
	// 예약6) 결제화면 뷰
	@RequestMapping(value="payment", method=RequestMethod.POST)
	public String payment(@RequestParam("tt_seatNum") String[] str, @RequestParam("mv_title") String mv_title, 
			HttpServletRequest request, Model model, @ModelAttribute BookVO book, @ModelAttribute MovieVO movieVO) {
/*			@RequestParam("mv_num") String mv_num , @RequestParam("tt_num") String tt_num, 
			@RequestParam("bk_wDate") String bk_wDate, @RequestParam("mv_time") String mv_time) {
*/

		List<BookVO>list = new ArrayList<>();		
		for (int i=0; i<str.length; i++) {
			String tt_seatNum = str[i];
			BookVO bookVO = new BookVO();

			bookVO.setMv_num(book.getMv_num());
			bookVO.setTt_num(book.getTt_num());
			bookVO.setBk_wDate(book.getBk_wDate());
			bookVO.setMv_time(book.getMv_time());
			bookVO.setTt_seatNum(tt_seatNum);
			bookVO.setMb_ID("아이디");
			bookVO.setBk_price(10000);
			//mv_num=null, tt_num=1, tt_seatNum=null, bk_date=null, bk_wDate=2018-12-03, mv_time=16:00, bk_price=30000, bk_paid=null
			list.add(bookVO);			
		}
		

		model.addAttribute("mv_title", mv_title);
		model.addAttribute("list", list);
		model.addAttribute("seat_str", str);
		model.addAttribute("listSize", list.size());
		

		return "book/payment";
	}
	
	
	// 예약7) 결제하기
	@RequestMapping(value = "complete", method = RequestMethod.POST)
	public String complete(@RequestParam("tt_seatNum") String[] tt_seatNum, HttpServletRequest request,
			@ModelAttribute BookVO bookVO, Model model, HttpServletResponse response) {
		
		for(int i=0; i<tt_seatNum.length; i++) {
			// 세션값 확인하여 아이디 값 세팅해야함
			bookVO.setMb_ID("아이디");
			bookVO.setTt_seatNum(tt_seatNum[i]);;
			bookVO.setBk_price(10000);
			System.out.println(bookVO.toString());
			bookDao.insertBook(bookVO);
		}	
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out;

		try {
			out = response.getWriter();
			out.println("<script>");
			out.println("alert('예약 완료되었습니다. 마이페이지에서 확인하세요');");
			out.println("location.href='/movie';");
			out.println("</script>");
			out.close();
			return null;
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
		return null;
		

	}

	
	
}
