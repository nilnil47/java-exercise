package com.hendisantika.postgres.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 24/09/18
 * Time: 18.46
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Retweet")
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReTweet extends AuditModel {
    @Id
    @GeneratedValue(generator = "retweet_generator")
    @SequenceGenerator(
            name = "retweet_generator",
            sequenceName = "retweet_sequence",
            initialValue = 1000
    )

    @JsonIgnore
    private Long id;

    @JsonProperty("tweet_user")
    @Transient
    private String tweetUser;

    @JsonProperty("tweet_id")
    private Long postId;

    private String username;

    @Transient
    private String content;
}