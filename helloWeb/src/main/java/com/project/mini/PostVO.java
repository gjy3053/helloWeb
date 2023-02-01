package com.project.mini;

public class PostVO {
	private int postNum;
	private String postId;
	private String postTitle;
	private String postContents;
	private String postDate;
	private int postGood;

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContents() {
		return postContents;
	}

	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public int getPostGood() {
		return postGood;
	}

	public void setPostGood(int postGood) {
		this.postGood = postGood;
	}

	@Override
	public String toString() {
		return "게시글 번호 : " + postNum + "\n제목 : " +  postTitle + "\n작성자 : " + postId  + "\n작성일자 : "
				+ postDate + "\n추천수 : " + postGood + "\n=============================================";
	}
}
