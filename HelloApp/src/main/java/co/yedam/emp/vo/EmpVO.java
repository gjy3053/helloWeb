package co.yedam.emp.vo;

import lombok.Data;

@Data //기본적으로 필요한거 다 있음 ex)getter, setter, 생성자 등등
public class EmpVO {
	private int employeeId;
	private String firstName;
	private String lastName;
	
}
