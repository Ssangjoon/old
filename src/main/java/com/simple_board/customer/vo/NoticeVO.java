package com.simple_board.customer.vo;

public class NoticeVO{
	private int seq;	// seq
	private String title;	// 제목
	private String content;	// 내용
	private String writer;	// 작성자
	private String view_count;	// 조회수
	private String crt_dt;	// 작성일
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getView_count() {
		return view_count;
	}
	public void setView_count(String view_count) {
		this.view_count = view_count;
	}
	public String getCrt_dt() {
		return crt_dt;
	}
	public void setCrt_dt(String crt_dt) {
		this.crt_dt = crt_dt;
	}
	
}
