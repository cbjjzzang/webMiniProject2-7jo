package com.sparta.webminiproject27jo.Contoller;


import com.sparta.webminiproject27jo.Dto.CommentRequestDto;
import com.sparta.webminiproject27jo.Model.Comment;
import com.sparta.webminiproject27jo.Model.Post;
import com.sparta.webminiproject27jo.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comments/{postId}")
    public ResponseEntity<Comment> createComment(
//            @PathVariable Long postId,
            @Validated @RequestBody CommentRequestDto requestDto
//            @AuthenticationPrincipal UserDetailsImpl userDetails

    ){Comment comment = commentService.createComment(requestDto);
//        Comment comment = commentService.createComment(requestDto,userDetails);
        return ResponseEntity.ok(comment); //responseEntity를 생성하는 함수
    }

    @GetMapping("/api/post/{postId}/comments")
    public Optional<Post> getComments(@PathVariable Long postId){
        return commentService.getPost(postId);

    }
    @GetMapping("/api/post/comments")
    public List<Comment> showComments(){
        return commentService.showComment();

    }
//    // 게시글 디테일 조회 - 여기서 글+댓글 정보 읽어올 수 있음
//    @GetMapping("/api/diary/{id}")
//    public Diary getDiary(@PathVariable Long id) {
//        Diary diary = diaryRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("id가 존재하지 않습니다."));
//        return diary;
//    }

    @DeleteMapping("/api/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId
//                              @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
//        commentService.deleteComment(commentId,userDetails)
        commentService.deleteComment(commentId);
    }


}