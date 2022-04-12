package com.sparta.webminiproject27jo.Contoller;


import com.sparta.webminiproject27jo.Dto.PostDetailResponseDto;
import com.sparta.webminiproject27jo.Dto.PostRequestDto;
import com.sparta.webminiproject27jo.Dto.PostResponseDto;
import com.sparta.webminiproject27jo.Model.Comment;
import com.sparta.webminiproject27jo.Model.Post;
import com.sparta.webminiproject27jo.Repository.CommentRepository;
import com.sparta.webminiproject27jo.Repository.PostLikeRepository;
import com.sparta.webminiproject27jo.Repository.PostRepository;
import com.sparta.webminiproject27jo.Service.CommentService;
import com.sparta.webminiproject27jo.Service.PostService;
import com.sparta.webminiproject27jo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentService commentService;
    private final PostLikeRepository postLikeRepository;

    // 게시글 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPost() {
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();


        List<PostResponseDto> postResponseDtos = new ArrayList<>();

        for (Post post : posts) {
            Long postLikeTotal = postLikeRepository.countByPost(post);
            PostResponseDto postResponseDto = new PostResponseDto(
                    post.getPostId(),
                    post.getUserId(),
                    post.getContent(),
                    post.getModifiedAt(),
                    post.getImageUrl(),
                    postLikeTotal
            );
            postResponseDtos.add(postResponseDto);
        }

        return postResponseDtos;
    }
    //특정게시글 조회
    @GetMapping("/api/post/{postId}/comments")
    public PostDetailResponseDto getComments(@PathVariable Long postId){
        Post post = postRepository.getById(postId);
        Long postLikeTotal = postLikeRepository.countByPost(post);
        List<Comment> comments = commentRepository.findAllByPostId(postId);

        return new PostDetailResponseDto(
                post.getPostId(),
                post.getUserId(),
                post.getContent(),
                post.getModifiedAt(),
                post.getImageUrl(),
                postLikeTotal,
                comments );

    }

    // 게시글 작성
    @PostMapping("/api/posts")
    public Post createPost(
            @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

//        User user = userDetails.getUser();

        return postService.createPost(postRequestDto);
    }

     //게시글 수정
    @PutMapping("/api/posts/{postId}")
    public Long updateDiary(
            @PathVariable Long postId,
            @RequestBody PostRequestDto requestDto) {
        postService.updatePost(postId, requestDto);
        return postId;
    }
//
//
    //게시글 삭제
    @DeleteMapping("/api/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return postId;
    }

}



