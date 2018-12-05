package com.movie.web;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.domain.BookVO;
import com.movie.domain.MemberVO;
import com.movie.repository.MemberDao;
import com.movie.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService memberService;


	// http://localhost:8999/moive/member/add
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add() {
		
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

		return "redirect:/";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "member/loginForm";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam String mb_ID, @RequestParam String mb_passwd, 
			HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("login : " + mb_ID);
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

		session.setAttribute("mb_ID", mb_ID);
		System.out.println("mb_passwd : " + mb_passwd);
		return "redirect:/";
	}

	@RequestMapping("main")
	public String main(HttpSession session) {
		String id = (String) session.getAttribute("mb_ID");
		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
		if (id == null) {
			return "redirect:/";
		}
		
		return "redirect:/";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		String mb_ID= (String) session.getAttribute("mb_ID");
		System.out.println("로그아웃함ㅋ");
		session.invalidate();
		//session.removeAttribute(mb_ID);
		return "redirect:/";
	}
	
	
	@RequestMapping("myPage") // 멤버객체, 예약정보
	public String myInfo(HttpSession session, Model model) {
		// 1. 멤버VO 가져오기
		String id = (String) session.getAttribute("mb_ID");
		MemberVO memberVO = memberService.get(id);
		
		
		// 2. 예약정보 가져오기
		List<MemberVO> bkList = memberService.getBookListByMvNum(memberVO.getMb_ID());
		
		
		
		model.addAttribute("memberVO", memberVO);
		return "member/myPage";
	}

	@RequestMapping("get")
	public String get(HttpSession session, Model model) {
		String id = (String) session.getAttribute("mb_ID");
		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
		if (id == null) {
			return "redirect:/member/login";
		}

		MemberVO memberVO = memberService.get(id);
		System.out.println("get : " + memberVO.toString());
		// model.addAttribute("member", member);
		model.addAttribute(memberVO);

		return "member/info";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpSession session, @ModelAttribute MemberVO memberVO, HttpServletResponse response)
			throws Exception {
		String id = (String) session.getAttribute("mb_ID");
		//System.out.println("세션" + id);
		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
		if (id == null) {
			System.out.println("세션이 없단다");
			return "redirect:/movie/";
		}
		
		System.out.println(memberVO.toString());

		int check = memberService.userCheck(memberVO.getMb_ID(), memberVO.getMb_passwd());
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(check);
		if (check == MemberService.ONLY_PASSWD_FAIL) {

			out.println("<script>");
			out.println("alert('패스워드 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close(); // out.flush() 먼저 수행하고 자원닫기
			

		} else {
			
			memberService.update(memberVO);
			out.println("<script>");
			out.println("alert('수정 성공');");	
			out.println("location.href='/movie/';");
			out.println("</script>");
			out.close();

		}
		
		
		return null;

	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(HttpSession session) {
		String id = (String) session.getAttribute("mb_ID");
		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
		if (id == null) {
			return "redirect:/member/login";
		}
		return "member/delete";
	}

	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(HttpSession session, @RequestParam String mb_passwd, HttpServletResponse response) throws Exception {
		String id = (String) session.getAttribute("mb_ID");
		// 세션값 id가 없으면 loginForm.jsp 화면으로 이동
		if (id == null) {
			return "redirect:/member/login";
		}
		
		int check = memberService.userCheck(id, mb_passwd);
		
		if (check == MemberService.ONLY_PASSWD_FAIL) {
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('패스워드틀림');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
            return null;
		}
		
		memberService.delete(id);
		session.invalidate();  // 세션값 초기화(전체 삭제)
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('회원 삭제되었습니다.');");
        out.println("location.href='/movie/'");
        out.println("</script>");
        out.close();
        return null;
	}
	
	@RequestMapping(value = "searchId")
	public String searchId() {
		System.out.println("searchId 까지");
		return "/member/searchIdForm";
	}
	
	@RequestMapping(value = "emailChk", method = RequestMethod.POST)
	public String emailChk(@ModelAttribute MemberVO memberVO, HttpServletResponse response) // Model 메서드를써야 el식 쓸수있음 폼에서
			throws Exception {

		String id = memberService.emailCheck(memberVO.getMb_email());
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('회원님의 아이디는 " + id +"입니다.');");
        out.println("location.href='/movie/'");
        out.println("</script>");
        out.close();
        return null;


	}
	
	@RequestMapping(value = "searchPwd")
	public String searchPwd(@ModelAttribute MemberVO memberVO) {
		String id = memberVO.getMb_ID();
		System.out.println("searchPwd id 담겼나? :" + id);
		return "/member/searchPasswdForm";
	}
	
	@RequestMapping(value = "searchIdPasswdForm")
	public String searchIdPasswdForm() {
		return "/member/searchIdPasswdForm";
	}
/*	
	//테스트용
	@RequestMapping(value = "searchPwd")
	public String searchPwd() {
		return "/member/searchPasswdForm";
	}*/

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	