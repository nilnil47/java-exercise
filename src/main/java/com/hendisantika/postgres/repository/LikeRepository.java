package com.hendisantika.postgres.repository;

import com.hendisantika.postgres.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository  extends JpaRepository<Like, Long> {
    Integer countByPostId(Long postId);
}
