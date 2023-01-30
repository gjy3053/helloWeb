package com.yedam;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.EmpDAO;
import com.yedam.emp.EmpVO;

@WebServlet("/myinfo")
public class FirstServlet extends HttpServlet{ //http통신 요청 -응답 (클라이언트 측에서 요청이 오면 응답을 해준다)
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청방식 : post요청일 경우에 tomcat 서버가 실행해줌
		req.setCharacterEncoding("utf-8"); //한글처리
		
		String id = req.getParameter("emp_id"); //form태그의 name속성의 값을 읽어들일때 getparameter메소드를 쓴다
		String name = req.getParameter("last_name");
		String mail = req.getParameter("email");
		String job = req.getParameter("job_id");
		String hdate = req.getParameter("hire_date");
		
		EmpVO emp = new EmpVO();
		emp.setEmployeeId(Integer.parseInt(id)); // setEmployeeId(변수 int)
		emp.setLastName(name);
		emp.setEmail(mail);
		emp.setJobId(job);
		emp.setHireDate(hdate);
		
		System.out.println(emp);
		
		EmpDAO dao = new EmpDAO();
		dao.addEmp(emp);
		
		doGet(req,resp); //doGet메소드 호출.
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//super.doGet(req, resp);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print("<h3>servlet page</h3>"); //클라이언트 쪽으로 출력해주는 스트림을 만들어줌
		resp.getWriter().print("<a href = 'info/resume.html'>이력서</a>");
		resp.getWriter().print("<a href = 'index.html'>첫 페이지로 이동</a>");
		
		//jdbc 연결처리
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empVoList();
		
		resp.getWriter().print("<ul>");
		for(EmpVO emp : list) {
			resp.getWriter().print("<li>" + emp.getEmployeeId() + "/" + emp.getLastName()  + "/" + emp.getEmployeeId() + "</li>");
		}
		resp.getWriter().print("</ul>");
		
		
	}
}
