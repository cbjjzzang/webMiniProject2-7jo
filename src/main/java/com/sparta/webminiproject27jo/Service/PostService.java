package com.sparta.webminiproject27jo.Service;

import com.sparta.webminiproject27jo.Dto.PostRequestDto;
import com.sparta.webminiproject27jo.Model.Comment;
import com.sparta.webminiproject27jo.Model.Post;
import com.sparta.webminiproject27jo.Repository.CommentRepository;
import com.sparta.webminiproject27jo.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
//    private final ImageUrlRepository imageUrlRepository;
//    private final DiaryLikeRepository diaryLikeRepository;



    //글 작성
    @Transactional
    public Post createPost(
            PostRequestDto requestDto
//            ,User user
    ) {

//        List<ImageUrl> imageUrlList1 = new ArrayList<>();
//        for (ImageUrl imageUrls : requestDto.getImageUrlList()) {
//            imageUrlList1.add(imageUrlRepository.save(imageUrls)
//            );
//        }

//        String content = requestDto.getContent();
//        List<Comment> comments = commentRepository.findAllByPostId(postId);
//        if (requestDto.getContent() == null) {
//            throw new IllegalArgumentException("내용을 입력해주세요.");
//        }
//        if (content.length() > 1000) {
//            throw new IllegalArgumentException("1000자 이하로 입력해주세요.");
//        }

        Post post = new Post(requestDto);

        return postRepository.save(post);
    }


    //수정
    @Transactional
    public Post updatePost(
            Long id,
            PostRequestDto requestDto
//            ,UserDetailsImpl userDetails
    ) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일기가 존재하지 않습니다.")
        );

        post.updatePost(requestDto);
        postRepository.save(post);
        return post;
    }

    //좋아요 더하기 수정




    //삭제
    @Transactional
    public void deletePost(Long postId
//            , UserDetailsImpl userDetails
    ) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("일기가 존재하지 않습니다.")
        );
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        for(Comment comment : comments){
            Long temp = comment.getId();
            commentRepository.deleteById(temp);
        }
//        postLikeRepository.deleteByPost(post);
        postRepository.deleteById(postId);
    }
}