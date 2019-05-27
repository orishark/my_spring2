package com.myspring.model.member.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myspring.model.member.dto.MemberDTO;

@Repository("MemberDAOImpl")
public class MemberDAOImpl implements MemberDAO {

	@Inject
	SqlSession sqlSession;
	
	private final String namespace = "com.myspring.dao.member.";
	
	@Override
	public void addMember(MemberDTO member) throws Exception {
		sqlSession.insert(namespace + "addMember", member);
	}

	@Override
	public void deleteMember(int mno) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public MemberDTO selectMemberInfo(String user_id) throws Exception {
		return sqlSession.selectOne(namespace + "selectMemberInfo", user_id);
	}

	@Override
	public List<MemberDTO> selectMemberList() throws Exception {
		return sqlSession.selectList(namespace + "selectMemberList");
	}

	@Override
	public void updateMemberInfo(MemberDTO member) throws Exception {
		 sqlSession.update(namespace + "updateMemberInfo", member);
	}

	@Override
	public String loginCheck(MemberDTO member) throws Exception {
		return sqlSession.selectOne(namespace + "login_check", member);
	}

	@Override
	public String userIdCheck(String user_id) throws Exception {
		return sqlSession.selectOne(namespace + "userId_check", user_id);
	}

}
