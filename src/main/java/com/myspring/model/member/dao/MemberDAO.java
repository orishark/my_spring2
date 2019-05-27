package com.myspring.model.member.dao;

import java.util.List;

import com.myspring.model.member.dto.MemberDTO;

public interface MemberDAO {

	public void addMember(MemberDTO member) throws Exception;
	public void deleteMember(int mno) throws Exception;
	public MemberDTO selectMemberInfo(String user_id) throws Exception;
	public List<MemberDTO> selectMemberList() throws Exception;
	public void updateMemberInfo(MemberDTO member) throws Exception;
	public String loginCheck(MemberDTO member) throws Exception;
	public String userIdCheck(String user_id) throws Exception;
}
