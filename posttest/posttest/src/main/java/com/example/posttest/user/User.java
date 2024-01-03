package com.example.posttest.user;

import com.example.posttest.post.Post;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="user_db")
@Entity(name ="user_db")
@EqualsAndHashCode(of ="id_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String username;

    private String senha;
    private String ftuser;
//    @CreationTimestamp
//    private LocalDateTime createAt;

    //passivel de erro
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Post> posts;

}
