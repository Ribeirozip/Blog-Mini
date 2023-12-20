package com.example.posttest.user;

import com.example.posttest.post.Post;
import com.example.posttest.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/perfil")
public class ControllerUser {
    @Autowired(required = true)
    public UserRepository userRepository;

//    @Autowired
//    private PostRepository postRepository;
    @GetMapping
    public ResponseEntity getAllusers(){
        var allusers = userRepository.findAll();
        return ResponseEntity.ok(allusers);
    }
//    @GetMapping("/{userId}/posts")
//    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable (value="userId) Long userId){
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()){
//            User user = userOptional.get();
//            List<Post> posts = postRepository.findByUser(user);
//            return ResponseEntity.ok(posts);
//        }else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @PostMapping
    public ResponseEntity<String> registeruser(@RequestBody User user){
        if (user ==null || user.getName()==null || user.getUsername()==null||user.getSenha()==null ){
            return ResponseEntity.badRequest().body("User,nome e senha são obrigatorios");
        }
        //tenho q fazer verificação de equals e hash pra ver se o user ja existe
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        newUser.setSenha(user.getSenha());
        userRepository.save(newUser);
        return ResponseEntity.ok("Novo user criado");
    }
    @PutMapping("/")
    public ResponseEntity<String> updateuser(@RequestBody User user){
        if (user.getIdUser()==null){
            return ResponseEntity.badRequest().body("Id de user é obrigatorio");
        }
        Optional<User> existingUser =userRepository.findById(user.getIdUser());

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setSenha(user.getSenha());
            userToUpdate.setName(user.getName());

            userRepository.save(userToUpdate);

            return ResponseEntity.ok("User atualizado com sucesso.");
        }else {
            return new ResponseEntity<>("User não encontrado,Atualização falhou.", HttpStatus.NOT_FOUND);
        }
        }
        @DeleteMapping("/")
        public ResponseEntity<String> deleteuser(@RequestBody User userDel){
        if(userDel==null){
            return ResponseEntity.badRequest().body("ID de user é obrigatorio");
        }
            System.out.println("Tentativa de excluir post com ID:" + userDel);
            Optional<User> existingUser = userRepository.findById(userDel.getIdUser());
            if (existingUser.isPresent()){
                userRepository.deleteById(userDel.getIdUser());
                return ResponseEntity.ok("User Excluido com sucesso.");
            }else {
                return new ResponseEntity<>("User nao encontrado, Excluir falhou.",HttpStatus.NOT_FOUND);
            }
        }
    }

