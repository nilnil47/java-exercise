package com.hendisantika.postgres.controller;

import com.hendisantika.postgres.entity.Like;
import com.hendisantika.postgres.exception.ResourceNotFoundException;
import com.hendisantika.postgres.repository.ReTweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 24/09/18
 * Time: 18.57
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private ReTweetRepository reTweetRepository;


//    @GetMapping
//    public Page<Like> getQuestions(Pageable pageable) {
//        return reTweetRepository.findAll(pageable);
//    }


//    @PostMapping
//    public Like createQuestion(@Valid @RequestBody Like like) {
//        return reTweetRepository.save(like);
//    }

//    @PutMapping("/{postId}/likes")
//    public Like addLike(@PathVariable Long postId,
//                               @Valid @RequestBody Like likeRequest) {
//        // todo: add if post does not exist
//        likeRequest.setPostId(postId);
//        return questionRepository.findById(questionId)
//                .map(like -> {
//                    like.setTitle(likeRequest.getTitle());
//                    like.setDescription(likeRequest.getDescription());
//                    return questionRepository.save(like);
//                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
//    }


    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return reTweetRepository.findById(questionId)
                .map(like -> {
                    reTweetRepository.delete(like);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }
}


//return questionRepository.findById(questionId)
//        .map(like -> {
//        like.setTitle(likeRequest.getTitle());
//        like.setDescription(likeRequest.getDescription());
//        return questionRepository.save(like);
//        }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));