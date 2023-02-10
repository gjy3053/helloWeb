package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class ImageUpload implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String savePath = req.getServletContext().getRealPath("/upload");
		int maxSize = (1024 * 1024 * 10); //1024 -> 1kB 
		String encoding = "utf-8";
		
		try {
			//파일업로드 서블릿
			MultipartRequest multi = new MultipartRequest(req,savePath,maxSize,encoding, new DefaultFileRenamePolicy());
			
			String id= multi.getParameter("id"); //글등록폼에서 쓴 이름
			String fileName = "";
			
			Enumeration<?> files = multi.getFileNames(); //파일 여러개 담을 수 있다
			while(files.hasMoreElements()) {
				String file = (String)files.nextElement();
				System.out.println(file);
				fileName = multi.getFilesystemName(file); //똑같은 이름의 파일을 올렸을 때, 덮어쓰기 방지로 바뀐 파일의 이름을 읽어옴
			}
			
			MemberVO vo = new MemberVO();
			vo.setMemberId(id);
			vo.setImage(fileName);
			MemberService service = new MemberServiceMybatis();
			service.modifyMember(vo); //정보변경
			
			System.out.println(vo);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		//{"retCode": "Success"}
		return "{\"retCode\": \"Success\"}";
	}

}
