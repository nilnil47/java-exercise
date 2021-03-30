package com.hendisantika.postgres.controller;

import com.hendisantika.postgres.entity.Tweet;
import com.hendisantika.postgres.exception.ResourceNotFoundException;
import com.hendisantika.postgres.repository.TweetRepository;
import com.hendisantika.postgres.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
@RequestMapping("questions")
public class AnswerController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/tweets")
    public List<Tweet> getAnswersByQuestionId() {
        return tweetRepository.findAll();
    }

    @PostMapping("/{questionId}/answers")
    public Tweet addAnswer(@PathVariable Long questionId,
                           @Valid @RequestBody Tweet tweet) {
        return questionRepository.findById(questionId)
                .map(question -> {
//                    tweet.setQuestion(question);
                    return tweetRepository.save(tweet);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

    @PutMapping("/{questionId}/answers/{answerId}")
    public Tweet updateAnswer(@PathVariable Long questionId,
                              @PathVariable Long answerId,
                              @Valid @RequestBody Tweet tweetRequest) {
        if (!questionRepository.existsById(questionId)) {
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
        if (!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Question not found with id " + questionId);
        }

        return tweetRepository.findById(answerId)
                .map(tweet -> {
                    tweetRepository.delete(tweet);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));

    }
}