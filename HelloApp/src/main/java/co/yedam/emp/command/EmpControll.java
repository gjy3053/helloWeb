package co.yedam.emp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceMybatis;
import co.yedam.emp.vo.EmpVO;

public class EmpControll implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		RequestDispatcher rd = null;
		
		String method = req.getMethod();
		System.out.println("요청방식 : " + method);
		PrintWriter out = null;
		
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(method.equals("GET")) {
			try {
				resp.sendRedirect("result/empList.jsp");
				//resp.sendRedirect("https://www.daum.net");
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			out.print("");
		}else if(method.equals("POST")) {
			String eid = req.getParameter("eid");  //emp.jsp의 name속성이 파라미터 이름
			String lName = req.getParameter("last_name");
			String job = req.getParameter("email");
			String hire = req.getParameter("hire_date");
			String mail = req.getParameter("job");
			
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(Integer.parseInt(eid)); //return 타입 무조건 String 이라서 int타입변환
			emp.setLastName(lName);
			emp.setJobId(job);
			emp.setHireDate(hire);
			emp.setEmail(mail);
			
			//서비스 로직
			EmpService service = new EmpServiceMybatis();
			int r = service.addEmp(emp);
			
			//요청재지정
			try {
				if(r>0) {
				resp.sendRedirect("empList.do"); //중복입력안되게함
					//rd = req.getRequestDispatcher("WEB-INF/result/addResult.jsp");
					//rd.forward(req,resp); //페이지 재지정 (이페이지에 머물러 있음)
				} else {
					//resp.sendRedirect("WEB-INF/result/errorResult.jsp");
					rd = req.getRequestDispatcher("WEB-INF/result/errorResult.jsp");
					rd.forward(req,resp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}else {
			System.out.println("알수 없는 요청입니다.");
			try {
				resp.sendRedirect("https://www.daum.net");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
