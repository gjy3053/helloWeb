package co.yedam.emp.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.vo.EmpVO;

public class EmpModFormControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		// 수정화면 : WEB-INF/views/modify.jsp 호출
		//String id = req.getParameter("eid");
		//EmpService service = new EmpServiceImpl();
		//EmpVO emp = service.getEmp(Integer.parseInt(id));
		//req.setAttribute("searchVO", emp);

		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/modify.jsp");
		try {
			rd.forward(req, resp);
		} catch (Exception e) { // 모든 예외처리
			e.printStackTrace();
		}
	}

}
