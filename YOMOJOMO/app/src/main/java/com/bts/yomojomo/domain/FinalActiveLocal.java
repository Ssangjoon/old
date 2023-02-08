package com.bts.yomojomo.domain;

import lombok.Data;

@Data
public class FinalActiveLocal {

	int no;
	int itrlocalno;

	public FinalActiveLocal(int value) {
		this.itrlocalno = value;
	}

}
