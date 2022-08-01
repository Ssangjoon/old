package com.simple_board.customer.vo;

public class FaqVO{
	private int seq;	// seq
	private String category;	// 카테고리
	private String title;	// 제목
	private String content;	// 내용
	private String sort_num;	// 노출 순서
	private String crt_dt;	// 작성일
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getSort_num() {
		return sort_num;
	}
	public void setSort_num(String sort_num) {
		this.sort_num = sort_num;
	}
	public String getCrt_dt() {
		return crt_dt;
	}
	public void setCrt_dt(String crt_dt) {
		this.crt_dt = crt_dt;
	}
	
}
