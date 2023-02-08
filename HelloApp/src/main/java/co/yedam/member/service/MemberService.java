package co.yedam.member.service;

import java.util.List;

import co.yedam.member.vo.MemberVO;

public interface MemberService {  //기능구현은 service에 있다
	//mapper의 작은 기능을 조합하는 곳
	public MemberVO login(MemberVO member);
	public int addMember(MemberVO member);
	public List<MemberVO> memberList();
	public MemberVO getMember(String id);
	
}
