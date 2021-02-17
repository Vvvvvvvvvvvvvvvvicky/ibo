package com.vicky.demo_s.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
	public ResultCode code;
	public String msg;
	public Object obj;
}


