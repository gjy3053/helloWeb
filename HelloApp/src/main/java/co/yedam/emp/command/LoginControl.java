package co.yedam.emp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;

public class LoginControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		String method = req.getMethod();  //요청방식이 get인지 등등 알려줌
		System.out.println("요청방식 : " + method);
		System.out.println("로그인 컨트롤러");
		String id = req.getParameter("uid"); //form>input:name속성.
		String pw = req.getParameter("upw");
		System.out.println("id : " + id); //전송버트 누르면 뜸
		System.out.println("pw : " + pw);
		
		
	}

}
