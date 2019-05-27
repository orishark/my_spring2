package com.myspring.service.reply;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.myspring.model.reply.dto.ReplyDTO;


public interface ReplyService {

	public List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session);
	public int count(int bno);
	public void create(ReplyDTO dto);
	public void update(ReplyDTO dto);
	public void delete(Integer rno);
	public void deleteAll(int bno);
	public ReplyDTO detail(int rno);
}
