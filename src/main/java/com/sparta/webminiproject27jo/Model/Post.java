package com.sparta.webminiproject27jo.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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

    @OneToMany
    @JoinColumn
    private List<Comment> comment;


    public Post(String cafeName, int deliveryFee, int totalPrice, List<FoodOrder> foods){
        this.restaurantName = cafeName;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.foods = foods;
    }
//    public OrderList(OrderDto orderDto){
//        this.restaurantName = orderDto.getRestaurantName();
//        this.foods = orderDto.getFoods();
//        this.deliveryFee = orderDto.getDeliveryFee();
//        this.totalPrice = orderDto.getTotalPrice();
//    }

}