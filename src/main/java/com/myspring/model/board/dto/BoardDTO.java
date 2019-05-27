package com.myspring.model.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {

	private int bno;		// 게시물 번호
	private String title;	// 제목
	private String content; // 내용
	private String writer;  // 작성자 아이디
	private Date regdate;	// 날짜
	private int viewcnt;	// 조회수
	private String name;	// 작성자 이름(member table join)
	private int cnt;		// 댓글 갯수
	private String show;	// 화면 표시 여부
	private String[] files; // 첨부파일 배열
	private String image_name;
}
