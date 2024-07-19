package com.kh.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class Rent {

	public int rentNo;
	public int rentMemNo;
	public int rentBookNo;
	public Date rentDate;
}
