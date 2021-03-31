package com.hendisantika.postgres.controller;

import com.hendisantika.postgres.entity.Like;
import com.hendisantika.postgres.entity.ReTweet;
import com.hendisantika.postgres.entity.Tweet;
import com.hendisantika.postgres.exception.ResourceNotFoundException;
import com.hendisantika.postgres.repository.LikeRepository;
import com.hendisantika.postgres.repository.ReTweetRepository;
import com.hendisantika.postgres.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 24/09/18
 * Time: 18.54
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ReTweetRepository reTweetRepository;

    @GetMapping("tweets")
    public List<Tweet> getTweets() {
        List<Tweet> tweets = tweetRepository.findAll();
        tweets.forEach(tweet ->
                {
                    tweet.setLikeCount(likeRepository.countByPostId(tweet.getId()));
                    tweet.setReTweetsCount(reTweetRepository.countByPostId(tweet.getId()));
                }
        );
        return tweets;
    }

    @GetMapping("retweets")
    public List<ReTweet> getReTweets() {
        List<ReTweet> retweets = reTweetRepository.findAll();
        retweets.forEach(reTweet ->
                {
                    Optional<Tweet> tweet = tweetRepository.findById(reTweet.getPostId());
                    tweet.ifPresent(val -> {
                        String content = val.getContent();
                        reTweet.setContent(content);
                    });
                }
        );
        return retweets;
    }

    @PostMapping("tweets")
    public Tweet addTweet(@Valid @RequestBody Tweet tweet) {
        tweetRepository.save(tweet);
        return tweet;
    }

    @PostMapping("tweets/{postId}/likes")
    public Like addLike(@PathVariable Long postId,
                        @Valid @RequestBody Like likeRequest) {

        if (tweetRepository.existsById(postId)) {
            likeRequest.setPostId(postId);
            likeRepository.save(likeRequest);
            return likeRequest;
        }
        throw new ResourceNotFoundException("Tweet not found with id " + postId);
    }

    @PostMapping("tweets/{postId}/retweet")
    public ReTweet addReTweet(@PathVariable Long postId,
                              @Valid @RequestBody ReTweet reTweet) {

        if (tweetRepository.existsById(postId)) {
            reTweet.setPostId(postId);
            reTweetRepository.save(reTweet);
            return reTweet;
        }
        throw new ResourceNotFoundException("Tweet not found with id " + postId);
    }
}