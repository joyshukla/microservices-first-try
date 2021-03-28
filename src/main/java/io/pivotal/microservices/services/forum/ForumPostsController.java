
package io.pivotal.microservices.services.forum;

import java.util.List;
import java.util.logging.Logger;

import io.pivotal.microservices.services.web.Account;
import io.pivotal.microservices.services.web.WebAccountsService;
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
        binder.setAllowedFields("accountNumber", "searchText");
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



}