package com.yedam.notice.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class NoticeListAjaxDel implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		NoticeService service = new NoticeServiceImpl();
		String json = "";
		if(service.removeReply(Integer.parseInt(id))>0) {
			//{"retCode" : "Success"} -> 성공했을때 넘겨짐
			json = "{\"retCode\" : \"Success\"}";
		}else {
			//{"retCode" : "Fail"}
			json = "{\"retCode\" : \"Fail\"}";
		}
		
		return json + ".json";
	}

}
