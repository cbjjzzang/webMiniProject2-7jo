package com.sparta.webminiproject27jo.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Data
public class PostRequestDto {

    private String content;
    private String imageUrl;
    private Long userId;

    public PostRequestDto(String content, String imageUrl, Long userId) {
        this.content = content;
        this.userId = userId;
        this.imageUrl = imageUrl;
    }
}

