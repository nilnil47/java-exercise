package com.hendisantika.postgres.repository;

import com.hendisantika.postgres.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 24/09/18
 * Time: 18.51
 * To change this template use File | Settings | File Templates.
 */
public interface TweetRepository extends JpaRepository<Tweet, Long> {
//    List<Tweet> findByQuestionId(Long questionId);
}