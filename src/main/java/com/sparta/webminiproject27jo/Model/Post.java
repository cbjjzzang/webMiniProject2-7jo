package com.sparta.webminiproject27jo.Model;

import com.sparta.webminiproject27jo.Dto.PostRequestDto;
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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private int likeCount;

    @Column
    private String imageUrl;

    @Column
    private Long userId;
//
//    @OneToMany
//    @JoinColumn
//    private List<Comment> comment;

    @OneToMany(mappedBy = "postId")
    private List<Comment> comments = new ArrayList<>();

//    public Post(String cafeName, int deliveryFee, int totalPrice, List<FoodOrder> foods){
//        this.restaurantName = cafeName;
//        this.deliveryFee = deliveryFee;
//        this.totalPrice = totalPrice;
//        this.foods = foods;
//    }
    public Post(PostRequestDto requestDto){
        this.content = requestDto.getContent();
        this.likeCount = requestDto.getLikeCount();
        this.imageUrl = requestDto.getIamgeUrl();
        this.userId = requestDto.getUserId();
//        this.comment = comments;
    }

}