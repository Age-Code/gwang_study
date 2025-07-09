package org.example.myblog.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.myblog.domain.User;
import org.example.myblog.dto.UserDto;
import org.example.myblog.repository.UserRepository;
import org.example.myblog.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceimpl implements UserService {

    final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Signup
    @Override
    public UserDto.SignupResDto signup(UserDto.SignupReqDto signupReqDto){

        User user = userRepository.findByUsername(signupReqDto.getUsername());
        if(user != null) {
            throw new RuntimeException("Already exist");
        }

        signupReqDto.setPassword(bCryptPasswordEncoder.encode(signupReqDto.getPassword()));
        user = userRepository.save(signupReqDto.toEntity());

        return user.toSignupResDto();
    }

}
