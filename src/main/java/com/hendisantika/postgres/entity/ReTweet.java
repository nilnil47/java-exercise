package com.hendisantika.postgres.entity;

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
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue = 1000
    )
    private Long id;

    private Long postId;

    private String username;

    @Transient
    private String content;
}