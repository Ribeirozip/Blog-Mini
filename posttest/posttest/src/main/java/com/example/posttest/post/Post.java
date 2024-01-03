package com.example.posttest.post;

import com.example.posttest.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
@Entity(name = "post")
@EqualsAndHashCode(of = "id_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post;
    private String titulo_post;
    private String image_post;
    private String conteudo_post;
//    @CreationTimestamp
//    private LocalDateTime criacao_post;
//   @ManyToOne
//    @JoinColumn(name ="user_id")
//    private User user;


}
