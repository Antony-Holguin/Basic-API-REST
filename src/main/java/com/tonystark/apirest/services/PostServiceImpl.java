package com.tonystark.apirest.services;

import com.tonystark.apirest.dto.PostDTO;
import com.tonystark.apirest.entities.Post;
import com.tonystark.apirest.exceptions.ResourceNotFoundException;
import com.tonystark.apirest.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    //En la implementacion del servicio se debe inyectar el repositorio
    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDTO savePost(PostDTO postDTO) {

        //-------De json convertir a Un POJO para guardarlo en la base de datos
        Post post = convertirAEntidad(postDTO);

        //De POJO a DTO
        Post newPost = postRepository.save(post);
        //------
        PostDTO postDtoResponse = convertirADto(post);
        return postDtoResponse;
    }

    @Override
    public List<PostDTO> getPosts(int pageN, int pageSize) {
        //Pageable and page from spring domain
        Pageable pageableObject = PageRequest.of(pageN, pageSize);
        Page<Post> posts = postRepository.findAll(pageableObject);

        List<Post> listOfPosts = posts.getContent();
        //List<Post> posts = postRepository.findAll();
        return listOfPosts.stream()
                .map(this::convertirADto).
                collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(long id) {
        Post postById = postRepository.findById(id).
                orElseThrow( ()-> new ResourceNotFoundException("Post","id",id));
        return convertirADto(postById);

    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        Post postById = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "id",id));

        postById.setTitle(postDTO.getTitle());
        postById.setDescription(postDTO.getDescription());
        postById.setPicture(postDTO.getPicture());

        Post updatedPost = postRepository.save(postById);

        return convertirADto(updatedPost);

    }

    @Override
    public void deletePost(long id) {
        Post postById = postRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Post", "id",id));

        postRepository.delete(postById);
    }


    //Convertir a Dto
    private PostDTO convertirADto(Post post){
        PostDTO postDto = new PostDTO();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setPicture(post.getPicture());
        return postDto;
    }

    //Convertir a POJO
    private Post convertirAEntidad(PostDTO postdto){
        Post post = new Post();
        post.setId(postdto.getId());
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setPicture(postdto.getPicture());
        return post;
    }

}
