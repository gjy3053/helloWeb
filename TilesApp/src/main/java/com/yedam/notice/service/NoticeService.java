package com.yedam.notice.service;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;

public interface NoticeService {
	public List<NoticeVO> noticeList(); //목록
	public NoticeVO getNotice(int nid); //한건조회
	public int addNotice(NoticeVO notice);// 글등록
	public int modNotice(NoticeVO notice);//글수정
	public int remNotice(int nid); //글삭제
	//댓글등록
	//댓글목록
	
	
}
