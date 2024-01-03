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

    //passivel de erro
//    @Autowired
//    private PostRepository postRepository;
    @GetMapping
    public ResponseEntity getAlluser(){
        var allusers = userRepository.findAll();
        return ResponseEntity.ok(allusers);
    }
    //passivel de erro
//    @GetMapping("/{userId}/posts")
//    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable (value="userId) Long userId)){
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()){
//            User user = userOptional.get();
//            List<Post> posts = postRepository.findByUser(user);
//            return ResponseEntity.ok(posts);
//        }else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    // login de usuario
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser){
        User user =userRepository.findByUsernameAndSenha(loginUser.getUsername(),loginUser.getSenha());

        if(user != null){
            return ResponseEntity.ok("login bem-sucedido. ID do usuario:"+ user.getId_user());
        }else {
            return ResponseEntity.badRequest().body("Usuario ou senha Invalido");
        }
    }

    @PostMapping
    public ResponseEntity<String> registeruser(@RequestBody User user){
        if (user ==null || user.getName()==null || user.getUsername()==null||user.getSenha()==null ){
            return ResponseEntity.badRequest().body("User,nome e senha são obrigatorios");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Nome de usuário já existe. Escolha um nome de usuário diferente.");
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
        if (user.getId_user()==null){
            return ResponseEntity.badRequest().body("Id de user é obrigatorio");
        }
        Optional<User> existingUser =userRepository.findById(user.getId_user());

        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setSenha(user.getSenha());
            userToUpdate.setName(user.getName());
            userToUpdate.setFtuser(user.getFtuser());

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
            Optional<User> existingUser = userRepository.findById(userDel.getId_user());
            if (existingUser.isPresent()){
                userRepository.deleteById(userDel.getId_user());
                return ResponseEntity.ok("User Excluido com sucesso.");
            }else {
                return new ResponseEntity<>("User nao encontrado, Excluir falhou.",HttpStatus.NOT_FOUND);
            }
        }
    }

