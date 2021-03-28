package io.pivotal.microservices.posts;

import org.apache.catalina.util.ToStringUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public static Integer nextThreadId = 1;

    @Id
    protected Long id;

    // thread ID
    @Column(name = "threadid")
    protected String threadid;

    @Column(name = "subject")
    protected String subject;

    @Column(name = "body")
    protected String body;

    @Column(name = "accountnumber")
    protected String accountnumber;

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


    protected static String getNextThreadId() {
        synchronized (nextThreadId) {
            nextThreadId++;
            return Integer.toString(nextThreadId);
        }
    }

    protected Post() {
    }

    public Post(String threadid, String subject, String body, String accountnumber) {
        // threadID provided, add the post to provided ThreadID
        id = getNextId();
        this.threadid = threadid;
        this.subject = subject;
        this.body = body;
        this.accountnumber = accountnumber;
    }

    public Post(String subject, String body, String accountnumber) {
        // threadID not provided. create a new threadID and add the new post to it.
        id = getNextId();
        this.threadid = getNextThreadId();
        this.subject = subject;
        this.body = body;
        this.accountnumber = accountnumber;
    }

    public Post(String subject, String body) {
        // threadID not provided. create a new threadID and add the new post to it.
        id = getNextId();
        this.threadid = getNextThreadId();
        this.subject = subject;
        this.body = body;
        this.accountnumber = "123456789";
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

    public String getThreadid() {
        return threadid;
    }
    protected void setThreadid(String threadid) {
        this.threadid = threadid;
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

    public String getAccountnumber() {
        return accountnumber;
    }
    protected void setAccountnumber(String subject) {
        this.accountnumber = accountnumber;
    }

    @Override
    public String toString() {
        return  "[" + accountnumber + "][" +  threadid + "]" + subject + "-" + body;
    }

}
