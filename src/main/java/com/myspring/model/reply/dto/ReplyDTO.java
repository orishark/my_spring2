package com.myspring.model.reply.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDTO {

	private Integer rno;
	private Integer bno;
	private String reply_text;
	private String replyer;
	private String name;
	private Date regdate;
	private Date update_date;
	private String secret_reply;
}
