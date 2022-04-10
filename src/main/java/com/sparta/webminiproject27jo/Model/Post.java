package com.sparta.webminiproject27jo.Model;

import com.sparta.webminiproject27jo.Dto.PostRequestDto;
import com.sparta.webminiproject27jo.Timestamped.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Entity
@Getter
@NoArgsConstructor
@ToString
@Table

public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PostId;

    @Column
    private String content;

    @Column
    private int likeCount;

    @Column
    private String imageUrl;

    @Column
    private Long userId;


    @OneToMany(mappedBy = "postId")
    private List<Comment> comments = new ArrayList<>();


    public Post(PostRequestDto requestDto){
        this.content = requestDto.getContent();
        this.likeCount = requestDto.getLikeCount();
        this.imageUrl = requestDto.getImageUrl();
        this.userId = requestDto.getUserId();
    }

    public void updatePost(PostRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.imageUrl = requestDto.getImageUrl();
//        this.likeCount = requestDto.getImageUrl();
//        this.is_open = requestDto.getIs_open();
    }
}