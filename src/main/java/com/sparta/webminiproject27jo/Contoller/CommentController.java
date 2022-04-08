package com.sparta.webminiproject27jo.Contoller;


import com.sparta.webminiproject27jo.Dto.CommentRequestDto;
import com.sparta.webminiproject27jo.Model.Comment;
import com.sparta.webminiproject27jo.Model.Post;
import com.sparta.webminiproject27jo.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comments/{postId}")
    public ResponseEntity<Comment> createComment(
//            @PathVariable Long postId,
            @Validated @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails

    ){
        Comment comment = commentService.createComment(requestDto,userDetails);
        return ResponseEntity.ok(comment); //responseEntity를 생성하는 함수
    }

    @GetMapping("/api/post/{postId}/comments")
    public Post getComments(@RequestBody Long postId){
        return commentService.getComment(postId);

    }

//    @PutMapping("/api/comment/{commentId}")
//    public Comment updateComment(
//            @PathVariable Long commentId,
//            @RequestBody CommentRequestDto requestDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails
//    ){
//        return commentService.updateComment(commentId,requestDto,userDetails);
//    }

    @DeleteMapping("/api/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        commentService.deleteComment(commentId,userDetails);
    }


}