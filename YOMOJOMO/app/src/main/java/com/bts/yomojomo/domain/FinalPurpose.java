package com.bts.yomojomo.domain;

public class FinalPurpose {

	int no;
	int membno;

	public FinalPurpose(int pupsArr) {
		no = pupsArr;
	}

	@Override
	public String toString() {
		return "FinalPurpose [no=" + no + ", membno=" + membno + "]";
	}

	public int getno() {
		return no;
	}

	public void setno(int no) {
		this.no = no;
	}

	public int getMembno() {
		return membno;
	}

	public void setMembno(int membno) {
		this.membno = membno;
	}

}