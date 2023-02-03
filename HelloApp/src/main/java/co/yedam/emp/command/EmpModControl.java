package co.yedam.emp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;

public class EmpModControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		//service: int modEmp(EmpVO) -> serviceImpl : modEmp(EmpVO) -> dao: updateEmp(EmpVO)
		

	}

}
