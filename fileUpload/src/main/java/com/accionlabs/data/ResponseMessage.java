package com.accionlabs.data;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {
private String success;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
