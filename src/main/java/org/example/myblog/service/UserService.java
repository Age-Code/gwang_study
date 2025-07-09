package org.example.myblog.service;

import org.example.myblog.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto.SignupResDto signup(UserDto.SignupReqDto signupReqDto);
}
