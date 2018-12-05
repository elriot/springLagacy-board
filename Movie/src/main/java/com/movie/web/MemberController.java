package com.movie.web;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.domain.MemberVO;
import com.movie.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	// http://localhost:8999/moive/member/add
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add() {
		System.out.println("======add======");
		
		return "member/joinForm";
		
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute MemberVO memberVO) {
		// Member 오브젝트 생성 후 파라미터 채워서 전달해줌
		// 회원가입날짜
		System.out.println("ddddddddddd");
		memberVO.setMb_joinDate(new Timestamp(System.currentTimeMillis()).toString());
		
		
		memberService.add(memberVO);
		System.out.println("memberVO : "+ memberVO.getMb_ID());

		return "redirect:/member/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "member/loginForm";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("mb_ID") String mb_ID, @RequestParam String mb_passwd, 
			HttpServletResponse response, HttpSession session) throws Exception {
		int check = memberService.userCheck(mb_ID, mb_passwd);

		if (check != MemberService.ID_AND_PASSWD_OK) {
			// 아이디, 패스워드 모두 일치하지 않으면
			String message = "";
			switch (check) {
			case MemberService.ONLY_PASSWD_FAIL:
				message = "패스워드 틀림";
				break;
			case MemberService.ID_AND_PASSWD_FAIL:
				message = "아이디 없음";
				break;
			}

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();

			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("history.back();");
			out.println("</script>");
			out.close(); // out.flush() 먼저 수행하고 자원닫기
			return null;
		}

//		session.setAttribute("id", id);

		return "redirect:/member/main";
	}
	
	

//	@RequestMapping("main")
//	public String main(HttpSession session) {
//		String id = (String) session.getAttribute("id");
//		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
//		if (id == null) {
//			return "redirect:/member/login";
//		}
//		
//		return "member/main";
//	}
//
//	@RequestMapping("logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/member/login";
//	}
//
//	@RequestMapping("get")
//	public String get(HttpSession session, Model model) {
//		String id = (String) session.getAttribute("id");
//		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
//		if (id == null) {
//			return "redirect:/member/login";
//		}
//
//		Member member = memberService.get(id);
//
//		// model.addAttribute("member", member);
//		model.addAttribute(member);
//
//		return "member/info";
//	}
//
//	@RequestMapping(value = "update", method = RequestMethod.GET)
//	public String update(HttpSession session, Model model) {
//		String id = (String) session.getAttribute("id");
//		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
//		if (id == null) {
//			return "redirect:/member/login";
//		}
//
//		Member member = memberService.get(id);
//
//		model.addAttribute(member);
//
//		return "member/update";
//	}
//
//	@RequestMapping(value = "update", method = RequestMethod.POST)
//	public String update(HttpSession session, @ModelAttribute Member member, HttpServletResponse response)
//			throws Exception {
//		String id = (String) session.getAttribute("id");
//		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
//		if (id == null) {
//			return "redirect:/member/login";
//		}
//
//		int check = memberService.userCheck(member.getId(), member.getPasswd());
//
//		if (check == MemberService.ONLY_PASSWD_FAIL) {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//
//			out.println("<script>");
//			out.println("alert('패스워드 틀림');");
//			out.println("history.back();");
//			out.println("</script>");
//			out.close(); // out.flush() 먼저 수행하고 자원닫기
//			return null;
//		}
//
//		memberService.update(member);
//
//		return "redirect:/member/main";
//	}
//	
//	@RequestMapping(value = "delete", method = RequestMethod.GET)
//	public String delete(HttpSession session) {
//		String id = (String) session.getAttribute("id");
//		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
//		if (id == null) {
//			return "redirect:/member/login";
//		}
//		return "member/delete";
//	}
//	
//	@RequestMapping(value = "delete", method = RequestMethod.POST)
//	public String delete(HttpSession session, @RequestParam String passwd, HttpServletResponse response) throws Exception {
//		String id = (String) session.getAttribute("id");
//		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
//		if (id == null) {
//			return "redirect:/member/login";
//		}
//		
//		int check = memberService.userCheck(id, passwd);
//		
//		if (check == MemberService.ONLY_PASSWD_FAIL) {
//			response.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>");
//            out.println("alert('패스워드틀림');");
//            out.println("history.back();");
//            out.println("</script>");
//            out.close();
//            return null;
//		}
//		
//		memberService.delete(id);
//		session.invalidate();  // 세션값 초기화(전체 삭제)
//		
//		response.setContentType("text/html; charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.println("<script>");
//        out.println("alert('회원 삭제되었습니다.');");
//        out.println("location.href='login'");
//        out.println("</script>");
//        out.close();
//        return null;
//	}
//	
//	
//	@RequestMapping("list")
//	public String list(HttpSession session, Model model) {
//		String id = (String) session.getAttribute("id");
//		// 세션값 없거나 admin이 아니면 /member/main 리다이렉트 이동
//		if (id == null || !id.equals("admin")) {
//			return "redirect:/member/main";
//		}
//		
//		List<Member> list = memberService.getAll();
//		
//		model.addAttribute("list", list);
//		
//		return "member/list";
//	}

}