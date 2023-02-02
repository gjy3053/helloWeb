package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.emp.command.LoginControl;
import co.yedam.emp.command.ServiceControl;

@WebServlet("*.do") //똑같은 url있으면안된다
public class FrontController extends HttpServlet{
	
	//url패턴과 실행할 프로그램 매핑
	Map<String, Command> map;  //Command -> 만든 인터페이스 이름
	
	public FrontController() {
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/service.do", new ServiceControl());
		map.put("/login.do", new LoginControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//url패턴을 확인 => 요청페이지 어떤지 ?
		
		//만약 http://localhost:8081/HelloApp/service.do 라면
		String uri = req.getRequestURI(); //url  /HelloApp/service.do
		String context = req.getContextPath(); // /HelloApp
		String page = uri.substring(context.length());
		
		//System.out.println("uri : " + uri);
		//System.out.println("context : " + context);
		//System.out.println(page); //service.do
		
		Command command = map.get(page);
		command.exec(req, resp);
		
	}
	
	@Override
	public void destroy() {
	
	}
}
