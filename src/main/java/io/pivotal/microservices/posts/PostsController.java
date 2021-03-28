package io.pivotal.microservices.posts;

import io.pivotal.microservices.posts.Post;
import io.pivotal.microservices.posts.PostRepository;
import io.pivotal.microservices.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * A RESTFul controller for accessing post information.
 * 
 * @author Karmana Trivedi
 */
@RestController
public class PostsController {

	protected Logger logger = Logger.getLogger(PostsController.class
			.getName());
	protected PostRepository postRepository;

	/**
	 * Create an instance plugging in the respository of Posts.
	 *
	 * @param postRepository
	 *            An post repository implementation.
	 */
	@Autowired
	public PostsController(PostRepository postRepository) {
		this.postRepository = postRepository;

		logger.info("PostRepository says system has "
				+ postRepository.countPosts() + " posts");
	}


	/**
	 * Fetch posts with the specified name. A partial case-insensitive match
	 * is supported. So <code>http://.../posts/owner/a</code> will find any
	 * posts with upper or lower case 'a' in their name.
	 * 
	 * @param partialSubject
	 * @return A non-null, non-empty set of posts.
	 * @throws PostNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/posts/{subject}")
	public List<Post> bySubject(@PathVariable("subject") String partialSubject) {
		logger.info("posts-service bySubject() invoked: "
				+ postRepository.getClass().getName() + " for "
				+ partialSubject);

		List<Post> posts = postRepository
				.findBySubjectContainingIgnoreCase(partialSubject);
		logger.info("posts-service bySubject() found: " + posts);

		if (posts == null || posts.size() == 0)
			throw new PostNotFoundException(partialSubject);
		else {
			return posts;
		}
	}

	@RequestMapping("/posts/bytid/{threadid}")
	public List<Post> byThreadID(@PathVariable Long threadid) {
		logger.info("posts-service byThreadID() invoked: "
				+ postRepository.getClass().getName() + " for "
				+ threadid);

		List<Post> posts = postRepository
				.findByThreadID(threadid);
		logger.info("posts-service byThreadID() found: " + posts);

		if (posts == null || posts.size() == 0)
			throw new PostNotFoundException(threadid);
		else {
			return posts;
		}
	}

	@RequestMapping("/posts/add/{subject}/{body}")
	public void addNewPost(@PathVariable String subject, @PathVariable String body) {
		logger.info("posts-service addNewPost() invoked: "
				+ postRepository.getClass().getName() + " for "
				+ " Subject :" + subject
				+ " Body :" + body);

		Post document = new Post(subject, body);
		postRepository.save(document);

		logger.info("post-service addNewPost() done.");
	}

	@RequestMapping("/posts/add/{threadid}/{subject}/{body}")
	public void addNewPostWithThreadID(@PathVariable Long threadid, @PathVariable String subject, @PathVariable String body) {
		logger.info("posts-service addNewPostWithThreadID() invoked: "
				+ postRepository.getClass().getName() + " for "
				+ " ThreadID :" + threadid
				+ " Subject :" + subject
				+ " Body :" + body);

		Post document = new Post(threadid, subject, body);
		postRepository.save(document);

		logger.info("post-service addNewPostWithThreadID() done.");
	}
}
