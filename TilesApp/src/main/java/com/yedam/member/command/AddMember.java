package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class AddMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 서비스에 있는 addmember, 매퍼에 있는 addmember활용하기
		// {"retCode": "Success"}, {"retCode": "Fail"}

		String savePath = req.getServletContext().getRealPath("/upload");
		int maxSize = (1024 * 1024 * 10); // 1024 -> 1kB
		String encoding = "utf-8";

		// 파일업로드 서블릿
		MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());

		String id = multi.getParameter("id"); // 글등록폼에서 쓴 이름
		String name = multi.getParameter("name");
		String phone = multi.getParameter("phone");
		String addr = multi.getParameter("addr");
		String fileName = "";

		Enumeration<?> files = multi.getFileNames(); // 파일 여러개 담을 수 있다
		while (files.hasMoreElements()) {
			String file = (String) files.nextElement();
			System.out.println(file);
			fileName = multi.getFilesystemName(file); // 똑같은 이름의 파일을 올렸을 때, 덮어쓰기 방지로 바뀐 파일의 이름을 읽어옴
		}

		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setMemberPw(id);
		vo.setMemberName(name);
		vo.setMemberPhone(phone);
		vo.setMemberAddr(addr);
		vo.setResponsibility("user");
		vo.setImage(fileName);
		
		System.out.println(vo);
		//결과값을 map타입에 저장
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("member",vo);
		Gson gson = new GsonBuilder().create();
		
		MemberService service = new MemberServiceMybatis();
		

		if (service.addMember(vo) > 0) {
			resultMap.put("retCode", "Success");
			//return "{\"retCode\": \"Success\"}.json";
		} else {
			resultMap.put("retCode", "Fail");
			//return "{\"retCode\": \"Fail\"}.json";
		}

		return gson.toJson(resultMap) + ".json";
	}

}
