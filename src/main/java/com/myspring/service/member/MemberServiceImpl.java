package com.myspring.service.member;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.myspring.model.member.dao.MemberDAO;
import com.myspring.model.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Resource(name="MemberDAOImpl")
	MemberDAO memberDao;
	
	@Override
	public void addMember(MemberDTO member) throws Exception {
		memberDao.addMember(member);
	}

	@Override
	public void deleteMember(int mno) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public MemberDTO selectMemberInfo(String user_id) throws Exception {
		return memberDao.selectMemberInfo(user_id);
	}

	@Override
	public List<MemberDTO> selectMemberList() throws Exception {
		return memberDao.selectMemberList();
	}

	@Override
	public void updateMemberInfo(MemberDTO member) throws Exception {
		memberDao.updateMemberInfo(member);
	}

	@Override
	public String loginCheck(MemberDTO member) throws Exception {
		return memberDao.loginCheck(member);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate(); //세션을 모두 초기화
	}

	@Override
	public String userIdCheck(String user_id) throws Exception {
		return memberDao.userIdCheck(user_id);
	}

}
