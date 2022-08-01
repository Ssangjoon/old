package com.simple_board.adm.vo;

public class MemberVO{
	private int seq;	// seq
	private String uid;	// 아이디
	private String name;	// 이름
	private String contact;	// 연락처
	private String email;	// 이메일 주소
	private String memo;	// 메모
	private int state;	// 상태
	private String upw;	// 비밀번호
	private int fail_upw;	// 비밀번호 실패 횟수
	private String crt_dt;	// 생성일
	private String last_login_dt;	// 마지막 로그인
	private String login_info;	// 로그인 시도 정보
	private int check_no;	// 로그인 체크

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	
	public int getFail_upw() {
		return fail_upw;
	}
	public void setFail_upw(int fail_upw) {
		this.fail_upw = fail_upw;
	}
	
	public String getCrt_dt() {
		return crt_dt;
	}
	public void setCrt_dt(String crt_dt) {
		this.crt_dt = crt_dt;
	}
	
	public String getLast_login_dt() {
		return last_login_dt;
	}
	public void setLast_login_dt(String last_login_dt) {
		this.last_login_dt = last_login_dt;
	}
	
	public String getLogin_info() {
		return login_info;
	}
	public void setLogin_info(String login_info) {
		this.login_info = login_info;
	}
	
	public int getCheck_no() {
		return check_no;
	}
	public void setCheck_no(int check_no) {
		this.check_no = check_no;
	}

		
}
