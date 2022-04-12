package com.sparta.webminiproject27jo.Service;


import com.sparta.webminiproject27jo.Dto.PostLikeRequestDto;
import com.sparta.webminiproject27jo.Dto.PostLikeResponseDto;
import com.sparta.webminiproject27jo.Model.Post;
import com.sparta.webminiproject27jo.Model.PostLike;
import com.sparta.webminiproject27jo.Model.User;
import com.sparta.webminiproject27jo.Repository.PostLikeRepository;
import com.sparta.webminiproject27jo.Repository.PostRepository;
import com.sparta.webminiproject27jo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public PostLikeResponseDto addLike(Long postId, Long uid) {
        User user = userRepository.findById(uid).orElseThrow(
                () -> new IllegalArgumentException("유저 정보가 없습니다.")
        );

        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("일기가 없습니다.")
        );

        PostLike findLike = postLikeRepository.findByUserAndPost(user,post).orElse(null);

        if(findLike == null){
            PostLikeRequestDto requestDto = new PostLikeRequestDto(user, post);
            PostLike postLike = new PostLike(requestDto);
            postLikeRepository.save(postLike);
        } else {
            postLikeRepository.deleteById(findLike.getId());
        }
        return new PostLikeResponseDto(postId, postLikeRepository.countByPost(post));
    }

  public List<PostLike> showLike() {

        return postLikeRepository.findAll();
    }

}

