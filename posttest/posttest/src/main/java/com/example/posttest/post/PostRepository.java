package com.example.posttest.post;

import com.example.posttest.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    //passivel de erro
//    List<Post>findByUser(User user);
}
