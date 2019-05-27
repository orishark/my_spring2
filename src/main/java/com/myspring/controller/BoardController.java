package com.myspring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.myspring.model.board.dto.BoardDTO;
import com.myspring.service.board.BoardService;
import com.myspring.service.board.Pager;


@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	BoardService boardService;
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav, @RequestParam(defaultValue="1") int curPage, 
	@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) throws Exception{

		int count = boardService.countArticle(search_option, keyword); // 레코드 갯수
	
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		
		keyword = "";
		List<BoardDTO> list = boardService.listAll(start,end,search_option,keyword); // 목록

		mav.setViewName("list"); // 이동할 페이지 저장
		Map<String, Object> map = new HashMap<>();
		map.put("list", list); // 맵에 자료들 저장
		map.put("count", count);
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("pager", pager);
		mav.addObject("map", map); 
	
		return mav; // list페이지로 이동
	}
	
	@RequestMapping(value="view.do", method=RequestMethod.GET)
	public ModelAndView view(ModelAndView mav, @RequestParam int bno, @RequestParam int curPage, 
			@RequestParam String search_option, @RequestParam String keyword, HttpSession session) throws Exception {
		
		boardService.increaseViewcnt(bno);
		mav.setViewName("view");
		mav.addObject("dto", boardService.read(bno));
		mav.addObject("curPage", curPage);
		mav.addObject("search_option", search_option);
		mav.addObject("keyword", keyword);
		return mav;
	}
	
	
	@RequestMapping("write.do")
	public String write() {

		return "write";
	}

	@RequestMapping("edit.do")
	public ModelAndView edit(ModelAndView mav, int bno, HttpSession session) throws Exception {
		
		BoardDTO dto = boardService.read(bno);	
		String user_id = (String)session.getAttribute("user_id");
			
		if(dto.getWriter().equals(user_id)) {
			
			mav.addObject("dto", dto);
			mav.setViewName("edit");
		}else {
			
			RedirectView rv = new RedirectView("/board/list.do");
			mav.setView(rv);
		}
		
		return mav;
	}
	
	@RequestMapping("delete.do")
	public String delete(int bno) throws Exception {
		
		boardService.delete(bno);
		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("update.do")
	public String update(@ModelAttribute BoardDTO dto) throws Exception {
		
		boardService.update(dto);

		return "redirect:/board/list.do";
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute BoardDTO dto, HttpSession session) throws Exception{
		
		// 로그인한 사용자의 아이디
		String writer = (String)session.getAttribute("user_id");
		dto.setWriter(writer);
		// 레코드 저장
		boardService.create(dto);
		// 목록갱신
		return "redirect:/board/list.do";
	}
}
