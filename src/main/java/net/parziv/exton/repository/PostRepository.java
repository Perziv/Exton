package net.parziv.exton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.parziv.exton.entity.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
