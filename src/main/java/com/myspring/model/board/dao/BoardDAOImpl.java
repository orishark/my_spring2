package com.myspring.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myspring.model.board.dao.BoardDAO;
import com.myspring.model.board.dto.BoardDTO;
import com.myspring.util.Utils;

@Repository("BoardDAOImpl")
public class BoardDAOImpl implements BoardDAO {

	@Inject
	SqlSession sqlSession;
	
	private final String namespace = "com.myspring.dao.board.";
	
	@Override
	public void deleteFile(String fullName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAttach(String fullName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAttach(String fullName, int bno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(BoardDTO dto) throws Exception {
		
		// < > 태그 무력화
		String content = dto.getContent(); 
		dto.setContent(Utils.replaceTagToString(content));
		
		String title = dto.getTitle(); 
		dto.setTitle(Utils.replaceTagToString(title));
		
		sqlSession.insert(namespace + "insert", dto);
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		return sqlSession.selectOne(namespace + "view", bno);
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		
		// < > 태그 무력화
		String content = dto.getContent(); 
		dto.setContent(Utils.replaceTagToString(content));
		
		String title = dto.getTitle(); 
		dto.setTitle(Utils.replaceTagToString(title));
				 
		sqlSession.update(namespace + "updateArticle", dto);
	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete(namespace + "deleteArticle", bno);
	}

	@Override
	public List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("start", start);
		map.put("end", end);
		
		sqlSession.update(namespace + "init");
		List<BoardDTO> list = sqlSession.selectList(namespace + "listAll", map);

		return list;
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sqlSession.update(namespace + "increaseViewcnt", bno);
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		return sqlSession.selectOne(namespace + "countArticle", map);
	}

}
