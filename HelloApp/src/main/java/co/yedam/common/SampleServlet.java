package co.yedam.common;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample")  // "/*" : 어떠한 요청이 들어오든지 servlet실행하겠다
public class SampleServlet extends HttpServlet{ //서블릿은 상속받아야함
	//서블릿. 생명주기 : 인스턴스 -> init(httpServlet이 가지고 있는 메소드) -> service -> destory
	//제어의 역전 : 톰캣이 제어함
	
	public SampleServlet() { //생성자
		System.out.println("생성자 호출");
	}
		
	@Override
		public void init(ServletConfig config) throws ServletException {
		System.out.println("init 실행 : 서버 실행후 한번만 실행됨");
		}
	
	@Override
		protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
			System.out.println("service 실행 : 해당 url을 호출할 떄마다 실행됨");
		}
	@Override
		public void destroy() {
			System.out.println("서버가 종료될때 한번 실행됨");
		}
	
}
