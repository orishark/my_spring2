package com.myspring.model.reply.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myspring.model.reply.dto.ReplyDTO;

@Repository("ReplyDAOImpl")
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	SqlSession sqlSession;
	
	private final String namespace = "com.myspring.dao.reply.";
	
	@Override
	public List<ReplyDTO> list(Integer bno, int start, int end) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("bno", bno);
		
		sqlSession.update(namespace + "init");
		return sqlSession.selectList(namespace + "listReply", map);
	}

	@Override
	public int count(int bno) {
		return sqlSession.selectOne(namespace + "count", bno);
	}

	@Override
	public void create(ReplyDTO dto) {
		sqlSession.insert(namespace + "insertReply", dto);	// 일반 댓글
	}

	@Override
	public void update(ReplyDTO dto) {
		sqlSession.update(namespace + "updateReply", dto);
	}

	@Override
	public void delete(Integer rno) {
		sqlSession.delete(namespace + "deleteReply", rno);
	}

	@Override
	public void deleteAll(int bno) {
		sqlSession.delete(namespace + "deleteAllReply", bno);
	}

	@Override
	public ReplyDTO detail(int rno) {
		// TODO Auto-generated method stub
		return null;
	}

}
