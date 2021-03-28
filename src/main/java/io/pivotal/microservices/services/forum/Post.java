package io.pivotal.microservices.services.forum;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Post DTO - used to interact with the {@link ForumPostsService}.
 * 
 * @author Paul Chapman
 */
@JsonRootName("Post")
public class Post {

	protected Long id;
	protected String threadID;
	protected String subject;
	protected String body;

	/**
	 * Default constructor for JPA only.
	 */
	protected Post() {
	}

	public long getId() {
		return id;
	}

	/**
	 * Set JPA id - for testing and JPA only. Not intended for normal use.
	 * 
	 * @param id
	 *            The new id.
	 */
	protected void setId(long id) {
		this.id = id;
	}

	public String getThreadID() {
		return threadID;
	}
	protected void setThreadID(String threadID) {
		this.threadID = threadID;
	}

	public String getSubject() {
		return subject;
	}
	protected void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}
	protected void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return subject + " [" + this.threadID + "]: " + body;
	}

}
