package com.yedam.notice.command;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeAddJson implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//글등록처리
		String savePath = req.getServletContext().getRealPath("/upload");
		int maxSize = (1024 * 1024 * 10); //1024 -> 1kB 
		String encoding = "utf-8";
		Gson gson = new GsonBuilder().create();
		NoticeVO vo = new NoticeVO();
		try {
			//파일업로드 서블릿
			MultipartRequest multi = new MultipartRequest(req,savePath,maxSize,encoding, new DefaultFileRenamePolicy());
			
			String title= multi.getParameter("title"); //글등록폼에서 쓴 이름
			String subject = multi.getParameter("subject");
			String writer = multi.getParameter("writer");
			String fileName = "";
			
			Enumeration<?> files = multi.getFileNames(); //파일 여러개 담을 수 있다
			while(files.hasMoreElements()) {
				String file = (String)files.nextElement();
				System.out.println(file);
				fileName = multi.getFilesystemName(file); //똑같은 이름의 파일을 올렸을 때, 덮어쓰기 방지로 바뀐 파일의 이름을 읽어옴
			}
		
			//NoticeVO생성
			vo = new NoticeVO();
			vo.setAttachFile(fileName);
			vo.setNoticeSubject(subject);
			vo.setNoticeTitle(title);
			vo.setNoticeWriter(writer);
			vo.setNoticeDate(new Date());
			
			NoticeService service = new NoticeServiceImpl();
			service.addNotice(vo);
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		return gson.toJson(vo) + ".json";
	}

}
