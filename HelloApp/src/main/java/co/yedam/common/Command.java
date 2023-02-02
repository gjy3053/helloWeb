package co.yedam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command { //인터페이스는 추상메소드만 등록
	
	public void exec(HttpServletRequest req, HttpServletResponse resp);

}
