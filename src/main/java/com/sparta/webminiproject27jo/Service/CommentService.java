package com.sparta.webminiproject27jo.Service;

import com.sparta.webminiproject27jo.Dto.CommentRequestDto;
import com.sparta.webminiproject27jo.Model.Comment;
import com.sparta.webminiproject27jo.Model.Post;
import com.sparta.webminiproject27jo.Repository.CommentRepository;
import com.sparta.webminiproject27jo.Repository.PostRepository;
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


    @Transactional
    public Comment createComment(
            CommentRequestDto requestDto
//            UserDetailsImpl userDetails
    ) {
//        Long postId = requestDto.getPostId();
//        Post post = postRepository.findById(postId).orElseThrow(
//                () -> new IllegalArgumentException("해당하는 게시글을 찾을 수 없습니다.")
//        );
        Comment comment = new Comment(requestDto
//                ,post
        );
        return commentRepository.save(comment);


    }


    public Optional<Post> getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

//        Post post = new Post(postId, comments);
        return post;
    }

    public List<Comment> showComment() {


//        Post post = new Post(postId, comments);
        return commentRepository.findAll();
    }


    @Transactional
    public void deleteComment(Long commentId
//                              UserDetailsImpl userDetails
    ) {
        commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
//        User user = commentRepository.findById(commentId).get().getUser();
//        Long commentUserId = user.getId();
//        Long loginUserId = userDetails.getUser().getId();
//        if (!Objects.equals(commentUserId, loginUserId)){
//            throw new IllegalArgumentException("작성자만 삭제 할 수 있습니다.");
//        } else {
            commentRepository.deleteById(commentId);
        }

}