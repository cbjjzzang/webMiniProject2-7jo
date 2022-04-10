package com.sparta.webminiproject27jo.Contoller;


import com.sparta.webminiproject27jo.Dto.PostRequestDto;
import com.sparta.webminiproject27jo.Dto.PostResponseDto;
import com.sparta.webminiproject27jo.Model.Post;
import com.sparta.webminiproject27jo.Repository.PostRepository;
import com.sparta.webminiproject27jo.Service.CommentService;
import com.sparta.webminiproject27jo.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentService commentService;
//    private final DiaryLikeService postLikeService;
//    private final PostLikeRepository postLikeRepository;

    // 게시글 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPost() {
        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();


        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        //계층 간 작업 시 Dto를 사용하는 습관을 갖는게 중요함.
        //Controller에서 직접 Diary diary를 건드리기보다 Dto를 활용하자.
        //효율성 측면에서도 좋음. Diary 테이블(DB)에는 User의 정보 전부(id, nickname, password, email 등)가 연결되어있음.
        //내가 진짜 필요한 정보만 담아서 활용하는 것. User 전체가 아닌 User의 nickname만 뽑아서 쓰는 것이 효율적임.

        for (Post post : posts) {
//            Long diaryLikeTotal = diaryLikeRepository.countByDiary(diary);
            PostResponseDto postResponseDto = new PostResponseDto(
                    post.getPostId(),
//                    post.getLikeCount(),
//                    post.getUser().getNickname(), // <-- Dto 효율성의 좋은 예시
//                    post.getUser().getId(),
//                    post.getUser().getUser_profile(),
                    post.getContent(),
                    post.getUserId(),
                    post.getModifiedAt(),
                    post.getImageUrl()
//                    post.getEmotion(),
//                    post.getTag(),
//                    post.getIs_open(),
//                    post.getDiaryLike(),
//                    diaryLikeTotal
            );

            postResponseDtos.add(postResponseDto);

        }

        return postResponseDtos;
    }
    //특정게시글 조회
    @GetMapping("/api/post/{postId}/comments")
    public Optional<Post> getComments(@PathVariable Long postId){
        return commentService.getPost(postId);

    }

    // 게시글 작성
    @PostMapping("/api/posts")
    public Post createPost(
            @RequestBody PostRequestDto postRequestDto
//            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

//        User user = userDetails.getUser();

        return postService.createPost(postRequestDto);
    }

     //게시글 수정
    @PutMapping("/api/posts/{postId}")
    public Long updateDiary(
            @PathVariable Long postId,
            @RequestBody PostRequestDto requestDto
//            , @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postService.updatePost(postId, requestDto
//                , userDetails
        );
        return postId;
    }
//
//
    //게시글 삭제
    @DeleteMapping("/api/posts/{postId}")
    public Long deletePost(@PathVariable Long postId
//            , @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postService.deletePost(postId
//                , userDetails
        );
        return postId;
    }
//
//
//    //마이페이지
//    @GetMapping("/api/mypage/{uId}")
//    public List<DiaryResponseDto> getMyDiary() {
//
//        List<Diary> diaries = diaryRepository.findAllByOrderByCreatedAtDesc();
//
//        List<DiaryResponseDto> diaryResponseDtos = new ArrayList<>();
//        //계층 간 작업 시 Dto를 사용하는 습관을 갖는게 중요함.
//        //Controller에서 직접 Diary diary를 건드리기보다 Dto를 활용하자.
//        //효율성 측면에서도 좋음. Diary 테이블(DB)에는 User의 정보 전부(id, nickname, password, email 등)가 연결되어있음.
//        //내가 진짜 필요한 정보만 담아서 활용하는 것. User 전체가 아닌 User의 nickname만 뽑아서 쓰는 것이 효율적임.
//        for (Diary diary : diaries) {
//            Long diaryLikeTotal = diaryLikeRepository.countByDiary(diary);
//            DiaryResponseDto diaryResponseDto = new DiaryResponseDto(
//                    diary.getId(),
//                    diary.getTitle(),
//                    diary.getUser().getNickname(), // <-- Dto 효율성의 좋은 예시
//                    diary.getUser().getId(),
//                    diary.getUser().getUser_profile(),
//                    diary.getContent(),
//                    diary.getCreatedAt(),
//                    diary.getModifiedAt(),
//                    diary.getImageUrlList(),
//                    diary.getEmotion(),
//                    diary.getTag(),
//                    diary.getIs_open(),
//                    diary.getDiaryLike(),
//                    diaryLikeTotal
//            );
//
//            diaryResponseDtos.add(diaryResponseDto);
//        }
//
//        return diaryResponseDtos;
//    }

}



