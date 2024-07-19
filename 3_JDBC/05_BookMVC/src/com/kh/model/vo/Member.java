package com.kh.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class Member {

	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private char gender;
	private String address;
	private String phone;
	private char status;
	private Date enrollDate;
}
