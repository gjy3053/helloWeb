package co.yedam.emp.command;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.service.EmpServiceMybatis;


public class EmpForm implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		EmpService service = new EmpServiceImpl();
		Map<String, String> jobList = service.jobList();
		req.setAttribute("jobList", jobList);
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/emp.jsp");
		try {
			rd.forward(req,resp);
		} catch (Exception e) { //모든 예외처리
			e.printStackTrace();
		} 
	}

}
