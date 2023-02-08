package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;

public interface NoticeMapper {
	public List<NoticeVO> selectList();
	public NoticeVO searchOne(int nid); //한건조회
	public int insertNotice(NoticeVO notice); //입력
	public int updateNotice(NoticeVO notice); //업데이트
	public int deleteNotice(int nid); //삭제
	public int increaseCnt(int nid);//조회수 증가
	//댓글목록
	//댓글등록
}
