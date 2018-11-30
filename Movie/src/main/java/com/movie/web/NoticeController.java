package com.movie.web;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.movie.domain.NoticeVO;
import com.movie.service.NoticeService;

@Controller
@RequestMapping("notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@RequestMapping("insert")
	public String insert() {
		System.out.println("=====insert=====");
		return "notice/insertForm";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(@RequestParam("fileName") MultipartFile multipartFile, 
			@ModelAttribute NoticeVO noticeVO, HttpServletRequest request) throws Exception {
		String filename = null;
		
		if(!multipartFile.isEmpty()) {
			ServletContext application = request.getServletContext();
			String realPath = application.getRealPath("/upload"); // webapp/upload
			
			filename = multipartFile.getOriginalFilename(); // 업로드한 원본 파일명
			
			File file = new File(realPath, filename);
			
			if(file.exists()) {
				filename = System.currentTimeMillis() + "_" + filename;
				file = new File(realPath, filename);
			}
			
			System.out.println("경로 : " + realPath + "\n" + "파일명 : " + filename);
			
			IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		
		noticeVO.setNt_fileName(filename);
		noticeVO.setNt_writeDate(new Timestamp(System.currentTimeMillis()).toString());
		noticeVO.setNt_writeIP(request.getRemoteAddr());
		noticeService.insert(noticeVO); // 글 등록
		return "redirect:/notice/list";
	}
	
	
}