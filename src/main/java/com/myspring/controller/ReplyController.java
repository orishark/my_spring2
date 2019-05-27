package com.myspring.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.model.reply.dto.ReplyDTO;
import com.myspring.service.board.Pager;
import com.myspring.service.reply.ReplyService;

@RestController
@RequestMapping("/reply/*")
public class ReplyController {

	@Inject
	ReplyService replyService;
	
	@RequestMapping("insert.do")
	public void insert(ReplyDTO dto, HttpSession session) {
		
		String user_id = (String)session.getAttribute("user_id");
		dto.setReplyer(user_id);

		replyService.create(dto);
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(int bno, @RequestParam(defaultValue="1") int curPage, ModelAndView mav, HttpSession session) {
				
		int count = replyService.count(bno);
		Pager pager = new Pager(count, curPage);
		
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		
		List<ReplyDTO> list = replyService.list(bno, start, end, session);
	
		mav.setViewName("reply_list");
		mav.addObject("list", list);
		mav.addObject("pager", pager);
		mav.addObject("bno", bno);
		mav.addObject("count", count);
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(ReplyDTO dto) {
		replyService.update(dto);
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("delete.do")
	public void update(int rno) {
		replyService.delete(rno);
	}
}
