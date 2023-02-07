package co.yedam.emp.service;

import java.util.List;
import java.util.Map;

import co.yedam.emp.vo.EmpVO;

//업무에 대한 정의 : interface에 정의하고 구현하는 클래스를 통해 실현 
public interface EmpService {
	public List<EmpVO> empList(); //목록 조회
	public int addEmp(EmpVO emp); //등록
	public EmpVO getEmp(int empId); //조회
	public Map<String, String> jobList(); //직무목록
	public int modEmp(EmpVO emp); //수정 
	public int removeEmp(int empid); //삭제

	
}
