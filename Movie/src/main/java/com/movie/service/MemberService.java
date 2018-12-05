package com.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.domain.MemberVO;
import com.movie.domain.TheatherVO;
import com.movie.repository.MemberDao;


@Service
@Transactional
public class MemberService {
	
	public static final int ID_AND_PASSWD_OK = 1;
	public static final int ONLY_PASSWD_FAIL = 0;
	public static final int ID_AND_PASSWD_FAIL = -1;
	
	// @Autowired : 타입 정보를 기준으로 의존관계를 주입
	@Autowired
	private MemberDao memberDao;
	
	
	
	
	public void add(MemberVO memberVO) {
		System.out.println("memberservice.add");
		memberDao.add(memberVO);
		System.out.println(memberVO.getMb_ID());
	}
	
	public int userCheck(String id, String passwd) {
		MemberVO memberVO = memberDao.get(id);
		if (memberVO != null) { // id 값 일치
			if (memberVO.getMb_passwd().equals(passwd)) { // passwd 값 일치
				return ID_AND_PASSWD_OK;
			} else { // passwd 값 불일치
				return ONLY_PASSWD_FAIL;
			}
		} else {  // member == null
			return ID_AND_PASSWD_FAIL;
		}
		
	}
	
	public String emailCheck(String email) {
		MemberVO memberVO = memberDao.getId(email);
		String id = memberVO.getMb_ID();
		
		
		System.out.println("담겼잖아 : " + id);
		return id;
		}
	
	@Transactional(readOnly=true)
	public MemberVO get(String id) {
		return memberDao.get(id);
	}
	
	public void update(MemberVO memberVO) {
		memberDao.update(memberVO);
	}
	
	public void delete(String id) {
		memberDao.delete(id);
	}
	
	public List<MemberVO> getAll() {
		return memberDao.getAll();
	}
	
	public int countById(String id) {
		return memberDao.countById(id);
	}
	
	public int countByEmail(String email) {
		return memberDao.countByEmail(email);
	}
	
	public String searchIdPwd(String id){
		MemberVO memberVO = memberDao.getPwd(id);
		String passwd = memberVO.getMb_passwd();
		return passwd;
	}
	
	
	
	// 나의 예약정보 가져오기 1. 내 아이디로 가져오기 with mv_num
	public List<MemberVO> getBookListByMvNum(String mb_ID){
		return memberDao.getBookListByMvNum(mb_ID);
	}

	// 나의 예약정보 가져오기 2. 좌석정보 가져오기	
	public List<TheatherVO> getBookListByMvNum (String mb_ID, String mv_num){
		return memberDao.getBookListByMvNum(mb_ID, mv_num);
	}
	
	
	
}




