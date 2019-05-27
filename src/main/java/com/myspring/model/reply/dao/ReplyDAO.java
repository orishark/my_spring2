package com.myspring.model.reply.dao;

import java.util.List;

import com.myspring.model.reply.dto.ReplyDTO;

public interface ReplyDAO {

	public List<ReplyDTO> list(Integer bno, int start, int end);
	public int count(int bno);
	public void create(ReplyDTO dto);
	public void update(ReplyDTO dto);
	public void delete(Integer rno);
	public void deleteAll(int bno);
	public ReplyDTO detail(int rno);
}
