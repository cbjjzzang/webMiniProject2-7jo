package com.sparta.webminiproject27jo.Service;

import com.sparta.webminiproject27jo.Dto.CommentRequestDto;
import com.sparta.webminiproject27jo.Dto.PostRequestDto;
import com.sparta.webminiproject27jo.Dto.PostResponseDto;
import com.sparta.webminiproject27jo.Model.Comment;
import com.sparta.webminiproject27jo.Model.Post;
import com.sparta.webminiproject27jo.Repository.CommentRepository;
import com.sparta.webminiproject27jo.Repository.PostLikeRepository;
import com.sparta.webminiproject27jo.Repository.PostRepository;
import com.sparta.webminiproject27jo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;


    @Transactional
    public Comment createComment(CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Comment comment = new Comment(requestDto, userDetails.getUser().getId());
            return commentRepository.save(comment);
    }


    public List<Comment> showComment() {
        return commentRepository.findAll();
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        commentRepository.deleteById(commentId);
    }

}