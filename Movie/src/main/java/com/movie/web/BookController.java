package com.movie.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.domain.BookVO;
import com.movie.domain.MovieVO;
import com.movie.repository.BookDao;
import com.movie.repository.MovieDao;

@Controller
@RequestMapping("book")
public class BookController {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private MovieDao movieDao;
	
	@RequestMapping("select")
	public String select(Model model) {
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
		List<MovieVO> list = movieDao.getSelectableMovie();
		
		model.addAttribute("list", list);	
		
		
		
		return "book/select";
	}
	
	/*@RequestMapping("seat")
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
	
	@RequestMapping("seatSelected")
	public String seatSelected(@RequestParam("seatNum") String[] str, HttpServletRequest request, Model model, @RequestParam("bk_wDate")String bk_wDate,  @RequestParam("tt_num")String tt_num,@RequestParam("rg_time")String rg_time) {
	
		for(int i=0; i<str.length; i++) {
			System.out.println(i+":"+str[i]);
		}
		
		
		for (int i=0;i<str.length; i++) {
			String tt_seatNum = str[i];
			//bookDao.updateBookPlaced(tt_seatNum);
		}
		
		
		return "redirect:/book/select";
	}
	
	
	
	
	
}
