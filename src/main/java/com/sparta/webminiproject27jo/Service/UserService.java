package com.sparta.webminiproject27jo.Service;


import com.sparta.webminiproject27jo.Dto.SignupRequestDto;
import com.sparta.webminiproject27jo.Dto.UserInfoDto;
import com.sparta.webminiproject27jo.Dto.UsernameCheckDto;
import com.sparta.webminiproject27jo.Model.User;
import com.sparta.webminiproject27jo.Repository.UserRepository;
import com.sparta.webminiproject27jo.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    //회원가입 처리 및 중복검사
    public ResponseEntity<User> registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        Optional<User> existUsername = userRepository.findByUsername(username);
        if(existUsername.isPresent()) throw new IllegalArgumentException("이미 존재하는 아이디입니다.");

        String nickname = requestDto.getNickname();

        Optional<User> existNickname = userRepository.findByNickname(nickname);
        if(existNickname.isPresent()) throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");

        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, nickname, password);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    //아이디 중복검사
//    public String usernameCheck(UsernameCheckDto usernameCheckDto) {
//        String msg = "사용가능한 아이디 입니다.";
//
//        Optional<User> existUsername = userRepository.findByUsername(usernameCheckDto.getUsername());
//        if(existUsername.isPresent()) throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
//    }


    //로그인한 유저 정보 가져오기
    public UserInfoDto getUserInfo(UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        String nickname = userDetails.getUser().getNickname();
        Long userId = userDetails.getUser().getId();

        return new UserInfoDto(userId, username, nickname);

    }

}
