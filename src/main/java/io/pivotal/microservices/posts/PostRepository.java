package io.pivotal.microservices.posts;

import java.util.List;

import io.pivotal.microservices.posts.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Post data implemented using Spring Data JPA.
 *
 * @author Karmana Trivedi
 */
public interface PostRepository extends CrudRepository<Post, Long> {

    /**
     * Find posts subject contains the specified string
     *
     * @param partialSubject
     *            Any alphabetic string.
     * @return The list of matching posts - always non-null, but may be
     *         empty.
     */
    public List<Post> findBySubjectContainingIgnoreCase(String partialSubject);
    public List<Post> findByThreadID(Long threadid);


    /**
     * Fetch the number of posts known to the system.
     *
     * @return The number of posts.
     */
    @Query("SELECT count(*) from Post")
    public int countPosts();
}
