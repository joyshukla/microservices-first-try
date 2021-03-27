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
				+ postRepository.countPosts() + " accounts");
	}

	/**
	 * Fetch an post with the specified post number.
	 * 
	 * @param postNumber
	 *            A numeric, 9 digit post number.
	 * @return The account if found.
	 * @throws PostNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping("/posts/{postNumber}")
	public Post byNumber(@PathVariable("postNumber") String postNumber) {

		logger.info("post-service byNumber() invoked: " + postNumber);
		Post post = postRepository.findByNumber(postNumber);
		logger.info("post-service byNumber() found: " + post);

		if (post == null)
			throw new PostNotFoundException(postNumber);
		else {
			return post;
		}
	}

	/**
	 * Fetch accounts with the specified name. A partial case-insensitive match
	 * is supported. So <code>http://.../accounts/owner/a</code> will find any
	 * accounts with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of accounts.
	 * @throws PostNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/posts/owner/{name}")
	public List<Post> byOwner(@PathVariable("name") String partialName) {
		logger.info("posts-service byOwner() invoked: "
				+ postRepository.getClass().getName() + " for "
				+ partialName);

		List<Post> posts = postRepository
				.findByOwnerContainingIgnoreCase(partialName);
		logger.info("posts-service byOwner() found: " + posts);

		if (posts == null || posts.size() == 0)
			throw new PostNotFoundException(partialName);
		else {
			return posts;
		}
	}
}
