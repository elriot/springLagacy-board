package com.movie.web;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.movie.domain.MemberVO;
import com.movie.domain.MovieVO;
import com.movie.domain.TheatherVO;
import com.movie.repository.AdminDao;
import com.movie.repository.TheatherDao;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private TheatherDao theatherDao;
	
	@RequestMapping("main")
	public String main() {

		return "admin/main";
	}
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	public String register() {
		return "admin/register";
	}
	
/*	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(@ModelAttribute RegisterVO mvo, HttpServletRequest request) {
		String[] ttNum = request.getParameterValues("tt_num");
			mvo.setTt_num(ttNum[0]);
		adminDao.insertRg(mvo);
		return "redirect:/";
	}*/
	
	// 영화 등록 페이지로 이동하기
	@RequestMapping(value="movie", method=RequestMethod.GET)
	public String movie(Model model) {
		List<TheatherVO> list = theatherDao.getTtNumList();
		model.addAttribute("list", list);
		for(TheatherVO t : list) {
			System.out.println(t.getTt_num()+","+t.getTt_seatNum());
			
		}
		return "admin/movie";
	}
	
	
	// 영화 & 상영정보 등록하기
	@RequestMapping(value="movie", method=RequestMethod.POST)
	public String movie(HttpServletRequest request, 
			@ModelAttribute MovieVO mv, 
			@RequestParam("image") MultipartFile multi
			) throws Exception {
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload");
		String filename = null;
		filename = multi.getOriginalFilename();
		
		String[] tt_nums = request.getParameterValues("tt_num");
		String[] mv_times = request.getParameterValues("mv_time");
		if(tt_nums.length == 1 && mv_times.length == 1) {
			int maxMvNum = adminDao.getMaxMvnum();
			int directory = (((maxMvNum+1)/100)+1)*100;
			File dic = new File(realPath+"/"+directory+"/"+(maxMvNum+1));
			if(!dic.exists()) {
				dic.mkdirs();
			}
			File file = new File(realPath+"/"+directory+"/"+(maxMvNum+1), filename);
			IOUtils.copy(multi.getInputStream(), new FileOutputStream(file));
			mv.setMv_postImage(filename);
			adminDao.insertMv(mv);
		} else {
			for(int i=0; i<tt_nums.length; i++) {
				mv.setTt_num(Integer.parseInt(tt_nums[i]));
				for(int j=0; j<mv_times.length; j++) {
					mv.setMv_time(mv_times[j]);
					int maxMvNum = adminDao.getMaxMvnum();
					int directory = (((maxMvNum+1)/100)+1)*100;
					File dic = new File(realPath+"/"+directory+"/"+(maxMvNum+1));
					if(!dic.exists()) {
						dic.mkdirs();
					}
					File file = new File(realPath+"/"+directory+"/"+(maxMvNum+1), filename);
					IOUtils.copy(multi.getInputStream(), new FileOutputStream(file));
					mv.setMv_postImage(filename);
					adminDao.insertMv(mv);
				}

			}
		}
		return "redirect:/admin/main";
	}
	
	
	@RequestMapping("list") 
	public String list(Model model) {
		List<MemberVO> list = adminDao.getAll();
		model.addAttribute("list", list); // 객체만 들어감. 
		
		return "admin/list";
	}
	
	
	
	

	
	
	
}
