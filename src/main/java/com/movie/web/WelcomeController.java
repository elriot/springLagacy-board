package com.movie.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String welcome() {
		return "index/index";
	}
	

}
