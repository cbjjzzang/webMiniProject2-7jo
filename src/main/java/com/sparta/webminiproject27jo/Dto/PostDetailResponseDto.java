package com.sparta.webminiproject27jo.Dto;

import com.sparta.webminiproject27jo.Model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class PostDetailResponseDto {
    private final Long postId;
    private final Long userId;
    private final String content;
    private final LocalDateTime modifiedAt;
    private final String imageUrl;
    private Long postLikeTotal;
    private List<Comment> comments;


    public PostDetailResponseDto(Long postId, Long userId, String content, LocalDateTime modifiedAt, String imageUrl, Long postLikeTotal, List<Comment> comments){
//    List<ImageUrl> imageUrlList , String emotion, String tag, Boolean is_open, DiaryLike diaryLike, Long diaryLikeTotal) {
        this.postId = postId;
        this.content = content;
        this.userId = userId;
        this.modifiedAt = modifiedAt;
        this.imageUrl = imageUrl;
        this.postLikeTotal = postLikeTotal;
        this.comments = comments;

    }

}