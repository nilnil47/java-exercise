package com.hendisantika.postgres.controller;

import com.hendisantika.postgres.entity.Like;
import com.hendisantika.postgres.entity.ReTweet;
import com.hendisantika.postgres.entity.Tweet;
import com.hendisantika.postgres.exception.ResourceNotFoundException;
import com.hendisantika.postgres.repository.LikeRepository;
import com.hendisantika.postgres.repository.TweetRepository;
import com.hendisantika.postgres.repository.ReTweetRepository;
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
@RequestMapping("tweets")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ReTweetRepository reTweetRepository;

    @GetMapping()
    public List<Tweet> getAnswersByQuestionId() {
        return tweetRepository.findAll();
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
    };

//    @GetMapping("retweets")
//    public List<ReTweet> getReTweets() {
//        return reTweetRepository.findAll().stream().map(
//                reTweet -> {
//                    Optional<Tweet> tweet = tweetRepository.findById(reTweet.getId());
//                    tweet.ifPresent(val -> {
//                        String content = val.getContent();
//                        reTweet.setContent(content);
//                    });
//                }
//        );
//    }



    @PostMapping()
    public Tweet addTweet(@Valid @RequestBody Tweet tweet) {
        tweetRepository.save(tweet);
        return tweet;
    }

    @PostMapping("/{postId}/likes")
    public Like addLike(@PathVariable Long postId,
                        @Valid @RequestBody Like likeRequest) {

        if (tweetRepository.existsById(postId)) {
            likeRequest.setPostId(postId);
            likeRepository.save(likeRequest);
            return likeRequest;
        }
        throw new ResourceNotFoundException("Tweet not found with id " + postId);
    }

    @PostMapping("/{postId}/retweet")
    public ReTweet addReTweet(@PathVariable Long postId,
                        @Valid @RequestBody ReTweet reTweet) {

        if (tweetRepository.existsById(postId)) {
            reTweet.setPostId(postId);
            reTweetRepository.save(reTweet);
            return reTweet;
        }
        throw new ResourceNotFoundException("Tweet not found with id " + postId);
    }




//    @PostMapping("/{questionId}/answers")
//    public Tweet addTweet(@PathVariable Long questionId,
//                          @Valid @RequestBody Tweet tweet) {
//        return reTweetRepository.findById(questionId)
//                .map(question -> {
////                    tweet.setQuestion(question);
//                    return tweetRepository.save(tweet);
//                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
//    }

    @PutMapping("/{questionId}/answers/{answerId}")
    public Tweet updateAnswer(@PathVariable Long questionId,
                              @PathVariable Long answerId,
                              @Valid @RequestBody Tweet tweetRequest) {
        if (!reTweetRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }

        return tweetRepository.findById(answerId)
                .map(tweet -> {
//                    tweet.setText(tweetRequest.getText());
                    return tweetRepository.save(tweet);
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));
    }

    @DeleteMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId,
                                          @PathVariable Long answerId) {
        if (!reTweetRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }

        return tweetRepository.findById(answerId)
                .map(tweet -> {
                    tweetRepository.delete(tweet);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));

    }
}