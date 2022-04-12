package com.sparta.webminiproject27jo.Dto;

import lombok.Getter;

@Getter
public class PostLikeResponseDto {
    private Long postId;
    private Long totalLike;

    public PostLikeResponseDto(Long postId, Long totalLike){
        this.postId = postId;
        this.totalLike = totalLike;
    }
}