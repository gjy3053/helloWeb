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

		if (rvo == null) {
			req.setAttribute("result","회원정보확인하세요");
			return "member/login.tiles"; //page="mypage"

		} else {
			HttpSession session = req.getSession();
			session.setAttribute("logId", rvo.getMemberId());
			session.setAttribute("logName", rvo.getMemberName());
			req.setAttribute("vo", service.login(vo));
			
		//	MemberVO mvo = service.getMember(id);
		//	session.setAttribute("vo", mvo);
			return "member/mypage.tiles";

		}
	}

}
