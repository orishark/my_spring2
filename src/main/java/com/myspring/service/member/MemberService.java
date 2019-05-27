package com.myspring.service.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.myspring.model.member.dto.MemberDTO;

public interface MemberService {

	public void addMember(MemberDTO member) throws Exception;
	public void deleteMember(int mno) throws Exception;
	public MemberDTO selectMemberInfo(String user_id) throws Exception;
	public List<MemberDTO> selectMemberList() throws Exception;
	public void updateMemberInfo(MemberDTO member) throws Exception;
	public String loginCheck(MemberDTO member) throws Exception;
	public void logout(HttpSession session);
	public String userIdCheck(String user_id) throws Exception;
}
