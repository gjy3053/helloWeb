package co.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.common.Command;
import co.yedam.member.service.MemberService;
import co.yedam.member.service.MemberServiceMybatis;
import co.yedam.member.vo.MemberVO;

public class SignOnControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		//form:multipart/form-data => 처리 (MultipartRequest)
		//생성자의 매개값: 1) 요청정보 2)저장경로 3)최대파일사이즈지정 4)인코딩(일본어 같은거 있으면) 5)리네임정책(서버에 똑같은 파일있으면 덮어쓰기 안되도록)
		String savePath = req.getServletContext().getRealPath("/images");
		int maxSize = (1024 * 1024 * 10); //1024 -> 1kB 
		String encoding = "utf-8";
		
		try {
			//파일업로드 서블릿
			MultipartRequest multi = new MultipartRequest(req,savePath,maxSize,encoding, new DefaultFileRenamePolicy());
			
			String id = multi.getParameter("member_id");
			String pw = multi.getParameter("member_pw");
			String nm = multi.getParameter("member_name");
			String ph = multi.getParameter("member_phone");
			String fileName = "";
			
			Enumeration<?> files = multi.getFileNames(); //파일 여러개 담을 수 있다
			while(files.hasMoreElements()) {
				String file = (String)files.nextElement();
				System.out.println(file);
				fileName = multi.getFilesystemName(file); //똑같은 이름의 파일을 올렸을 때, 덮어쓰기 방지로 바뀐 파일의 이름을 읽어옴
			}
			
			MemberVO member = new MemberVO();
			member.setMemberId(id);
			member.setMemberPw(pw);
			member.setMemberName(nm);
			member.setMemberPhone(ph);
			member.setImage(fileName);
			
			MemberService service = new MemberServiceMybatis();
			if(service.addMember(member)>0) {
				resp.sendRedirect("empList.do");
			}else {
				resp.sendRedirect("errorPage.do");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
