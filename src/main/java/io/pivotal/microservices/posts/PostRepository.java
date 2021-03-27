package io.pivotal.microservices.posts;

import java.util.List;

import io.pivotal.microservices.posts.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Repository for Post data implemented using Spring Data JPA.
 *
 * @author Karmana Trivedi
 */
public interface PostRepository extends Repository<Post, Long> {
    /**
     * Find an account with the specified account number.
     *
     * @param postNumber
     * @return The account if found, null otherwise.
     */
    public Post findByNumber(String postNumber);

    /**
     * Find accounts whose owner name contains the specified string
     *
     * @param partialName
     *            Any alphabetic string.
     * @return The list of matching posts - always non-null, but may be
     *         empty.
     */
    public List<Post> findByOwnerContainingIgnoreCase(String partialName);

    /**
     * Fetch the number of posts known to the system.
     *
     * @return The number of posts.
     */
    @Query("SELECT count(*) from Post")
    public int countPosts();
}
