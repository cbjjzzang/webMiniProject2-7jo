package com.sparta.webminiproject27jo.Dto;

import com.sparta.webminiproject27jo.Model.PostLike;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class PostResponseDto {
    private final Long postId;
    private final Long userId;
    private final String content;
    private final LocalDateTime modifiedAt;
    private final String imageUrl;
//    private final String emotion;
//    private final String tag;
//    private final Boolean is_open;
//    private PostLike postLike;
    private Long postLikeTotal;


    public PostResponseDto(Long postId,String content,Long userId, LocalDateTime modifiedAt, String imageUrl, Long postLikeTotal){
//    List<ImageUrl> imageUrlList , String emotion, String tag, Boolean is_open, DiaryLike diaryLike, Long diaryLikeTotal) {
        this.postId = postId;
        this.content = content;
        this.userId = userId;
        this.modifiedAt = modifiedAt;
        this.imageUrl = imageUrl;
//        this.emotion = emotion;
//        this.tag = tag;
//        this.is_open = is_open;
//        this.postLike = postLike;
        this.postLikeTotal = postLikeTotal;

    }
}