package co.yedam.emp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DAO;
import co.yedam.emp.vo.EmpVO;

public class EmpDAO extends DAO {  //JDBC구현
	//싱글톤 (메모리 절약) -> 생성자:private(외부접근방지), 메소드:public getInstance 
	
	private static EmpDAO instance = new EmpDAO(); //필드
	private EmpDAO() {		
	}
	public static EmpDAO getInstance() {
		return instance;
	}
	
	
	
	public List<EmpVO> empList() {
		List<EmpVO> emps = new ArrayList<>();
		connect();
		sql = "select * from emp_temp order by employee_id";
		// psmt : 쿼리실행 & 실행결과를 반환
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));

				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return emps;
	}
}
