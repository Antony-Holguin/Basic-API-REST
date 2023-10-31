package com.tonystark.apirest.controllers;

import com.tonystark.apirest.dto.PostDTO;
import com.tonystark.apirest.dto.UserDTO;
import com.tonystark.apirest.entities.Post;
import com.tonystark.apirest.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("allPosts")
    public List<PostDTO> getAllPosts(
            @RequestParam(value = "pageN", defaultValue = "0", required = false) int pageN,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return postService.getPosts(pageN, pageSize);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }


    @PostMapping()
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postdto){ //@RequestMapping sirve para capturar los datos json
        return new ResponseEntity<>(postService.savePost(postdto), HttpStatus.CREATED);
    }





    @PutMapping("/post/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable long id){
        return new ResponseEntity<>(postService.updatePost(postDTO,id), HttpStatus.OK);
    }

    @DeleteMapping("post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }




}
