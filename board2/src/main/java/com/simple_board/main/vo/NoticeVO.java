package com.simple_board.main.vo;

public class NoticeVO{
	private int seq;	// seq
	private String title;	// 제목
	private String content;	// 내용
	private String crt_year;	// 작성일
	private int crt_month;	// 작성일
	private String crt_day;	// 작성일
	
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
	public String getCrt_year() {
		return crt_year;
	}
	public void setCrt_year(String crt_year) {
		this.crt_year = crt_year;
	}
	public int getCrt_month() {
		return crt_month;
	}
	public void setCrt_month(int crt_month) {
		this.crt_month = crt_month;
	}
	public String getCrt_day() {
		return crt_day;
	}
	public void setCrt_day(String crt_day) {
		this.crt_day = crt_day;
	}
}
