package springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public Post findById(int id);
}