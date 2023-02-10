package com.yedam.member.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class modifyMember2 implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String addr = req.getParameter("addr");
		String res= req.getParameter("resp");
		
		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setMemberName(name);
		vo.setMemberPhone(phone);
		vo.setMemberAddr(addr);
		vo.setResponsibility(res);
		
		//System.out.println(vo);
		
		MemberService service = new MemberServiceMybatis();
		service.modifyMember(vo);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("member",vo);
		Gson gson = new GsonBuilder().create();
		
		
		if(service.modifyMember(vo)>0) {
			//{retCode:"Success"}
			resultMap.put("retCode", "Success");
		}
		else {
			resultMap.put("retCode", "Fail");
		}
		
		return gson.toJson(resultMap) + ".json";
	}

}
