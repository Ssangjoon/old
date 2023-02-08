package com.bts.yomojomo.domain;

public class FinalSns {
	int no;
	int snsno;
	String snsemail;
	
	@Override
	public String toString() {
		return "FinalSns [no=" + no + ", snsno=" + snsno + ", snsemail=" + snsemail + "]";
	}
	public int getno() {
		return no;
	}
	public void setno(int no) {
		this.no = no;
	}
	public int getSnsno() {
		return snsno;
	}
	public void setSnsno(int snsno) {
		this.snsno = snsno;
	}
	public String getSnsemail() {
		return snsemail;
	}
	public void setSnsemail(String snsemail) {
		this.snsemail = snsemail;
	}

	

}