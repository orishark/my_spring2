package com.myspring.model.member.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MemberDTO {

	private int mno;				// 고유번호
	private String user_id;			// 사용자 아이디
	private String password;		// 사용자 암호
	private String name;			// 사용자 이름
	private String email;			// 사용자 이메일
	private Date   reg_date;		// 등록일
	private String image_name;		// 프로필 이미지 이름
}
