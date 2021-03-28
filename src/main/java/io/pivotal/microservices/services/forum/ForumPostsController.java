
package io.pivotal.microservices.services.forum;

import java.util.List;
import java.util.logging.Logger;

import io.pivotal.microservices.services.forum.Account;
import io.pivotal.microservices.services.forum.WebAccountsService;
import io.pivotal.microservices.services.forum.Post;
import io.pivotal.microservices.services.forum.ForumPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Client controller, fetches Account info from the microservice via
 * {@link ForumPostsService}.
 * 
 * @author Paul Chapman
 */


@Controller
public class ForumPostsController {

    @Autowired
    protected ForumPostsService postsService;

    protected Logger logger = Logger.getLogger(ForumPostsController.class.getName());

    public ForumPostsController(ForumPostsService accountsService) {
        this.postsService = postsService;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("subject", "body");
    }

    @RequestMapping("/posts")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/posts/showall")
    public String showall(Model model) {
        logger.info("forum-service showall() invoked: ");

        List<Post> posts = postsService.showAllPosts();
        logger.info("forum-service showall() found: " + posts);
        model.addAttribute("showall");
        if (posts != null)
            model.addAttribute("posts", posts);
        return "posts";
    }

    @RequestMapping(value = "/posts/add", method = RequestMethod.GET)
    public String searchForm(Model model) {
        model.addAttribute("addPostCriteria", new io.pivotal.microservices.services.forum.addPostCriteria());
        return "addPost";
    }


    @RequestMapping(value = "/posts/add/doadd")
    public String doAdd(Model model, addPostCriteria criteria, BindingResult result) {
        logger.info("forum-service addPost() invoked: " + criteria);

        criteria.validate(result);

        String subject = criteria.getSubject();
        String body = criteria.getBody();

        postsService.addNewPost(subject, body);


        return "success";

        /*
        if (StringUtils.hasText(accountNumber)) {
            return byNumber(model, accountNumber);
        } else {
            String searchText = criteria.getSearchText();
            return ownerSearch(model, searchText);
        }*/
    }



}