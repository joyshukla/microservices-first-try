package io.pivotal.microservices.services.forum;

import io.pivotal.microservices.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Hide the access to the microservice inside this local service.
 * 
 * @author Karmana Trivedi
 */
@Service
public class ForumPostsService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(ForumPostsService.class.getName());

    public ForumPostsService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    /**
     * The RestTemplate works because it uses a custom request-factory that uses
     * Ribbon to look-up the service to use. This method simply exists to show this.
     */
    @PostConstruct
    public void demoOnly() {
        // Can't do this in the constructor because the RestTemplate injection
        // happens afterwards.
        logger.warning("The RestTemplate request factory is " + restTemplate.getRequestFactory().getClass());
    }

    public List<Post> showAllPosts() {
        logger.info("showAllPosts() invoked:");
        Post[] posts = null;
        logger.info("serviceUrl:" + serviceUrl);

        try {
            posts = restTemplate.getForObject(serviceUrl + "/posts/showall", Post[].class);
        } catch (HttpClientErrorException e) { // 404
            // Nothing found
        }

        logger.info(String.valueOf(posts[0]));

        if (posts == null || posts.length == 0)
            return null;
        else{
            logger.info("showAllPosts() returned:" + Arrays.asList(posts));
            return Arrays.asList(posts);
        }

    }


    public void addNewPost(String subject, String body) {

        logger.info("addNewPost() invoked: for " + subject + " : " + body);
        try {
            restTemplate.getForObject(serviceUrl + "/posts/add/{subject}/{body}", Post.class, subject, body);
        } catch (Exception e) {
            logger.severe(e.getClass() + ": " + e.getLocalizedMessage());
        }

    }

    /*
    public Account findByNumber(String accountNumber) {

        logger.info("findByNumber() invoked: for " + accountNumber);
        try {
            return restTemplate.getForObject(serviceUrl + "/accounts/{number}", Account.class, accountNumber);
        } catch (Exception e) {
            logger.severe(e.getClass() + ": " + e.getLocalizedMessage());
            return null;
        }
    }

    public List<Account> byOwnerContains(String name) {
        logger.info("byOwnerContains() invoked:  for " + name);
        Account[] accounts = null;

        try {
            accounts = restTemplate.getForObject(serviceUrl + "/accounts/owner/{name}", Account[].class, name);
        } catch (HttpClientErrorException e) { // 404
            // Nothing found
        }

        if (accounts == null || accounts.length == 0)
            return null;
        else
            return Arrays.asList(accounts);
    }

    public Account getByNumber(String accountNumber) {
        Account account = restTemplate.getForObject(serviceUrl + "/accounts/{number}", Account.class, accountNumber);

        if (account == null)
            throw new AccountNotFoundException(accountNumber);
        else
            return account;
    }
     */
}
