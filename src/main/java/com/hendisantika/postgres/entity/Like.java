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
@Table(name = "Likes")
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Like extends AuditModel {
    @Id
    @GeneratedValue(generator = "like_generator")
    @SequenceGenerator(
            name = "like_generator",
            sequenceName = "like_sequence",
            initialValue = 1000
    )
    private Long id;

    private Long postId;

    private String username;
}