package com.sparta.webminiproject27jo.Model;

import com.example.todaydiary.diary.Diary;
import com.example.todaydiary.timestamped.Timestamped;
import com.example.todaydiary.user.User;
import com.sparta.webminiproject27jo.Dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "POST_ID",nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    public Comment(CommentRequestDto requestDto, Post post, User user){
        this.comment = requestDto.getComment();
        this.post = post;
        this.user = user;
    }


    public void updateComment(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}