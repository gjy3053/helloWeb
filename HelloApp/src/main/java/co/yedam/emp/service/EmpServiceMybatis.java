package co.yedam.emp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import co.yedam.common.DataSource;
import co.yedam.emp.vo.EmpVO;

//EmpServiceImpl : jdbc활용
//EmpServiceMybatis : mybatis활용
public class EmpServiceMybatis implements EmpService {

	SqlSessionFactory sessionFactory = DataSource.getInstance();
	SqlSession session = sessionFactory.openSession(true); //true넣어야 자동 커밋

	@Override
	public List<EmpVO> empList() {
		return session.selectList("co.yedam.emp.mapper.EmpMapper.empList");
	}

	@Override
	public int addEmp(EmpVO emp) {
		//데이터 여러개 넣을때는 자동커밋 말고 if문 쓰기 (rollback해줘야하기때문)
		int r = session.insert("co.yedam.emp.mapper.EmpMapper.addEmp", emp);
		if(r>0) {
			session.commit();
		}else {
			session.rollback();
		}
		return r;
	}

	@Override
	public EmpVO getEmp(int empId) { // 한건 가져오기

		return session.selectOne("co.yedam.emp.mapper.EmpMapper.getEmp", empId);
	}

	@Override
	public Map<String, String> jobList() {

		return null;
	}

	@Override
	public int modEmp(EmpVO emp) {

		return session.update("co.yedam.emp.mapper.EmpMapper.modEmp",emp); //처리된 건수 int타입
	}

	@Override
	public int removeEmp(int id) {

		return session.delete("co.yedam.emp.mapper.EmpMapper.removeEmp",id);
	}

}
