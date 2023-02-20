package com.yedam.notice.vo;

import java.util.Date;

import lombok.Data;
@Data
public class NoticeVO { //DB정보
	private int noticeId;
	private String noticeWriter;
	private String noticeTitle;
	private String noticeSubject;
	private Date noticeDate;
	private int hitCount;
	private String attachFile;
}
