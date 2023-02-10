package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class Login implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 성공하면 mypage로 이동하고
		// 로그인 실패하면 다시 로그인화면으로 이동할때 "아이디와 패스워드를 확인"하도록

		String id = req.getParameter("mid");
		String pw = req.getParameter("mpw");

		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setMemberPw(pw);

		MemberService service = new MemberServiceMybatis();
		MemberVO rvo = service.login(vo);
		String page="";

		if (rvo == null) {
			req.setAttribute("result","회원정보확인하세요");
			page="login";
			
			//return "member/login.tiles"; //page="mypage"
			

		} else {
			//세션에 로그인정보담음
			HttpSession session = req.getSession();
			MemberVO mvo = service.getMember(id);
			session.setAttribute("logId", mvo.getMemberId());
			session.setAttribute("logName", mvo.getMemberName());
			session.setAttribute("Auth", mvo.getResponsibility());
			
			MemberVO vo_1 = service.getMember(id);
			req.setAttribute("vo", vo_1);
			
			//session.setAttribute("vo", mvo);
			page="mypage";
			//return "member/mypage.tiles";

		}
		return "member/"+page+".tiles";
	}

}
