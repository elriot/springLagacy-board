package com.spring.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.domain.ReplyVO;
import com.spring.board.persistent.ReplyDAO;

@Controller
public class RController {
	
	@SuppressWarnings("unused")
	@Inject
	private ReplyDAO replyDAO;
	
	@RequestMapping(value="/reply/write", method=RequestMethod.POST)
	public @ResponseBody String replyWrite(@RequestBody ReplyVO reply) throws Exception{//넘어오는 데이터가 VO에 상응하는지 확인해야 함
		//@RequestBody
		System.out.println(reply.getrContent());
		replyDAO.insert(reply);
		int rNum = replyDAO.selectMaxRnum();
		return rNum+""; // 통신을 타면 int타입도 String으로 변환됨
	}
	
	@RequestMapping("reply/delete")
	public String replyDelete(@RequestParam int rNum, @RequestParam int bNum, RedirectAttributes redirect) throws Exception{
		replyDAO.delete(rNum);		
		//리다이렉트 파라미터 넘기는 방법
		redirect.addAttribute("bNum", bNum); // redirect 방식에서 파라미터 넘기기
		return "redirect:/board/detail";
				//"redirect:/board/detail?bNum="+bNum;
	}
	

}
