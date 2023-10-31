package com.tonystark.apirest.services;

import com.tonystark.apirest.dto.PostDTO;

import java.util.List;

public interface PostService {

    public PostDTO savePost(PostDTO postDTO);

    public List<PostDTO> getPosts(int pageN, int pageSize);

    public PostDTO getPostById(long id);

    //Update Posts
    //1. Find or fail a post by id, then update it correspondingly
    public PostDTO updatePost(PostDTO postDTO, long id);

    public void deletePost(long id);




}
