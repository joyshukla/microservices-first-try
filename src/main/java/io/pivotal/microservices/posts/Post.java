package io.pivotal.microservices.posts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Persistent post entity with JPA markup. posts are stored in an H2
 * relational database.
 *
 * @author Karmana Trivedi
 */
@Entity
@Table(name = "T_POST")
public class Post implements Serializable{

    private static final long serialVersionUID = 1L;

    public static Long nextId = 0L;
    public static Long nextThreadId = 0L;

    @Id
    protected Long id;

    // thread ID
    //@Column(name = "threadid")
    protected Long threadID;

    //@Column(name = "subject")
    protected String subject;

    //@Column(name = "body")
    protected String body;

    /**
     * This is a very simple, and non-scalable solution to generating unique
     * ids. Not recommended for a real application. Consider using the
     * <tt>@GeneratedValue</tt> annotation and a sequence to generate ids.
     *
     * @return The next available id.
     */
    protected static Long getNextId() {
        synchronized (nextId) {
            return nextId++;
        }
    }


    protected static Long getNextThreadId() {
        synchronized (nextThreadId) {
            return nextThreadId++;
        }
    }

    protected Post() {
    }

    public Post(Long threadID, String subject, String body) {
        // threadID provided, add the post to provided ThreadID
        id = getNextId();
        this.threadID = threadID;
        this.subject = subject;
        this.body = body;
    }

    public Post(String subject, String body) {
        // threadID not provided. create a new threadID and add the new post to it.
        id = getNextId();
        this.threadID = getNextThreadId();
        this.subject = subject;
        this.body = body;
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

    public long getThreadId() {
        return threadID;
    }

    protected void setThreadID(Long threadID) {
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
