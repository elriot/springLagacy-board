package com.movie.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.domain.MemberVO;
import com.movie.service.MemberService;

@RestController
@RequestMapping("member")
public class MemberRestController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("list")
	public List<MemberVO> list(){
		
		List<MemberVO> list = memberService.getAll();
		return list;
				
	}
	
	@RequestMapping("chkDupId")
	public Map<String, Integer> checkDuplicatId(@RequestParam String id){
		
		int count = memberService.countById(id);
		Map<String, Integer> map = new HashMap<>();
		map.put("count", count);
		
		return map;
	}
}
