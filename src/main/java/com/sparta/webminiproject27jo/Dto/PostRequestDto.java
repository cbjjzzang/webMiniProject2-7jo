package com.sparta.webminiproject27jo.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Data
public class PostRequestDto {

    private String content;
    private int likeCount;
    private String imageUrl;
    private Long userId;
    private Boolean is_open;

}