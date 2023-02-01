package com.project.mini;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/projectJson")
public class ProjectServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String addr = req.getParameter("addr");
		

		CustomerVO vo = new CustomerVO();
		vo.setCustomerId(id);
		vo.setCustomerPw(pw);
		vo.setCustomerAddr(addr);

		CustomerDAO dao = new CustomerDAO();

			if (dao.addCustomer(vo) > 0) {
				resp.getWriter().print("{\"retCode\" : \"Success\"}");
			} else {
				resp.getWriter().print("{\"retCode\" : \"Fail\"}");
			}
		}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8"); // 응답정보에 한글이 있으면 처리
		resp.setContentType("text/json;charset=utf-8"); // 형식이 json이다

		CustomerDAO dao = new CustomerDAO();
		List<CustomerVO> list = dao.CustomerVoList();
		// [{"id" : 100, "filrstName" : "Hong", "email" : "HONG".....}, {}, {}]
		String json = "[";
		for (int i = 0; i < list.size(); i++) {
			json +=  "{\"id\" : \"" + list.get(i).getCustomerId() + "\", \"pw\" : \"" + list.get(i).getCustomerPw()
					+ "\", \"addr\" : \"" + list.get(i).getCustomerAddr()+ "\"}";
			if (i + 1 != list.size()) {
				json += ",";
			}
		}
		json += "]";

		resp.getWriter().print(json);
}
}
