package com.myspring.service.reply;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.myspring.model.reply.dao.ReplyDAO;
import com.myspring.model.reply.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Resource(name="ReplyDAOImpl")
	ReplyDAO replyDao;
	
	@Override
	public List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session) {
		return replyDao.list(bno, start, end);
	}

	@Override
	public int count(int bno) {
		return replyDao.count(bno);
	}

	@Override
	public void create(ReplyDTO dto) {
		replyDao.create(dto);
	}

	@Override
	public void update(ReplyDTO dto) {
		replyDao.update(dto);
	}

	@Override
	public void delete(Integer rno) {
		replyDao.delete(rno);
	}

	@Override
	public void deleteAll(int bno) {
		replyDao.deleteAll(bno);
	}

	@Override
	public ReplyDTO detail(int rno) {
		// TODO Auto-generated method stub
		return null;
	}

}
