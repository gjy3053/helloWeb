package co.yedam.emp.command;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.vo.EmpVO;

public class EmpModFormControl implements Command {
	// 수정화면: WEB-INF/views/modify.jsp 호출.
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");  //주소에 있는 아이디 넘겨줌

		EmpService service = new EmpServiceImpl();
		EmpVO emp = service.getEmp(Integer.parseInt(id)); 
		req.setAttribute("searchVO", emp);
		Map<String, String> jobList = service.jobList();
		req.setAttribute("jobList", jobList);
		
		try {
			req.getRequestDispatcher("WEB-INF/views/modify.jsp").forward(req, resp);
		} catch (Exception e) { // 모든 예외처리.
			e.printStackTrace();
		}

	}

}