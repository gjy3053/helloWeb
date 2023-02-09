package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.member.command.*;
import com.yedam.notice.command.*;

public class FrontController extends HttpServlet{
	
	private Map<String, Command> map;
	private String charset;
	
	public FrontController() {
		map = new HashMap<String, Command>();
	}
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		map.put("/main.do", new MainControl());
		map.put("/second.do", new SecondControl());
		//공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeDetail.do", new NoticeDetail());
		map.put("/noticeForm.do", new NoticeForm());//글등록화면
		map.put("/noticeAdd.do", new NoticeAdd());//글등록처리
		
		// 댓글부분
		map.put("/replyList.do", new ReplyList()); //댓글목록, 여기로 들어가면 json형태의 댓글 보임
		map.put("/removeReply.do", new RemoveReply());//댓글삭제
		map.put("/addReply.do", new AddReply()); //댓글등록
		
		//회원관련
		map.put("/loginForm.do", new LoginForm()); //로그인화면
		map.put("/login.do", new Login()); //로그인처리
		map.put("/mypage.do", new myPage()); //마이페이지 겸 수정화면
		map.put("/logout.do", new Logout());
		map.put("/modifyMember.do", new ModifyMember()); 
		
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String page = uri.substring(context.length());
		System.out.println(page);
		
		Command command = map.get(page);
		String viewPage = command.exec(req,resp);
		// viewPage에 오는 값 -> main/main.tiles
		
		if(viewPage.endsWith(".tiles")) {
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);	
		}else if(viewPage.endsWith(".do")) {
			resp.sendRedirect(viewPage);
		}else if(viewPage.endsWith(".json")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().print(viewPage.substring(0,viewPage.length()-5)); //.json붙은것을 빼야한다
			
		}
		
	}
}
