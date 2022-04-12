package com.sparta.webminiproject27jo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SignupRequestDto {

    private String username;

    private String nickname;

    private String password;
}
