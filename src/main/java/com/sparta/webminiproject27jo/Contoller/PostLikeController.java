package com.sparta.webminiproject27jo.Contoller;


import com.sparta.webminiproject27jo.Dto.PostLikeResponseDto;
import com.sparta.webminiproject27jo.Dto.PostRequestDto;
import com.sparta.webminiproject27jo.Service.PostLikeService;
import com.sparta.webminiproject27jo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("api/like/{postId}/{uid}")
    public PostLikeResponseDto postLike(@PathVariable Long postId, @PathVariable Long uid
//            , @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return postLikeService.addLike(postId,
//                userDetails.getUser().getId()
        uid);
    }

    //게시글 좋아요 개수 수정
    @PutMapping("/api/posts/{postId}/{uid}")
    public Long updateDiary(
            @PathVariable Long postId,
            @PathVariable Long uid
//            , @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postService.updatePost(postId, requestDto
//                , userDetails
        );
        return postId;
    }
}