package com.movie.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("mb_ID");
		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
		if (id == null) {
		    
/*		    //영화 예매시 오류 발생해서 일단 주석 처리. 
			if(!id.equals("admin")) {
				response.sendRedirect("/movie/");
				return false;
			}*/
				
			System.out.println("세션이 없단다 from preHandle");
			response.sendRedirect("/movie/");
			return false;
		} 

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
/*		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		MemberVO memberVO = (MemberVO) modelMap.get("memberVO");*/
		
		// 아래 코드 주석 해지하면 로그아웃시 오류 발생함
//		int result = (Integer) modelMap.get("result");
//		
//		if(result == 1) {
//			session.setAttribute("mb_ID", memberVO.getMb_ID());
//			System.out.println(memberVO.getMb_ID() + " 로그인 성공");
//		}
	}
}