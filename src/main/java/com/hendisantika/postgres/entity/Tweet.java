package com.hendisantika.postgres.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 24/09/18
 * Time: 18.48
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Tweets")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tweet extends AuditModel {
    @Id
    @GeneratedValue(generator = "tweet_generator")
    @SequenceGenerator(
            name = "tweet_generator",
            sequenceName = "tweet_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String content;

    private String username;

    @Transient
    private int likeCount;

    @Transient
    private int reTweetsCount;
}
