package io.pivotal.microservices.services.forum;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class addPostCriteria {
	private String subject;
	private String body;

	//private String searchText;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isValid() {
		return true;
	}

	public boolean validate(Errors errors) {
		return true;
	}

}
