package com.example.posttest.post;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/paginainicial")
public class ControllerPost {
    @Autowired(required = true)
    public PostRepository postRepository;

    @GetMapping
    public ResponseEntity getAllpost(){
        var allposts = postRepository.findAll();
        return ResponseEntity.ok(allposts);
    }

    @PostMapping
    public ResponseEntity<String> registerpost(@RequestBody Post post){
        if(post ==null|| post.getTitulo_post() == null || post.getConteudo_post() ==null){
            return ResponseEntity.badRequest().body("Titulo e conteudo são obirgatórios");
        }
        Post newPost= new Post();
        newPost.setTitulo_post(post.getTitulo_post());
        newPost.setImage_post(post.getImage_post());
        newPost.setConteudo_post(post.getConteudo_post());
        postRepository.save(newPost);
        return ResponseEntity.ok("Novo post feito");
    }
    @PutMapping("/")
    public ResponseEntity<String> updatepost(@RequestBody Post updatePostDTO) {
        if (updatePostDTO.getId_post()==null){
            return ResponseEntity.badRequest().body("ID do post é obrigatorio");
        }
        Optional<Post> existingPost = postRepository.findById(updatePostDTO.getId_post());

        if (existingPost.isPresent()) {
            Post postToUpdate = existingPost.get();
            postToUpdate.setTitulo_post(updatePostDTO.getTitulo_post());
            postToUpdate.setImage_post(updatePostDTO.getImage_post());
            postToUpdate.setConteudo_post(updatePostDTO.getConteudo_post());


            postRepository.save(postToUpdate);

            return ResponseEntity.ok("Post atualizado com sucesso.");
        } else {
            return new ResponseEntity<>("Post não encontrado. Atualização falhou.", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/")
    public ResponseEntity<String> deletepost(@RequestBody Post postDel) {
        if(postDel==null){
            return ResponseEntity.badRequest().body("ID do post é obrigatorio");
        }
        System.out.println("Tentativa de excluir post com ID: "+postDel);
        Optional<Post> existingPost = postRepository.findById(postDel.getId_post());
        if (existingPost.isPresent()) {
            postRepository.deleteById(postDel.getId_post());
            return ResponseEntity.ok("Post excluido com sucesso.");
        } else {
            return new ResponseEntity<>("Post não encontrado. Excluir falhou.", HttpStatus.NOT_FOUND);
        }
    }


}
