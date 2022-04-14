package com.sparta.webminiproject27jo.Contoller;

import com.sparta.webminiproject27jo.Dto.PostResponseDto;
import com.sparta.webminiproject27jo.Model.PostLike;
import com.sparta.webminiproject27jo.Service.PostLikeService;
import com.sparta.webminiproject27jo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostLikeService postLikeService;
    //좋아요 버튼 누르기
    @PostMapping("/api/like/{postId}")
    public Boolean postLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails)
     {
        return postLikeService.addLike(postId, userDetails.getUser().getId());
    }

//    @GetMapping("/api/likes")
//    public List<PostLike> showComments() {
//        return postLikeService.showLike();
//    }

    @PostMapping("/api/posts/rank")
    public List<PostResponseDto> topPosts(){
        return postLikeService.topPosts();
    }


}