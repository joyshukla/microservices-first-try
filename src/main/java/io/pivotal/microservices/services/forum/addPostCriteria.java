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
		/*
		if (StringUtils.hasText(accountNumber))
			return !(StringUtils.hasText(searchText));
		else
			return (StringUtils.hasText(searchText));

		 */
	}

	public boolean validate(Errors errors) {
		return true;
		/*
		if (StringUtils.hasText(accountNumber)) {
			if (accountNumber.length() != 9)
				errors.rejectValue("accountNumber", "badFormat",
						"Account number should be 9 digits");
			else {
				try {
					Integer.parseInt(accountNumber);
				} catch (NumberFormatException e) {
					errors.rejectValue("accountNumber", "badFormat",
							"Account number should be 9 digits");
				}
			}

			if (StringUtils.hasText(searchText)) {
				errors.rejectValue("searchText", "nonEmpty",
						"Cannot specify account number and search text");
			}
		} else if (StringUtils.hasText(searchText)) {
			; // Nothing to do
		} else {
			errors.rejectValue("accountNumber", "nonEmpty",
					"Must specify either an account number or search text");

		}

		return errors.hasErrors();

		 */
	}
/*
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (StringUtils.hasText(accountNumber) ? "number: " + accountNumber
				: "")
				+ (StringUtils.hasText(searchText) ? " text: " + searchText
						: "");
	}
 */
}
