package com.sparta.webminiproject27jo.Contoller;


import com.sparta.webminiproject27jo.Dto.PostDetailResponseDto;
import com.sparta.webminiproject27jo.Dto.PostEditRequestDto;
import com.sparta.webminiproject27jo.Dto.PostRequestDto;
import com.sparta.webminiproject27jo.Dto.PostResponseDto;
import com.sparta.webminiproject27jo.Model.Comment;
import com.sparta.webminiproject27jo.Model.Post;
import com.sparta.webminiproject27jo.Model.PostLike;
import com.sparta.webminiproject27jo.Model.User;
import com.sparta.webminiproject27jo.Repository.CommentRepository;
import com.sparta.webminiproject27jo.Repository.PostLikeRepository;
import com.sparta.webminiproject27jo.Repository.PostRepository;
import com.sparta.webminiproject27jo.Repository.UserRepository;
import com.sparta.webminiproject27jo.Service.PostService;
import com.sparta.webminiproject27jo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PostService postService;
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;

    // 게시글 조회
    @PostMapping("/api/posts/{userId}")
    public List<PostResponseDto> getPost(@PathVariable Long userId) {
        System.out.println(userId);
        User user = new User();
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();
        if(userId != null) {
            user = userRepository.getById(userId);
        }
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        boolean postLikes;
        for (Post post : posts) {
            int postLikeTotal = postLikeRepository.countByPost(post);

            Optional<PostLike> postLike= postLikeRepository.findByUserAndPost(user, post);
            postLikes = postLike.isPresent();

            PostResponseDto postResponseDto = new PostResponseDto(
                    post.getPostId(),
                    post.getUserId(),
                    post.getContent(),
                    post.getModifiedAt(),
                    post.getImageUrl(),
                    post.getNickName(),
                    postLikeTotal,
                    postLikes
            );
            postResponseDtos.add(postResponseDto);
        }

        return postResponseDtos;
    }
    //특정게시글 조회
    @GetMapping("/api/postDetail/{postId}/comments")
    public PostDetailResponseDto getComments(@PathVariable Long postId){
        Post post = postRepository.getById(postId);
        int postLikeTotal = postLikeRepository.countByPost(post);
        List<Comment> comments = commentRepository.findByPostIdOrderByModifiedAtDesc(postId);

        return new PostDetailResponseDto(
                post.getPostId(),
                post.getUserId(),
                post.getContent(),
                post.getNickName(),
                post.getModifiedAt(),
                post.getImageUrl(),
                postLikeTotal,
                comments );
    }

    // 게시글 작성
    @PostMapping("/api/post")
    public void upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("content") String content,
            @RequestParam("nickName") String nickName,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws IOException
    {

        PostRequestDto postRequestDto = new PostRequestDto(content, file, nickName, userDetails.getUser().getId());
        postService.upload(postRequestDto, "static");
    }

     //게시글 수정
    @PutMapping("/api/posts/{postId}")
    public Long updatePost(
            @PathVariable Long postId,
            @RequestBody PostEditRequestDto requestDto) {
        postService.updatePost(postId, requestDto);
        return postId;
    }

    //게시글 삭제
    @DeleteMapping("/api/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return postId;
    }
}



